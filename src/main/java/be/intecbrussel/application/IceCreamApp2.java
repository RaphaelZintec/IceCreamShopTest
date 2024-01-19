package be.intecbrussel.application;

import be.intecbrussel.eatables.*;
import be.intecbrussel.sellers.IceCreamCar;
import be.intecbrussel.sellers.IceCreamSeller;
import be.intecbrussel.sellers.PriceList;
import be.intecbrussel.sellers.Stock;

public class IceCreamApp2 {
    public static void main(String[] args) {
        PriceList priceList = new PriceList(1,2,3);
        Stock stock = new Stock(2,2,2,2);

        IceCreamSeller iceCreamCar = new IceCreamCar(priceList, stock);

        Cone cone = iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY, Cone.Flavor.STRAWBERRY});
        IceRocket iceRocket = iceCreamCar.orderIceRocket();
        IceRocket iceRocket2 = iceCreamCar.orderIceRocket();
        IceRocket iceRocket3 = iceCreamCar.orderIceRocket();
        IceRocket iceRocket4 = iceCreamCar.orderIceRocket();
        IceRocket iceRocket5 = iceCreamCar.orderIceRocket();
        Magnum magnum = iceCreamCar.orderMagnum(Magnum.MagnumType.MILKCHOCOLATE);

        Eatable[] eatables = new Eatable[]{cone, iceRocket, iceRocket2, iceRocket3, iceRocket4, iceRocket5, magnum};

        for (Eatable eatable : eatables) {
            if (eatable != null) {
                eatable.eat();
            }
        }

        System.out.println("PROFIT: " + iceCreamCar.getProfit());

    }
}
