package objectville.cells;

import objectville.enums.ResourceType;
import objectville.enums.UtilityType;
import objectville.interfaces.ResourceConsumer;
import objectville.interfaces.ResourceProducer;
import objectville.simulation.Position;

public class Housing extends Zone implements ResourceProducer, ResourceConsumer {

    private int lifestyleReceived;

    public Housing(Position position) {
        // TODO: implement
        super(position);
    }

    @Override
    public char getSymbol() {
        // TODO: implement
        return 'H';
    }

    @Override
    public void recomputeLevel() {
        // TODO: implement
    }

    @Override
    public int getUtilityDemand(UtilityType type) {
        // TODO: implement
        return 0;
    }

    @Override
    public int produce(ResourceType type) {
        // TODO: implement
        return 0;
    }

    @Override
    public int demand(ResourceType type) {
        // TODO: implement
        return 0;
    }

    @Override
    public void receive(ResourceType type, int amount) {
        // TODO: implement
    }
}