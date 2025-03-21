package Fase1.P1.Actividad.Rectangulos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opc;
        Scanner sc = new Scanner(System.in);
        ContainerRect conte = new ContainerRect();

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Agregar un rectangulo");
            System.out.println("2. Mostrar relación entre dos rectangulos");
            System.out.println("3. Graficar dos rectangulos");
            System.out.println("4. Mostrar Rectangulos");
            System.out.println("5. Finalizar");
            System.out.print("Selecciona una opción (1-5): ");

            opc = AccionesMain.validarEntrada(sc);

            switch (opc) {
                case 1:
                    AccionesMain.agregarRectangulo(sc, conte);
                    break;
                case 2:
                    AccionesMain.mostrarRelacion(sc, conte);
                    break;
                case 3:
                    AccionesMain.graficarRectangulos(sc, conte);
                    break;
                case 4:
                    System.out.println(conte);
                    break;
                case 5:
                    System.out.println("\nPrograma finalizado. ¡Hasta nunca :p!");
                    System.out.println("Posdata, ya no quiero trabajar. La UI me ha matado :')");
                    break;
                default:
                    System.out.println("\nOpcion invalida, intenta de nuevo.");
            }
        } while (opc != 5);

        sc.close();
    }


}

//        Crear dos rectángulos con coordenadas específicas
//        Rectangulo rect1 = new Rectangulo(new Coordenada(7.6, 2.2), new Coordenada(1.5, 0.3));
//        Rectangulo rect2 = new Rectangulo(new Coordenada(9.4,-2.5), new Coordenada(4.0, 4.2));
//        Rectangulo rect3 = new Rectangulo(new Coordenada(33.3, 3.6), new Coordenada(20.5, -1.1));
//        Rectangulo rect4 = new Rectangulo(new Coordenada(20.5, 8.6), new Coordenada(10.3, -5.2));
//        Rectangulo rect5 = new Rectangulo(new Coordenada(9, 5), new Coordenada(4, 0));
//        Rectangulo rect6 = new Rectangulo(new Coordenada(5, 11), new Coordenada(0, 6));