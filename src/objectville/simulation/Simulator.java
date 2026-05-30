package objectville.simulation;
import objectville.cells.Cell;
import objectville.cells.Commercial;
import objectville.cells.Housing;
import objectville.cells.Industrial;
import objectville.cells.ServiceProvider;
import objectville.cells.UtilityProvider;
import objectville.io.OutputWriter;
import objectville.cells.Zone;
import objectville.enums.ResourceType;
import objectville.enums.UtilityType;
import objectville.interfaces.Connectable;
import objectville.interfaces.ResourceConsumer;
import objectville.interfaces.ResourceProducer;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
public class Simulator {
    private static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};
    private final City city;
    private final OutputWriter output;
    private int currentTick;
    private Set<Zone> servedZones = new HashSet<>();
    private int[] previousLevels = new int[0];
    public Simulator(City city, OutputWriter output) {
        this.city = city;
        this.output = output;
        this.currentTick = 0;
    }

    public void run(int numTicks) {
        for (int i = 0; i < numTicks; i++) {
            tick();
        }
    }

    public void tick() {
        currentTick++;
        output.writeTickHeader(currentTick);

        for (Zone zone : city.getZones()) {
            zone.resetTickInputs();
        }

        distributeServices();
        distributeUtilities();
        if (currentTick > 1) {
            distributeResources();
        }
        updateZones();
        accumulateProduction();
    }

    protected void distributeServices() {
        for (ServiceProvider provider : city.getServiceProviders()) {
            Position center = provider.getPosition();
            int radius = provider.getRadius();
            for (Zone zone : city.getZones()) {
                if (center.euclideanDistance(zone.getPosition()) <= radius) {
                    zone.receiveService(provider.getServiceType());
                    output.writeServiceReceived(zone, provider.getServiceType());
                }
            }
        }
    }

    protected void distributeUtilities() {
        UtilityType[] order = {UtilityType.INTERNET, UtilityType.WATER, UtilityType.ELECTRICITY};
        for (UtilityType type : order) {
            servedZones = new HashSet<>();
            for (UtilityProvider provider : city.getUtilityProviders()) {
                if (provider.getUtilityType() == type) {
                    bfsDistribute(provider);
                }
            }
        }
    }

    protected void bfsDistribute(UtilityProvider provider) {
        UtilityType type = provider.getUtilityType();
        int remaining = provider.getCapacity();

        Set<Position> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        Position start = provider.getPosition();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty() && remaining > 0) {
            Position pos = queue.poll();
            Cell cell = city.getCell(pos);
            if (cell == null) {
                continue;
            }

            if (cell instanceof Zone && !servedZones.contains(cell)) {
                Zone zone = (Zone) cell;
                int demand = zone.getUtilityDemand(type);
                int give = Math.min(demand, remaining);
                if (give > 0) {
                    zone.receiveUtility(type, give);
                    servedZones.add(zone);
                    output.writeUtilityReceived(zone, type, give);
                    remaining -= give;
                }
            }

            if (cell instanceof Connectable) {
                for (int k = 0; k < 8; k++) {
                    int nr = pos.getRow() + DR[k];
                    int nc = pos.getCol() + DC[k];
                    Position next = new Position(nr, nc);
                    if (visited.contains(next)) {
                        continue;
                    }
                    Cell neighbour = city.getCell(nr, nc);
                    if (neighbour instanceof Connectable) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }
    }

    protected void distributeResources() {
        ResourcePool pool = city.getResourcePool();
        List<Zone> zones = city.getZones();

        int populationConsumers = 0;
        int goodsConsumers = 0;
        int lifestyleConsumers = 0;
        for (Zone zone : zones) {
            if (zone instanceof Industrial || zone instanceof Commercial) {
                populationConsumers++;
            }
            if (zone instanceof Commercial) {
                goodsConsumers++;
            }
            if (zone instanceof Housing) {
                lifestyleConsumers++;
            }
        }

        int populationShare = pool.sharePerConsumer(ResourceType.POPULATION, populationConsumers);
        int goodsShare = pool.sharePerConsumer(ResourceType.GOODS, goodsConsumers);
        int lifestyleShare = pool.sharePerConsumer(ResourceType.LIFESTYLE, lifestyleConsumers);

        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                if (lifestyleShare > 0) {
                    ((ResourceConsumer) zone).receive(ResourceType.LIFESTYLE, lifestyleShare);
                    output.writeResourceReceived(zone, ResourceType.LIFESTYLE, lifestyleShare);
                }
            } else if (zone instanceof Industrial) {
                if (populationShare > 0) {
                    ((ResourceConsumer) zone).receive(ResourceType.POPULATION, populationShare);
                    output.writeResourceReceived(zone, ResourceType.POPULATION, populationShare);
                }
            } else if (zone instanceof Commercial) {
                if (populationShare > 0) {
                    ((ResourceConsumer) zone).receive(ResourceType.POPULATION, populationShare);
                    output.writeResourceReceived(zone, ResourceType.POPULATION, populationShare);
                }
                if (goodsShare > 0) {
                    ((ResourceConsumer) zone).receive(ResourceType.GOODS, goodsShare);
                    output.writeResourceReceived(zone, ResourceType.GOODS, goodsShare);
                }
            }
        }

        pool.clear();
    }

    protected void updateZones() {
        List<Zone> zones = city.getZones();
        previousLevels = new int[zones.size()];
        for (int i = 0; i < zones.size(); i++) {
            Zone zone = zones.get(i);
            previousLevels[i] = zone.getLevel();
            zone.recomputeLevel();
        }
    }

    protected void accumulateProduction() {
        ResourcePool pool = city.getResourcePool();
        List<Zone> zones = city.getZones();
        for (int i = 0; i < zones.size(); i++) {
            Zone zone = zones.get(i);
            ResourceType produced = producedResource(zone);
            int amount = 0;
            if (produced != null) {
                amount = ((ResourceProducer) zone).produce(produced);
                output.writeProduction(zone, produced, amount);
                pool.add(produced, amount);
            }

            int oldLevel = previousLevels[i];
            int newLevel = zone.getLevel();
            if (newLevel > oldLevel) {
                output.writeLevelUp(zone, oldLevel, newLevel);
            } else if (newLevel < oldLevel) {
                output.writeLevelDown(zone, oldLevel, newLevel);
            }
        }
    }

    private ResourceType producedResource(Zone zone) {
        if (zone instanceof Housing) {
            return ResourceType.POPULATION;
        } else if (zone instanceof Industrial) {
            return ResourceType.GOODS;
        } else if (zone instanceof Commercial) {
            return ResourceType.LIFESTYLE;
        }
        return null;
    }


    public int getCurrentTick() {

        return currentTick;
    }

    public City getCity() {

        return city;
    }
}