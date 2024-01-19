package be.intecbrussel.sellers;

import be.intecbrussel.eatables.*;

public class IceCreamSalon implements IceCreamSeller{
    private PriceList priceList;
    private double totalProfit;

    public IceCreamSalon(PriceList priceList) {
        this.priceList = priceList;
    }

    @Override
    public Cone orderCone(Cone.Flavor[] balls) {
        Cone cone = new Cone(balls);
        totalProfit += priceList.getBallPrice() * balls.length * 0.25;
        return cone;
    }

    @Override
    public IceRocket orderIceRocket() {
        IceRocket iceRocket = new IceRocket();
        totalProfit += priceList.getRocketPrice() * 0.20;
        return iceRocket;
    }

    @Override
    public Magnum orderMagnum(Magnum.MagnumType type) {

        Magnum magnum = new Magnum(type);
        totalProfit += priceList.getMagnumPrice(type) * 0.01;
        return magnum;
    }

    @Override
    public double getProfit() {
        return totalProfit;
    }

    @Override
    public String toString() {
        return "IceCreamSalon{" +
                "priceList=" + priceList +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
