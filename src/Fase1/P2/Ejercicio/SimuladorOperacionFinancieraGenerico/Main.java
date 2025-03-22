package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico;

public class Main {
    public static void main(String[] args) {
        try {
            OperacionFinanciera<Double> operacion = new OperacionFinanciera<>(1000.0, 0.05, 2);

            double interesSimple = Operador.interesSimple(operacion);
            double interesCompuesto = Operador.interesCompuesto(operacion);
            double montoConvertido = Operador.convertirMoneda(1000, "soles", "dolares");

            System.out.println("Interés Simple: " + interesSimple);
            System.out.println("Interés Compuesto: " + interesCompuesto);
            System.out.println("Conversión de 1000 soles a dólares: " + montoConvertido);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

