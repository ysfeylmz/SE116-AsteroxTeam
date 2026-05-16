package objectville.simulation;

import objectville.cells.Cell;
import objectville.cells.ServiceProvider;
import objectville.cells.UtilityProvider;
import objectville.cells.Zone;

import java.util.List;

public class City {

    private final int rows;
    private final int cols;
    private Cell[][] grid;
    private List<Zone> zones;
    private List<UtilityProvider> utilityProviders;
    private List<ServiceProvider> serviceProviders;
    private ResourcePool resourcePool;

    public City(int rows, int cols) {
        // TODO: implement
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        // TODO: implement
        return rows;
    }

    public int getCols() {
        // TODO: implement
        return cols;
    }

    public Cell getCell(int row, int col) {
        // TODO: implement
        return null;
    }

    public Cell getCell(Position position) {
        // TODO: implement
        return null;
    }

    public void setCell(int row, int col, Cell cell) {
        // TODO: implement
    }

    public boolean isInBounds(int row, int col) {
        // TODO: implement
        return false;
    }

    public List<Zone> getZones() {
        // TODO: implement
        return zones;
    }

    public List<UtilityProvider> getUtilityProviders() {
        // TODO: implement
        return utilityProviders;
    }

    public List<ServiceProvider> getServiceProviders() {
        // TODO: implement
        return serviceProviders;
    }

    public ResourcePool getResourcePool() {
        // TODO: implement
        return resourcePool;
    }
}