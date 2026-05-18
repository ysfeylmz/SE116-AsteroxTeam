package objectville.cells;

import objectville.enums.UtilityType;
import objectville.simulation.Position;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'P';
    }

    @Override
    public UtilityType getUtilityType() {
        // TODO: implement
        return UtilityType.ELECTRICITY;
    }
}
