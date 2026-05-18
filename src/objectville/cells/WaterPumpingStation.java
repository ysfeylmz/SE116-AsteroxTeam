package objectville.cells;

import objectville.enums.UtilityType;
import objectville.simulation.Position;

public class WaterPumpingStation extends UtilityProvider {

    public WaterPumpingStation(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'W';
    }

    @Override
    public UtilityType getUtilityType() {
        // TODO: implement
        return UtilityType.WATER;
    }
}