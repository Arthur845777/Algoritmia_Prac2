package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese el capital inicial: ");
            double capital = scanner.nextDouble();
            System.out.print("Ingrese la tasa de interés (en decimal, ej: 0.05 para 5%): ");
            double tasa = scanner.nextDouble();
            System.out.print("Ingrese el tiempo en años: ");
            int tiempo = scanner.nextInt();

            OperacionFinanciera<Double> operacion = new OperacionFinanciera<>(capital, tasa, tiempo);

            int opcion;
            do {
                System.out.println("\n--- Menú de Operaciones Financieras ---");
                System.out.println("1. Calcular Interés Simple");
                System.out.println("2. Calcular Interés Compuesto");
                System.out.println("3. Convertir Moneda");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        double interesSimple = Operador.interesSimple(operacion);
                        System.out.println("Interés Simple: " + interesSimple);
                        break;
                    case 2:
                        double interesCompuesto = Operador.interesCompuesto(operacion);
                        System.out.println("Interés Compuesto: " + interesCompuesto);
                        break;
                    case 3:
                        System.out.print("Ingrese cantidad a convertir: ");
                        double cantidad = scanner.nextDouble();
                        System.out.print("Ingrese la moneda de origen: ");
                        String origen = scanner.next();
                        System.out.print("Ingrese la moneda de destino: ");
                        String destino = scanner.next();
                        double montoConvertido = Operador.convertirMoneda(cantidad, origen, destino);
                        System.out.println("Conversión de " + cantidad + " " + origen + " a " + destino + ": " + montoConvertido);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
