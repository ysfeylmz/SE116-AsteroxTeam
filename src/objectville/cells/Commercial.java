package objectville.cells;

import objectville.enums.ResourceType;
import objectville.enums.UtilityType;
import objectville.interfaces.ResourceConsumer;
import objectville.interfaces.ResourceProducer;
import objectville.simulation.Position;
import objectville.enums.ServiceType;

public class Commercial extends Zone implements ResourceProducer, ResourceConsumer {

    private int populationReceived;
    private int goodsReceived;

    public Commercial(Position position) {
        super(position);
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    @Override
    public void recomputeLevel() {
        boolean hasElectricity = getUtilityReceived(UtilityType.ELECTRICITY) >0;
        boolean hasWater = getUtilityReceived(UtilityType.WATER) >0;
        boolean hasInternet= getUtilityReceived(UtilityType.INTERNET) >0;
        boolean hasPopulation = this.populationReceived > 0;
        boolean hasGoods  =this.goodsReceived >0;
        //depolardaki kaynakları kontrol ettim

        if (!hasElectricity || !hasWater || !hasInternet || !hasPopulation || !hasGoods){

            this.level = 0;
            return;
            //bu 5'inden biri bile yoksa bina level 0 olur bu yuzden ileriye bakmaya gerek yok.

        }

        this.level = 1; //if bloguna girmezse herşey var ve level 1.

        if(hasService(ServiceType.SECURITY)){
            this.level = 2; //güvenlik varsa eğer 2.level.

            if(this.populationReceived >1 && this.goodsReceived > 1) {
                this.level =3; //daha fazla varsa level 3.
            }


        }


    }

    @Override
    public int getUtilityDemand(UtilityType type) {
        // TODO: implement
        return 0;
    }

    @Override
    public int produce(ResourceType type) {
        if(type != ResourceType.LIFESTYLE){

            return 0; // eğer kaynak lifestyle değilse üretme 0 dondur.

        }
        if(this.level ==0){
            return 0;

        }

        int electricity = getUtilityReceived(UtilityType.ELECTRICITY);
        int water = getUtilityReceived(UtilityType.WATER);
        int internet = getUtilityReceived(UtilityType.INTERNET);
        //kaynakları int atadım.

        int m = Math.min(electricity, Math.min(water, internet));
        //min değeri bulma.

        return this.level * m;


    }

    @Override
    public int demand(ResourceType type) {
        // TODO: implement
        return 0;
    }

    @Override
    public void receive(ResourceType type, int amount) {
        if(type == ResourceType.POPULATION){
            this.populationReceived = populationReceived + amount;

        } //Nüfüs verirse nüfusu ekle.

        else if(type == ResourceType.GOODS){
            this.goodsReceived = goodsReceived + amount;
        } //Ürün verirse onu ekle.
    }
}
