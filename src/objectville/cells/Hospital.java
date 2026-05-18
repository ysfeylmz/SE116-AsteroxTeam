package objectville.cells;

import objectville.enums.ServiceType;
import objectville.simulation.Position;

public class Hospital extends ServiceProvider {

    public static final int RADIUS = 3;

    public Hospital(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'D';
    }

    @Override
    public ServiceType getServiceType() {
        // TODO: implement
        return ServiceType.HEALTH;
    }

    @Override
    public int getRadius() {
        // TODO: implement
        return RADIUS;
    }
}