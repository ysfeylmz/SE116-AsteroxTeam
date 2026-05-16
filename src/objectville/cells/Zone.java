package objectville.cells;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;
import objectville.interfaces.Connectable;
import objectville.simulation.Position;
import java.util.Map;
public abstract class Zone extends Cell implements Connectable {

    protected int level;
    protected Map<UtilityType, Integer> utilitiesReceived;
    protected Map<ServiceType, Boolean> servicesReceived;

    public Zone(Position position) {
        // TODO:
        super(position);
    }

    public int getLevel() {
        // TODO:
        return 0;
    }

    public void setLevel(int level) {
        // TODO:
    }

    public void resetTickInputs() {
        // TODO:
    }

    public void receiveUtility(UtilityType type, int amount) {
        // TODO:
    }

    public void receiveService(ServiceType type) {
        // TODO:
    }

    public int getUtilityReceived(UtilityType type) {
        // TODO:
        return 0;
    }

    public boolean hasService(ServiceType type) {
        // TODO:
        return false;
    }

    public abstract void recomputeLevel();

    public abstract int getUtilityDemand(UtilityType type);
}