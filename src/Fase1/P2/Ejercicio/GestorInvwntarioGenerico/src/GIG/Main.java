package GIG;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorProductos gp = new GestorProductos();

        while (true) {
            System.out.println("\nMenú de GestorInventarioGenerico:");
            System.out.println("1. Agregar Producto al Inventario.");
            System.out.println("2. Calcular el valor total del Inventario.");
            System.out.println("3. Aplicar descuento a un producto.");
            System.out.println("4. Incrementar existencias de un producto.");
            System.out.println("5. Determinar el producto más caro.");
            System.out.println("6. Determinar el producto más barato.");
            System.out.println("7. Salir del Programa.");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    gp.agregarProducto();
                    break;
                case "2":
                	gp.calcularValorTotalInventario();
                	break;
                case "3":
                    gp.aplicarDescuento();
                    break;
                case "4":
                    gp.incrementarExistencias();;
                    break;
                case "5":
                    gp.productoMasBarato();
                	break;
                case "6":
                    gp.productoMasBarato();
                    break;
                case "7":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
