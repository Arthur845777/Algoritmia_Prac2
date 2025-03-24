package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Controlator;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model.MonedaCambio;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model.OperacionFinanciera;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model.Operador;

import java.util.Scanner;

public class AccionesMain {

    public static void mostrarInteresSimple(Scanner sc,Control oFP){
        System.out.println(oFP);
        int index = seleccionarOperacion(sc, oFP);
        OperacionFinanciera operacion = (OperacionFinanciera) oFP.get(index);
        System.out.println(" El interes simple de tu operacion es: " + Operador.interesSimple(operacion));
    }

    public static void mostrarInteresCompuesto(Scanner sc,Control oFP){
        System.out.println(oFP);
        int index = seleccionarOperacion(sc, oFP);
        OperacionFinanciera operacion = (OperacionFinanciera) oFP.get(index);
        System.out.println(" El interes copmuesto de tu operacion es: " + Operador.interesCompuesto(operacion));
    }

    public static void cambioModenada(Scanner sc){
        System.out.println("Modedas disponibles: DOLARES - SOLES - EUROS");

        System.out.print("Ingrese cantidad a convertir: ");
        double cantidad = sc.nextDouble();

        System.out.print("Ingrese la moneda de origen: ");
        String origen = sc.next();

        System.out.print("Ingrese la moneda de destino: ");
        String destino = sc.next();

        double montoConvertido = MonedaCambio.convertirMoneda(cantidad, origen, destino);
        System.out.println("Conversión de " + cantidad + " " + origen + " a " + destino + ": " + montoConvertido);
    }


    public static void addOperacion(Scanner sc, Control operacionFinancieraControl){
        System.out.println(" Agregar una Operacion ");
        OperacionFinanciera operacion = crearOperacion(sc);
        operacionFinancieraControl.add(operacion);
        System.out.println(" Operacion Registrada exitosamente. ");
    }

    public static OperacionFinanciera crearOperacion(Scanner sc){
        Scanner scanner = new Scanner(System.in);
        double monto, tasaInteres; int plazo;

        while(true){
            try {
                System.out.println("Ingrese el monto de la operacion: ");
                monto = Double.parseDouble(scanner.nextLine());

                System.out.println("Ingrese el tasa de interes: ");
                tasaInteres = Double.parseDouble(scanner.nextLine());

                System.out.println("Ingrese el plazo en DIAS: ");
                plazo = Integer.parseInt(scanner.nextLine());

                if ((monto <= 0) || (plazo <= 0) || (tasaInteres < 0)) {
                    System.out.println("El valor de la operacion no es valido, recordarle ingresar la cantidad de dias :p");
                } else {
                    return new OperacionFinanciera<>(monto, tasaInteres, plazo);
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Asegúrate de ingresar solo números y usar punto (.) para decimales.");
            }
        }
    }

    public static int seleccionarOperacion(Scanner sc, Control oFP) {
        int index;
        while (true) {
            try {
                System.out.print("Selecciona una operación (0 - " + (oFP.size() - 1) + "): ");
                index = Integer.parseInt(sc.nextLine().trim());

                if (index >= 0 && index < oFP.size()) {
                    return index;
                } else {
                    System.out.println("Índice fuera de rango. Intenta nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
            }
        }
    }
}