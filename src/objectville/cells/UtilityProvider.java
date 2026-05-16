package objectville.cells;

import objectville.enums.UtilityType;
import objectville.interfaces.Connectable;
import objectville.simulation.Position;

public abstract class UtilityProvider extends Cell implements Connectable {

    public static final int CAPACITY = 100;

    public UtilityProvider(Position position) {
        // TODO:
        super(position);
    }

    public abstract UtilityType getUtilityType();

    public int getCapacity() {
        // TODO:
        return CAPACITY;
    }
}