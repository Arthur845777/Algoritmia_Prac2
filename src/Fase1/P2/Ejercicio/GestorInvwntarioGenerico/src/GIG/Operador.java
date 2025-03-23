package GIG;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Operador<T extends Number> {
	//lista de productos 
	private List<Producto<T>> inventario = new ArrayList<>();

	//funcion para agregar productos
    public void agregarProducto(Producto<T> producto) {
        inventario.add(producto);
    }

    //funcion para calcular el total de la venda de productos de productos
    public double calcularValorTotal() {
        double total = 0;
        for (Producto<T> p : inventario) {
            total += p.getPrecio().doubleValue() * p.getCantidad();//tranforma el tipo T en double para un mejor manejo
        }
        return total;
    }
    
    //funcion para calcular el descuento
    public void aplicarDescuento(String nombre, T descuento) {
        for (Producto<T> p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                double nuevoPrecio = p.getPrecio().doubleValue() * (1 - descuento.doubleValue() / 100);
                p.setPrecio((T) Double.valueOf(nuevoPrecio));
                return;
            }
        }
    }

    //funcion para incrementar existencias de un producto
    public void incrementarExistencias(String nombre, int cantidad) {
        for (Producto<T> p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setCantidad(p.getCantidad() + cantidad);
                return;
            }
        }
    }

    //para ambos casos se hace la funcion stream para recorrer los productos del inventario
    //encontrar el maximo/minimo al comparar el precio, el cual se convierte en double para mejor manejo,
    //y en caso no haya ningun producto, retorna null para no usar un try catch 
    //funcion para encontrar el producto mas caro
    public Producto<T> productoMasCaro() {
        return inventario.stream().max(Comparator.comparingDouble(p -> p.getPrecio().doubleValue())).orElse(null);
    }
    //funcion para encontrar el producto mas barato 
    public Producto<T> productoMasBarato() {
        return inventario.stream().min(Comparator.comparingDouble(p -> p.getPrecio().doubleValue())).orElse(null);
    }
}
