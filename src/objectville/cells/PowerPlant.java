package objectville.cells;

import objectville.enums.UtilityType;
import objectville.simulation.Position;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(Position position) {

        super(position);
    }

    @Override
    public char getSymbol() {

        return 'P';
    }

    @Override
    public UtilityType getUtilityType() {

        return UtilityType.ELECTRICITY;
    }
}
