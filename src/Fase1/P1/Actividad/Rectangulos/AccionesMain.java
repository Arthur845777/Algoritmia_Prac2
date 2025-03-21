package Fase1.P1.Actividad.Rectangulos;

import java.util.List;
import java.util.Scanner;

public class AccionesMain {
    public static int validarEntrada(Scanner sc) {
        while (true) {
            try {
                int opcion = Integer.parseInt(sc.nextLine().trim());

                if (opcion >= 1 && opcion <= 5) { return opcion; }
                else { System.out.print("Ingresa un número valido entre 1 y 5: "); }

            } catch (NumberFormatException e) {
                System.out.print("Entrada no valida. Ingresa un numero entero: ");
            }
        }
    }

    public static void agregarRectangulo(Scanner sc, ContainerRect conte) {
        System.out.println("\n=== Agregar un rectangulo ===");
        Rectangulo nuevoRect = crearRectangulo(sc);
        conte.addRectagulo(nuevoRect);
        System.out.println("Rectángulo agregado correctamente :'p ");
    }

    public static void mostrarRelacion(Scanner sc, ContainerRect conte) {
        if (conte.numRect < 2) {
            System.out.println("Debes agregar al menos dos rectángulos para comparar.");
            return;
        }

        System.out.println("\n=== Mostrar relación entre dos rectángulos ===");
        System.out.println(conte);

        int recta1 = seleccionarRectangulo(sc, conte, "primer");
        int recta2 = seleccionarRectangulo(sc, conte, "segundo");

        Verificador.mostrarR(conte.getRectangulo(recta1), conte.getRectangulo(recta2));

    }

    public static void graficarRectangulos(Scanner sc, ContainerRect conte) {
        if (conte.numRect < 2) {
            System.out.println("No hay suficientes rectángulos para graficar.");
            return;
        }

        System.out.println("\n=== Graficar dos rectángulos ===");
        System.out.println(conte);

        int recta1 = seleccionarRectangulo(sc, conte, "primer");
        int recta2 = seleccionarRectangulo(sc, conte, "segundo");

        List<Rectangulo> lista = List.of(conte.getRectangulo(recta1), conte.getRectangulo(recta2));

//        List<Rectangulo> lista = new ArrayList<>();
//        lista.add(new Rectangulo(new Coordenada(1,1), new Coordenada(4,3)));
//        lista.add(new Rectangulo(new Coordenada(2,2), new Coordenada(5,3)));

        RectanguloGrafico.mostrarGrafico(lista);
    }

    public static Rectangulo crearRectangulo(Scanner sc) {
        double x1, y1, x2, y2;

        while (true) {
            try {
                System.out.print("Ingrese x1: ");
                x1 = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Ingrese y1: ");
                y1 = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Ingrese x2: ");
                x2 = Double.parseDouble(sc.nextLine().trim());

                System.out.print("Ingrese y2: ");
                y2 = Double.parseDouble(sc.nextLine().trim());

                if (x1 != x2 && y1 != y2) {
                    return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
                } else {
                    System.out.println("Error: Las coordenadas deben formar un rectángulo válido. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Asegúrate de ingresar solo números y usar punto (.) para decimales.");
            }
        }
    }


    public static int seleccionarRectangulo(Scanner sc, ContainerRect conte, String orden) {
        int index;
        while (true) {
            try {
                System.out.print("Selecciona el " + orden + " rectangulo (0 - " + (conte.numRect - 1) + "): ");
                index = Integer.parseInt(sc.nextLine().trim());

                if (index >= 0 && index < conte.numRect) {
                    return index;
                } else {
                    System.out.println("Indice fuera de rango. Intenta nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un numero entero.");
            }
        }
    }

}
