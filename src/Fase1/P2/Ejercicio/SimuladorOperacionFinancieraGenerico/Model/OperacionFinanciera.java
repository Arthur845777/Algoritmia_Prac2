package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model;

public class OperacionFinanciera <T extends Number> {
    private T monto;
    private T tasaInteres;
    private int plazo;

    public OperacionFinanciera() {super();}
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
}