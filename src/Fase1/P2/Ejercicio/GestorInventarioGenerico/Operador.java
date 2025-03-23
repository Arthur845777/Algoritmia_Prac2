package Fase1.P2.Ejercicio.GestorInventarioGenerico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Operador<T extends Number> {
    private List<Producto<T>> inventario = new ArrayList<>();

    public void agregarProducto(Producto<T> producto) {
        inventario.add(producto);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Producto<T> p : inventario) {
            total += p.getPrecio().doubleValue() * p.getCantidad();
        }
        return total;
    }

    public void aplicarDescuento(String nombre, T descuento) {
        for (Producto<T> p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                double nuevoPrecio = p.getPrecio().doubleValue() * (1 - descuento.doubleValue() / 100);
                p.setPrecio((T) Double.valueOf(nuevoPrecio));
                return;
            }
        }
    }

    public void incrementarExistencias(String nombre, int cantidad) {
        for (Producto<T> p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setCantidad(p.getCantidad() + cantidad);
                return;
            }
        }
    }

    public Producto<T> productoMasCaro() {
        return inventario.stream().max(Comparator.comparingDouble(p -> p.getPrecio().doubleValue())).orElse(null);
    }

    public Producto<T> productoMasBarato() {
        return inventario.stream().min(Comparator.comparingDouble(p -> p.getPrecio().doubleValue())).orElse(null);
    }
}
