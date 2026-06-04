package objectville.io;

import objectville.cells.*;
import objectville.simulation.City;
import objectville.simulation.Position;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public MapLoader() {

    }

    public City load(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Drop trailing blank lines (keep interior structure intact).
            while (!lines.isEmpty() && lines.get(lines.size() - 1).trim().isEmpty()) {
                lines.remove(lines.size() - 1);
            }
            if (lines.isEmpty()) {
                throw new IOException("Map file is empty!");
            }

            int rows;
            int cols;
            List<String> gridLines;

            // The grid uses only letter symbols, never digits. So a first line
            // that is purely "<rows> <cols>" is an optional dimension header.
            if (lines.get(0).trim().matches("\\d+\\s+\\d+")) {
                String[] dimensions = lines.get(0).trim().split("\\s+");
                rows = Integer.parseInt(dimensions[0]);
                cols = Integer.parseInt(dimensions[1]);
                gridLines = lines.subList(1, lines.size());
            } else {
                // Header-less map: derive dimensions from the grid itself.
                gridLines = lines;
                rows = gridLines.size();
                cols = 0;
                for (String g : gridLines) {
                    int len = g.replace(" ", "").replace("\t", "").length();
                    if (len > cols) {
                        cols = len;
                    }
                }
            }

            City city = new City(rows, cols);
            for (int r = 0; r < rows; r++) {
                String cleanLine = r < gridLines.size()
                        ? gridLines.get(r).replace(" ", "").replace("\t", "")
                        : "";
                for (int c = 0; c < cols; c++) {
                    Position pos = new Position(r, c);
                    if (c < cleanLine.length()) {
                        city.setCell(r, c, createCellFromSymbol(cleanLine.charAt(c), pos));
                    } else {
                        city.setCell(r, c, new EmptyCell(pos));
                    }
                }
            }
            return city;
        }
    }

    public Cell createCellFromSymbol(char symbol, Position position) {
        switch (symbol) {
            case 'H': return new Housing(position);
            case 'C': return new Commercial(position);
            case 'I': return new Industrial(position);
            case 'R': return new Road(position);
            case 'P': return new PowerPlant(position);
            case 'W': return new WaterPumpingStation(position);
            case 'T': return new InternetHub(position);
            case 'D': return new Hospital(position);
            case 'F': return new PoliceStation(position);
            case 'S': return new School(position);
            case 'E':
            default:
                return new EmptyCell(position);
        }
    }
}
