package objectville.io;

import objectville.cells.Zone;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;

public class OutputWriter {

    public OutputWriter() {
        // TODO: implement
    }

    public void writeTickHeader(int tickNumber) {
        // TODO: implement
    }

    public void writeServiceReceived(Zone zone, ServiceType service) {
        // TODO: implement
    }

    public void writeUtilityReceived(Zone zone, UtilityType utility, int amount) {
        // TODO: implement
    }

    public void writeLine(String line) {
        // TODO: implement
    }

    protected String utilityName(UtilityType type) {
        // TODO: implement
        return "";
    }

    protected String serviceName(ServiceType type) {
        // TODO: implement
        return "";
    }

    protected String zoneDisplayName(Zone zone) {
        // TODO: implement
        return "";
    }
}