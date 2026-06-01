package objectville.cells;

import objectville.interfaces.Connectable;
import objectville.simulation.Position;

public class Road extends Cell implements Connectable {

    public Road(Position position) {

        super(position);
    }

    @Override
    public char getSymbol() {

        return 'R';
    }
}