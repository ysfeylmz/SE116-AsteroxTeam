package objectville.cells;
import objectville.enums.UtilityType;
import objectville.simulation.Position;
public class InternetHub  extends UtilityProvider{
    public InternetHub(Position position){
        super(position);
    }@Override
    public char getSymbol(){

        return 'T';
    }@Override
        public UtilityType getUtilityType(){

        return UtilityType.INTERNET;
    }
}
