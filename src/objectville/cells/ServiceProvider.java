package objectville.cells;
import objectville.enums.ServiceType;
import objectville.simulation.Position;
public abstract class ServiceProvider extends Cell {

    public ServiceProvider(Position position) {
        // TODO:
        super(position);
    }

    public abstract ServiceType getServiceType();

    public abstract int getRadius();
}