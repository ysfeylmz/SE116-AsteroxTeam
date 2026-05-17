package objectville.cells;
import objectville.enums.ServiceType;
import objectville.simulation.Position;
public class PoliceStation extends ServiceProvider{
  public static final int RADIUS=5;
  public PoliceStation(Position position){
      super(position);
  }@Override
    public char getSymbol(){
      //TODO:implement
        return 'F';
    }@Override
    public ServiceType getServiceType(){
      //TODO:implement
        return ServiceType.SECURITY;

    }@Override
    public int getRadius(){
      //TODO:implement
        return RADIUS;
    }


}