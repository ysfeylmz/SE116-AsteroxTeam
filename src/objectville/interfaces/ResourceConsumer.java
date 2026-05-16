package objectville.interfaces;

import objectville.enums.ResourceType;

public interface ResourceConsumer {

    int demand(ResourceType type);

    void receive(ResourceType type, int amount);
}