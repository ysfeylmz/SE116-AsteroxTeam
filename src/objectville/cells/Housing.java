package objectville.cells;

import objectville.enums.ResourceType;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;
import objectville.interfaces.ResourceConsumer;
import objectville.interfaces.ResourceProducer;
import objectville.simulation.Position;

public class Housing extends Zone implements ResourceProducer, ResourceConsumer {

    private int lifestyleReceived;
    private int lastOutput;

    public Housing(Position position) {
        super(position);
        this.lifestyleReceived = 0;
        this.lastOutput = 0;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    @Override
    public void recomputeLevel() {
        int m = Math.min(getUtilityReceived(UtilityType.ELECTRICITY),
                Math.min(getUtilityReceived(UtilityType.WATER), getUtilityReceived(UtilityType.INTERNET)));

        if (m == 0) {
            setLevel(0);
            return;
        }

        boolean hasAllServices = hasService(ServiceType.SECURITY) &&
                hasService(ServiceType.HEALTH) &&
                hasService(ServiceType.EDUCATION);

        if (level == 0) {
            setLevel(1);
        }
        else if (level == 1) {
            if (hasAllServices) {
                setLevel(2);
            }
        }
        else if (level == 2) {
            if (!hasAllServices) {
                setLevel(1);
            } else if (lifestyleReceived > 0) {
                setLevel(3);
            }
        }
        else if (level == 3) {
            if (!hasAllServices || lifestyleReceived == 0) {
                setLevel(2);
            }
        }
    }

    @Override
    public int getUtilityDemand(UtilityType type) {
        return Math.max(1, lastOutput);
    }

    @Override
    public int produce(ResourceType type) {
        if (type != ResourceType.POPULATION) {
            return 0;
        }

        int m = Math.min(getUtilityReceived(UtilityType.ELECTRICITY),
                Math.min(getUtilityReceived(UtilityType.WATER), getUtilityReceived(UtilityType.INTERNET)));

        int output = 0;

        if (level == 0) {
            output = 0;
        } else if (level == 1) {
            output = m;
        } else if (level == 2) {
            output = 2 * m;
        } else if (level == 3) {
            output = (2 * m) + lifestyleReceived;
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
        if (type == ResourceType.LIFESTYLE) {
            this.lifestyleReceived += amount;
        }
    }
    @Override
    public void resetTickInputs() {
        super.resetTickInputs();
        this.lifestyleReceived = 0;
}
}