package objectville.simulation;

import objectville.cells.UtilityProvider;
import objectville.io.OutputWriter;

public class Simulator {

    private final City city;
    private final OutputWriter output;
    private int currentTick;

    public Simulator(City city, OutputWriter output) {
        // TODO: implement
        this.city = city;
        this.output = output;
    }

    public void run(int numTicks) {
        // TODO: implement
    }

    public void tick() {
        // TODO: implement
    }

    protected void distributeServices() {
        // TODO: implement
    }

    protected void distributeUtilities() {
        // TODO: implement
    }

    protected void bfsDistribute(UtilityProvider provider) {
        // TODO: implement
    }

    protected void distributeResources() {
        // TODO: implement
    }

    protected void updateZones() {
        // TODO: implement
    }

    protected void accumulateProduction() {
        // TODO: implement
    }

    public int getCurrentTick() {
        // TODO: implement
        return currentTick;
    }

    public City getCity() {
        // TODO: implement
        return city;
    }
}