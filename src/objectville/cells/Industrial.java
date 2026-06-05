package objectville.cells;

import objectville.enums.ResourceType;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;
import objectville.interfaces.ResourceConsumer;
import objectville.interfaces.ResourceProducer;
import objectville.simulation.Position;

public class Industrial extends Zone implements ResourceProducer, ResourceConsumer {

    private int populationReceived;
    private int lastOutput;

    public Industrial(Position position) {
        super(position);
        this.populationReceived = 0;
        this.lastOutput = 0;
    }

    @Override
    public char getSymbol() {
        return 'I';
    }

    @Override
    public boolean usesService(ServiceType type) {
        return type == ServiceType.SECURITY;
    }

    @Override
    public void recomputeLevel() {
        int m = Math.min(getUtilityReceived(UtilityType.ELECTRICITY),
                getUtilityReceived(UtilityType.WATER));

        if (m == 0) {
            setLevel(0);
            return;
        }
        int desired =0;

        if(populationReceived > 0 ){
            desired = 1;

            if (hasService(ServiceType.SECURITY)){
                desired = 2;

                if (populationReceived > 0){
                    desired = 3;

                }

            }

        }


        if (level < desired) {
            setLevel(level + 1);
        } else if (level > desired) {
            setLevel(level - 1);
        }
    }

    @Override
    public int getUtilityDemand(UtilityType type) {
        if (type == UtilityType.ELECTRICITY || type == UtilityType.WATER) {
            return Math.max(1, lastOutput);
        }
        return 0;
    }

    @Override
    public int produce(ResourceType type) {
        if (type != ResourceType.GOODS) {
            return 0;
        }

        int m = Math.min(getUtilityReceived(UtilityType.ELECTRICITY),
                getUtilityReceived(UtilityType.WATER));

        int output = 0;

        if (level == 0) {
            output = 0;
        } else if (level == 1) {
            output = m;
        } else if (level == 2) {
            output = 2 * m;
        } else if (level == 3) {
            output = (2 * m) + populationReceived;
        }

        this.lastOutput = output;
        return output;
    }

    @Override
    public int demand(ResourceType type) {
        return 0;
    }

    @Override
    public void receive(ResourceType type, int amount) {
        if (type == ResourceType.POPULATION) {
            this.populationReceived += amount;
        }
    }

    @Override
    public void resetTickInputs() {
        super.resetTickInputs();
        this.populationReceived = 0;
    }
}