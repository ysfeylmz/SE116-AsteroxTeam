package objectville.simulation;

import objectville.cells.Cell;
import objectville.cells.Road;
import objectville.cells.UtilityProvider;
import objectville.cells.Zone;
import objectville.enums.UtilityType;
import objectville.io.OutputWriter;

import java.util.*;

public class Simulator {

    private final City city;
    private final OutputWriter output;
    private int currentTick;

    public Simulator(City city, OutputWriter output) {
        this.city = city;
        this.output = output;
        this.currentTick=0;
    }

    public void run(int numTicks) {
        // TODO: implement
    }

    public void tick() {
        // TODO: implement
    }

    protected void distributeServices() {
for(UtilityProvider provider :city.getUtilityProviders()){
    bfsDistribute(provider);
}
    }

    protected void distributeUtilities() {
        // TODO: implement
    }

    protected void bfsDistribute(UtilityProvider provider) {
        UtilityType type=provider.getUtilityType();
        int remainingCapacity=provider.getCapacity();
        Queue<Position> queue =new LinkedList<>();
        Set<Position> visited =new HashSet<>();
        Position startPos=provider.getPosition();
        queue.add(startPos);
        visited.add(startPos);
        int[][] directions ={{-1,0},{1,0},{0,-1},{0,1}};  //way matris=> up,down,left,right
        while (!queue.isEmpty()&& remainingCapacity>0){
            Position currentPos=queue.poll();
            for(int[] dir:directions){
                int nextRow=currentPos.getRow()+dir[0];
                int nextCol=currentPos.getCol()+dir[1];
                if(!city.isInBounds(nextRow,nextCol)) continue;
                Position nextPos=new Position(nextRow,nextCol);
                if(visited.contains(nextPos)) continue;
                Cell neighborCell=city.getCell(nextRow,nextCol);
                if(neighborCell==null) continue;
                if(neighborCell instanceof Road){
                    visited.add(nextPos);
                    queue.add(nextPos);
                }else if(neighborCell instanceof Zone){
                    Zone zone =(Zone) neighborCell;
                    int demand =zone.getUtilityDemand(type);
                    if(demand>0){
                        int amountToGive=Math.min(demand,remainingCapacity);
                        if(amountToGive>0){
                            zone.receiveUtility(type,amountToGive);
                            remainingCapacity-=amountToGive;
                            output.writeUtilityReceived(zone,type,amountToGive);
                        }
                    }
                    visited.add(nextPos);
                }
            }
        }
    }

    protected void distributeResources() {
        // TODO: implement
    }

    protected void updateZones() {
        // TODO: implement
    }

    protected void accumulateProduction() {
        // TODO: implement
    }

    public int getCurrentTick() {
        // TODO: implement
        return currentTick;
    }

    public City getCity() {
        // TODO: implement
        return city;
    }
}