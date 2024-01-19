package be.intecbrussel.sellers;

public class Stock {
    private int iceRockets;
    private int cones;
    private int balls;
    private int magni;

    public Stock(int iceRockets, int cones, int balls, int magni) {
        this.iceRockets = iceRockets;
        this.cones = cones;
        this.balls = balls;
        this.magni = magni;
    }

    public int getBalls() {
        return balls;
    }

    public int getCones() {
        return cones;
    }

    public int getIceRockets() {
        return iceRockets;
    }

    public int getMagni() {
        return magni;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public void setCones(int cones) {
        this.cones = cones;
    }

    public void setIceRockets(int iceRockets) {
        this.iceRockets = iceRockets;
    }

    public void setMagni(int magni) {
        this.magni = magni;
    }
}

