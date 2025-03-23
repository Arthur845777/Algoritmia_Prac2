package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model;

public class MonedaCambio {
    public static double convertirMoneda(double monto, String monedaActual, String monedaCambio) {
        double tasaConversion = obtenerTasaConversion(monedaActual, monedaCambio);
        return monto * tasaConversion;
    }

    private static double obtenerTasaConversion(String origen, String destino) {
        if (origen.equalsIgnoreCase("soles") && destino.equalsIgnoreCase("dolares")) return 0.27;
        if (origen.equalsIgnoreCase("dolares") && destino.equalsIgnoreCase("soles")) return 3.7;
        if (origen.equalsIgnoreCase("euros") && destino.equalsIgnoreCase("soles")) return 4.0;
        if (origen.equalsIgnoreCase("soles") && destino.equalsIgnoreCase("euros")) return 0.25;
        if (origen.equalsIgnoreCase("euros") && destino.equalsIgnoreCase("dolares")) return 1.08;
        if (origen.equalsIgnoreCase("dolares") && destino.equalsIgnoreCase("euros")) return 1.08;
        return 1;
    }

}

