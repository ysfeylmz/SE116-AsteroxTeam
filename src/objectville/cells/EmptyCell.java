package objectville.cells;

import objectville.simulation.Position;

public class EmptyCell extends Cell {

    public EmptyCell(Position position) {

        super(position);
    }

    @Override
    public char getSymbol() {

        return 'E';
    }
}