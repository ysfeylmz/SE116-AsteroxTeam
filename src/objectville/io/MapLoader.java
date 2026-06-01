package objectville.io;

import objectville.cells.*;
import objectville.simulation.City;
import objectville.simulation.Position;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public MapLoader() {

    }

    public City load(String filePath) throws IOException {
try(BufferedReader br=new BufferedReader(new FileReader(filePath))){
    String firstLine=br.readLine();
    if (firstLine == null) {
        throw new IOException("Map file is empty!");
    }
    String[] dimensions = firstLine.trim().split("\\s+");
    int rows = Integer.parseInt(dimensions[0]);
    int cols = Integer.parseInt(dimensions[1]);
    City city= new City(rows,cols);
    for(int r=0;r<rows;r++){
        String line=br.readLine();
        if(line==null) break;
        String cleanLine = line.replace(" ", "").replace("\t", "");
        for(int c=0;c<cols;c++){
            if(c<cleanLine.length()){
                char symbol =cleanLine.charAt(c);
                Position pos =new Position(r,c);
                Cell cell=createCellFromSymbol(symbol,pos);
                city.setCell(r,c,cell);
            }else{
                city.setCell(r,c,new EmptyCell(new Position(r,c)));
            }
        }
    }return city;
}}

    public Cell createCellFromSymbol(char symbol, Position position) {
switch (symbol){
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