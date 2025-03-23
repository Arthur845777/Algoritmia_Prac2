package Fase1.P2.Ejercicio.GestorInventarioGenerico;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operador<Double> operador = new Operador<>();

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
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    operador.agregarProducto(new Producto<>(nombre, precio, cantidad));
                    break;
                case 2:
                    System.out.println("Valor total del inventario: " + operador.calcularValorTotal());
                    break;
                case 3:
                    System.out.print("Nombre del producto a descontar: ");
                    String prodDesc = scanner.nextLine();
                    System.out.print("Porcentaje de descuento (%): ");
                    double descuento = scanner.nextDouble();
                    operador.aplicarDescuento(prodDesc, descuento);
                    break;
                case 4:
                    System.out.print("Nombre del producto: ");
                    String prodInc = scanner.nextLine();
                    System.out.print("Cantidad a incrementar: ");
                    int incremento = scanner.nextInt();
                    operador.incrementarExistencias(prodInc, incremento);
                    break;
                case 5:
                    Producto<Double> caro = operador.productoMasCaro();
                    System.out.println("Producto más caro: " + (caro != null ? caro.getNombre() : "No hay productos"));
                    break;
                case 6:
                    Producto<Double> barato = operador.productoMasBarato();
                    System.out.println("Producto más barato: " + (barato != null ? barato.getNombre() : "No hay productos"));
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
