package objectville.simulation;

import objectville.enums.ResourceType;

import java.util.EnumMap;
import java.util.Map;

public class ResourcePool {

    private final Map<ResourceType, Integer> totals = new EnumMap<>(ResourceType.class);

    public ResourcePool() {
        clear();
    }

    public void add(ResourceType type, int amount) {
        if (type == null || amount <= 0) {
            return;
        }
        totals.put(type, totals.getOrDefault(type, 0) + amount);
    }

    public int get(ResourceType type) {
        return totals.getOrDefault(type, 0);
    }

    public void clear() {
        for (ResourceType type : ResourceType.values()) {
            totals.put(type, 0);
        }
    }

    public int sharePerConsumer(ResourceType type, int numConsumers) {
        if (numConsumers <= 0) {
            return 0;
        }
        return get(type) / numConsumers;
    }
}