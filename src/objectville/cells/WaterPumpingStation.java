package objectville.cells;

import objectville.enums.UtilityType;
import objectville.simulation.Position;

public class WaterPumpingStation extends UtilityProvider {

    public WaterPumpingStation(Position position) {

        super(position);
    }

    @Override
    public char getSymbol() {

        return 'W';
    }

    @Override
    public UtilityType getUtilityType() {

        return UtilityType.WATER;
    }
}