package be.intecbrussel.eatables;

public class Magnum implements Eatable{

    public enum MagnumType {
        MILKCHOCOLATE,
        WHITECHOCOLATE,
        BLACKCHOCOLATE,
        ALPINENUTS,
        ROMANTICSTRAWBERRIES;
    }
    private MagnumType type;

    public Magnum() {
        this.type = MagnumType.MILKCHOCOLATE;
    }

    public Magnum(MagnumType type) {
        this.type = type;
    }

    @Override
    public void eat() {
        System.out.println("We are eating a " + type + " magnum");
    }

    public MagnumType getType() {
        return type;
    }


}
