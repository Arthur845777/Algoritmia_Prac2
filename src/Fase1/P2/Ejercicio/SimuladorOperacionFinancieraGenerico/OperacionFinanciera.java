package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico;

public class OperacionFinanciera <T extends Number> {
    private T monto;
    private T tasaInteres;
    private int plazo;

    public OperacionFinanciera() {}
    public OperacionFinanciera(T monto, T tasaInteres, int plazo) {
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
    }

    public T getMonto() {
        return monto;
    }
    public T getTasaInteres() {
        return tasaInteres;
    }
    public int getPlazo() {
        return plazo;
    }

    public void setMonto(T monto) {
        this.monto = monto;
    }
    public void setTasaInteres(T tasaInteres) {
        this.tasaInteres = tasaInteres;
    }
    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }
}
