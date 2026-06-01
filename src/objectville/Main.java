package objectville;

import objectville.io.MapLoader;
import objectville.io.OutputWriter;
import objectville.simulation.City;
import objectville.simulation.Simulator;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java -jar ObjectVilleGame.jar <mapFile> <tickCount>");
            return;
        }
        String mapPath = args[0];

        int ticks;
        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Tick count must be a whole number, got: " + args[1]);
            return;
        }

        if (ticks < 0) {
            System.err.println("Tick count cannot be negative: " + ticks);
            return;
        }

        try {
            City city = new MapLoader().load(mapPath);
            OutputWriter output = new OutputWriter();
            Simulator simulator = new Simulator(city, output);
            simulator.run(ticks);
        } catch (IOException e) {
            System.err.println("Could not read map file '" + mapPath + "': " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid map: " + e.getMessage());
        }


    }
}
