package Fase1.P1.Actividad.Rectangulos;

// Explciacion 4.1
public class Coordenada {
    private double x;
    private double y;

    public Coordenada() {}
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Coordenada(Coordenada c) {
        this.x = c.x;
        this.y = c.y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return  "(" + x + ";" + y +")";
    }
}