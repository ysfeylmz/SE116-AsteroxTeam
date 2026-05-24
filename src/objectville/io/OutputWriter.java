package objectville.io;

import objectville.cells.Commercial;
import objectville.cells.Housing;
import objectville.cells.Industrial;
import objectville.cells.Zone;
import objectville.enums.ResourceType;
import objectville.enums.ServiceType;
import objectville.enums.UtilityType;

public class OutputWriter {

    public OutputWriter() {
    }

    public void writeTickHeader(int tickNumber) {
        writeLine("Tick " + tickNumber);
    }

    public void writeServiceReceived(Zone zone, ServiceType service) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " received " + serviceName(service) + " service");
    }

    public void writeUtilityReceived(Zone zone, UtilityType utility, int amount) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " received " + amount + " " + utilityName(utility));
    }

    public void writeResourceReceived(Zone zone, ResourceType resource, int amount) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " received " + amount + " " + resourceName(resource));
    }

    public void writeProduction(Zone zone, ResourceType resource, int amount) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " generated " + amount + " " + resourceName(resource));
    }

    public void writeLevelUp(Zone zone, int fromLevel, int toLevel) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " levels up from " + fromLevel + " to " + toLevel);
    }

    public void writeLevelDown(Zone zone, int fromLevel, int toLevel) {
        writeLine(zoneDisplayName(zone) + " at " + zone.getPosition()
                + " levels down from " + fromLevel + " to " + toLevel);
    }

    public void writeLine(String line) {
        System.out.println(line);
    }

    protected String utilityName(UtilityType type) {
        switch (type) {
            case ELECTRICITY: return "electricity";
            case WATER:       return "water";
            case INTERNET:    return "internet";
            default:          return type.name().toLowerCase();
        }
    }

    protected String serviceName(ServiceType type) {
        switch (type) {
            case SECURITY:  return "security";
            case HEALTH:    return "health";
            case EDUCATION: return "education";
            default:        return type.name().toLowerCase();
        }
    }

    protected String resourceName(ResourceType type) {
        switch (type) {
            case POPULATION: return "population";
            case GOODS:      return "goods";
            case LIFESTYLE:  return "lifestyle";
            default:         return type.name().toLowerCase();
        }
    }

    protected String zoneDisplayName(Zone zone) {
        if (zone instanceof Housing) {
            return "House";
        } else if (zone instanceof Industrial) {
            return "Industrial";
        } else if (zone instanceof Commercial) {
            return "Commercial";
        }
        return zone.getClass().getSimpleName();
    }
}