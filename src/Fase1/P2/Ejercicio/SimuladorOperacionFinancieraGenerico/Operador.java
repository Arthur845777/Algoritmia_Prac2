package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico;

public class Operador{

    public static <T extends Number> double interesSimple(OperacionFinanciera<T> obj) {
        double monto = obj.getMonto().doubleValue();
        double tasaInteres = obj.getTasaInteres().doubleValue();
        int plazo = obj.getPlazo();

        return monto * (1 + tasaInteres * plazo);
    }

    public static <T extends Number> double interesCompuesto(OperacionFinanciera<T> obj) {
        double capital = obj.getMonto().doubleValue();
        double tasaInteres = obj.getTasaInteres().doubleValue();
        double plazo = obj.getPlazo();

        return capital * (Math.pow((1 + (1/tasaInteres)), plazo));
    }

    public static double convertirMoneda(double monto, String monedaActual, String monedaCambio) {
        double tasaConversion = obtenerTasaConversion(monedaActual, monedaCambio);
        return monto * tasaConversion;
    }

    private static double obtenerTasaConversion(String origen, String destino) {
        // cambiar al modo de seleccion simple, ose lo que hemos hecho en el primer trabajo
        if (origen.equals("soles") && destino.equals("dolares")) return 0.27;
        if (origen.equals("dolares") && destino.equals("soles")) return 3.7;
        if (origen.equals("euros") && destino.equals("soles")) return 4.0;
        if (origen.equals("soles") && destino.equals("euros")) return 0.25;
        if (origen.equals("euros") && destino.equals("dolares")) return 1.08;
        if (origen.equals("dolares") && destino.equals("euros")) return 1.08;
        return 1; // si son iguales, no hay cam io
    }

}
