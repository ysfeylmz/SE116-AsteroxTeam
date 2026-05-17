package objectville.cells;
import objectville.enums.UtilityType;
import objectville.simulation.Position;
public class InternetHub  extends UtilityProvider{
    public InternetHub(Position position){
        //TODO:implement
        super(position);
    }@Override
    public char getSymbol(){
        //TODO:implement
        return 'T';
    }@Override
        public UtilityType getUtilityType(){
        //TODO:implement
        return UtilityType.INTERNET;
    }
}
