package Fase1.P2.Ejercicio.GestorInventarioGenerico;

import java.util.Scanner;

//Clase que ayuda al manejo de operador para no cargar el main con funciones
public class GestorProductos {
	Scanner scanner;
    Operador<Double> operador;

	public GestorProductos() {
		this.scanner = scanner = new Scanner(System.in);
		this.operador = new Operador<>();
	}
	
	public void agregarProducto() {
		System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Cantidad del producto: ");
        int cantidad = scanner.nextInt();
        operador.agregarProducto(new Producto<>(nombre, precio, cantidad));
	}
	
	public void calcularValorTotalInventario() {
        System.out.println("Valor total del inventario: " + operador.calcularValorTotal());
	}
	public void aplicarDescuento() {
		System.out.print("Nombre del producto a descontar: ");
        String prodDesc = scanner.nextLine();
        System.out.print("Porcentaje de descuento (%): ");
        double descuento = scanner.nextDouble();
        operador.aplicarDescuento(prodDesc, descuento);
	}
	public void incrementarExistencias() {
		System.out.print("Nombre del producto: ");
        String prodInc = scanner.nextLine();
        System.out.print("Cantidad a incrementar: ");
        int incremento = scanner.nextInt();
        operador.incrementarExistencias(prodInc, incremento);
	}
	public void productoMasCaro() {
		Producto<Double> caro = operador.productoMasCaro();
        System.out.println("Producto más caro: " + (caro != null ? caro.getNombre() : "No hay productos"));
	}
	public void productoMasBarato() {
		Producto<Double> barato = operador.productoMasBarato();
        System.out.println("Producto más barato: " + (barato != null ? barato.getNombre() : "No hay productos"));
	}
}