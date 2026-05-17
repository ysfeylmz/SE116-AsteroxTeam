package objectville.cells;
import objectville.enums.ServiceType;
import objectville.simulation.Position;
public class School extends ServiceProvider{
    public static final int RADIUS=4;
    public School(Position position){
        //TODO:implement
        super(position);

    }@Override
    public char getSymbol(){
        //TODO:implement
        return 'S';
    }@Override
    public ServiceType getServiceType(){
        //TODO:implement
        return ServiceType.EDUCATION;
    }@Override
    public int getRadius(){
        //TODO:implement
        return RADIUS;
    }
}