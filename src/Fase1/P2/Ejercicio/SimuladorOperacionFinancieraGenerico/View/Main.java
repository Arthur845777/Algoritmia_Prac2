package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.View;

import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Controlator.AccionesMain;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Controlator.Control;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model.OperacionFinanciera;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Control <OperacionFinanciera> operacionFinancieraControl = new Control<OperacionFinanciera>();

        try {
            int opcion;
            do {
                System.out.println("\n--- Menú de Operaciones Financieras ---");
                System.out.println("1. Agregar Financieras");
                System.out.println("2. Calcular Interés Simple");
                System.out.println("3. Calcular Interés Compuesto");
                System.out.println("4. Convertir Moneda");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        AccionesMain.addOperacion(scanner, operacionFinancieraControl);
                        break;

                    case 2:
                        AccionesMain.mostrarInteresSimple(scanner, operacionFinancieraControl);
                        break;

                    case 3:
                        AccionesMain.mostrarInteresCompuesto(scanner, operacionFinancieraControl);
                        break;

                    case 4:
                        AccionesMain.cambioModenada(scanner);
                        break;

                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 5);
            scanner.close();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}