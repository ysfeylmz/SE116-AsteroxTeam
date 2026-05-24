package objectville.cells;

import objectville.simulation.Position;

public abstract class Cell {

    protected Position position;

    public Cell(Position position) {
        this.position=position;
    }

    public abstract char getSymbol();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        return position;
    }

    public void update() {


    }
}