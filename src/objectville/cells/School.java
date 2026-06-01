package objectville.cells;
import objectville.enums.ServiceType;
import objectville.simulation.Position;
public class School extends ServiceProvider{
    public static final int RADIUS=4;
    public School(Position position){

        super(position);

    }@Override
    public char getSymbol(){

        return 'S';
    }@Override
    public ServiceType getServiceType(){

        return ServiceType.EDUCATION;
    }@Override
    public int getRadius(){

        return RADIUS;
    }
}