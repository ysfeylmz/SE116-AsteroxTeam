package objectville.simulation;

import objectville.cells.Cell;
import objectville.cells.ServiceProvider;
import objectville.cells.UtilityProvider;
import objectville.cells.Zone;

import java.util.ArrayList;
import java.util.List;

public class City {

    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private final List<Zone> zones;
    private final List<UtilityProvider> utilityProviders;
    private final List<ServiceProvider> serviceProviders;
    private final ResourcePool resourcePool;

    public City(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.zones = new ArrayList<>();
        this.utilityProviders = new ArrayList<>();
        this.serviceProviders = new ArrayList<>();
        this.resourcePool = new ResourcePool();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell getCell(int row, int col) {
        if (!isInBounds(row, col)) {
            return null;
        }
        return grid[row][col];
    }

    public Cell getCell(Position position) {
        if (position == null) {
            return null;
        }
        return getCell(position.getRow(), position.getCol());
    }

    public void setCell(int row, int col, Cell cell) {
        if (!isInBounds(row, col)) {
            return;
        }
        grid[row][col] = cell;

        if (cell instanceof Zone) {
            zones.add((Zone) cell);
        } else if (cell instanceof UtilityProvider) {
            utilityProviders.add((UtilityProvider) cell);
        } else if (cell instanceof ServiceProvider) {
            serviceProviders.add((ServiceProvider) cell);
        }
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public List<UtilityProvider> getUtilityProviders() {
        return utilityProviders;
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public ResourcePool getResourcePool() {
        return resourcePool;
    }
}