package objectville.cells;

import objectville.enums.ServiceType;
import objectville.simulation.Position;

public class Hospital extends ServiceProvider {

    public static final int RADIUS = 3;

    public Hospital(Position position) {

        super(position);
    }

    @Override
    public char getSymbol() {

        return 'D';
    }

    @Override
    public ServiceType getServiceType() {

        return ServiceType.HEALTH;
    }

    @Override
    public int getRadius() {

        return RADIUS;
    }
}