package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model;

public class Operador <T extends Number>{

    public static double interesSimple(OperacionFinanciera obj) {
        double monto = obj.getMonto().doubleValue();
        double tasaInteres = obj.getTasaInteres().doubleValue();
        int plazo = obj.getPlazo();

        return monto * (1 + tasaInteres * plazo);
    }

    public static double interesCompuesto(OperacionFinanciera obj) {
        double capital = obj.getMonto().doubleValue();
        double tasaInteres = obj.getTasaInteres().doubleValue();
        double plazo = obj.getPlazo();

        return capital * (Math.pow((1 + (1/tasaInteres)), plazo));
    }
}