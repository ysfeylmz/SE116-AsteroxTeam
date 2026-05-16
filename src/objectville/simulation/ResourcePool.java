package objectville.simulation;

import objectville.enums.ResourceType;

import java.util.EnumMap;
import java.util.Map;

public class ResourcePool {

    private final Map<ResourceType, Integer> totals = new EnumMap<>(ResourceType.class);

    public ResourcePool() {
        // TODO: implement
    }

    public void add(ResourceType type, int amount) {
        // TODO: implement
    }

    public int get(ResourceType type) {
        // TODO: implement
        return 0;
    }

    public void clear() {
        // TODO: implement
    }

    public int sharePerConsumer(ResourceType type, int numConsumers) {
        // TODO: implement
        return 0;
    }
}