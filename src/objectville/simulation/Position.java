package objectville.simulation;

import java.util.Objects;

public final class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        // TODO: implement
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        // TODO: implement
        return row;
    }

    public int getCol() {
        // TODO: implement
        return col;
    }

    public int manhattanDistance(Position other) {
        // TODO: implement
        return 0;
    }

    public double euclideanDistance(Position other) {
        // TODO: implement
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        // TODO: implement
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: implement
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        // TODO: implement
        return "(" + row + "," + col + ")";
    }
}