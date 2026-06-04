package objectville.cells;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;
import objectville.interfaces.Connectable;
import objectville.simulation.Position;
import java.util.Map;
import java.util.HashMap;
public abstract class Zone extends Cell implements Connectable {

    protected int level;
    protected Map<UtilityType, Integer> utilitiesReceived;
    protected Map<ServiceType, Boolean> servicesReceived;

    public Zone(Position position) {
        super(position);
        this.level = 0;
        this.utilitiesReceived = new HashMap<>();
        this.servicesReceived = new HashMap<>();

        resetTickInputs();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= 0 && level <= 3) {
            this.level = level;
        }
    }

    public void resetTickInputs() {
        for (UtilityType u : UtilityType.values()) {
            utilitiesReceived.put(u, 0);
        }
        for (ServiceType s : ServiceType.values()) {
            servicesReceived.put(s, false);
        }
    }

    public void receiveUtility(UtilityType type, int amount) {
        int current = utilitiesReceived.getOrDefault(type, 0);
        utilitiesReceived.put(type, current + amount);
    }

    public void receiveService(ServiceType type) {
        servicesReceived.put(type, true);
    }

    public int getUtilityReceived(UtilityType type) {
        return utilitiesReceived.getOrDefault(type, 0);
    }

    public boolean hasService(ServiceType type) {
        return servicesReceived.getOrDefault(type, false);
    }


    public abstract boolean usesService(ServiceType type);
    public abstract void recomputeLevel();

    public abstract int getUtilityDemand(UtilityType type);
}