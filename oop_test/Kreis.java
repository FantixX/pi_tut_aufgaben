package oop_test;

public class Kreis {

    private double x;
    private double y;
    private double r;

    public Kreis(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Kreis(Kreis k) {
        this(k.x, k.y, k.r);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    void setRadius(double r) {
        this.r = r;
    }

    double berechneFläche() {
        return Math.PI * r * r;
    }

    double berechneUmfang() {
        return 2 * Math.PI * r;
    }
}
