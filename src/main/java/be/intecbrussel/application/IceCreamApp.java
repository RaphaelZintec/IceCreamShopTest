package be.intecbrussel.application;

import be.intecbrussel.eatables.*;
import be.intecbrussel.sellers.IceCreamSalon;
import be.intecbrussel.sellers.IceCreamSeller;
import be.intecbrussel.sellers.PriceList;

public class IceCreamApp {
    public static void main(String[] args) {
        PriceList priceList = new PriceList(10,20,100);
        IceCreamSeller iceCreamSalon = new IceCreamSalon(priceList);

        Cone cone = iceCreamSalon.orderCone(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE});
        IceRocket iceRocket = iceCreamSalon.orderIceRocket();
        Magnum magnum = iceCreamSalon.orderMagnum(Magnum.MagnumType.ROMANTICSTRAWBERRIES);

        Eatable[] icecreams = new Eatable[]{cone, iceRocket, magnum};

        for (Eatable icecream : icecreams) {
            icecream.eat();
        }

        System.out.println("PROFIT: " + iceCreamSalon.getProfit());
    }
}
