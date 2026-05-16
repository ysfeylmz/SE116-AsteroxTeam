package objectville.cells;

import objectville.simulation.Position;

public class EmptyCell extends Cell {

    public EmptyCell(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'E';
    }
}