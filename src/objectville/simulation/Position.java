package objectville.simulation;

import java.util.Objects;

public final class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {

        this.row = row;
        this.col = col;
    }

    public int getRow() {

        return row;
    }

    public int getCol() {

        return col;
    }

    public int manhattanDistance(Position other) {
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);

    }


    public double euclideanDistance(Position other) {
        int dr = this.row - other.row;
        int dc = this.col - other.col;
        return Math.sqrt(dr * dr + dc * dc);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o ){
            return true;
        }
        if(!(o instanceof Position)) {
            return false;
        }
        Position other = (Position) o;

        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(row, col);
    }

    @Override
    public String toString() {

        return "(" + row + "," + col + ")";
    }
}