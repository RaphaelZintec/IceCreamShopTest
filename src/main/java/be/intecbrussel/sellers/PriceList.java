package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Magnum;

public class PriceList {
    private double ballPrice;
    private double rocketPrice;
    private double magnumStandardPrice;

    public PriceList() {
        this.ballPrice = 1;
        this.rocketPrice = 2;
        this.magnumStandardPrice = 4;
    }

    public PriceList(double ballPrice, double rocketPrice, double magnumStandardPrice) {
        this.ballPrice = ballPrice;
        this.rocketPrice = rocketPrice;
        this.magnumStandardPrice = magnumStandardPrice;
    }

    public void setBallPrice(double ballPrice) {
        this.ballPrice = ballPrice;
    }

    public void setRocketPrice(double rocketPrice) {
        this.rocketPrice = rocketPrice;
    }

    public void setMagnumStandardPrice(double magnumStandardPrice) {
        this.magnumStandardPrice = magnumStandardPrice;
    }

    public double getBallPrice() {
        return ballPrice;
    }

    public double getRocketPrice() {
        return rocketPrice;
    }

    public double getMagnumPrice(Magnum.MagnumType type) {
        switch (type) {
            case ALPINENUTS:
                return magnumStandardPrice * 1.5;
            case MILKCHOCOLATE:
                return magnumStandardPrice * 1.1;
            case WHITECHOCOLATE:
                return  magnumStandardPrice * 1.4;
            default:
                return magnumStandardPrice * 2;
        }

    }
}
