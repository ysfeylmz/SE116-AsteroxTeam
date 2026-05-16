package objectville.cells;

import objectville.simulation.Position;

public abstract class Cell {

    protected Position position;

    public Cell(Position position) {
        // TODO: implement
    }

    public abstract char getSymbol();

    public Position getPosition() {
        // TODO: implement
        return null;
    }

    public void setPosition(Position position) {
        // TODO: implement
    }

    public void update() {
        // TODO: implement
    }
}