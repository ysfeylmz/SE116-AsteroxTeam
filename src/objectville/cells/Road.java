package objectville.cells;

import objectville.interfaces.Connectable;
import objectville.simulation.Position;

public class Road extends Cell implements Connectable {

    public Road(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'R';
    }
}