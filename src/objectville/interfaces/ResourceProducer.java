package objectville.interfaces;

import objectville.enums.ResourceType;

public interface ResourceProducer {

    int produce(ResourceType type);
}