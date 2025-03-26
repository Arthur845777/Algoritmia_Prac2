package Fase1.P2.Ejercicio.NuevaPractica.ejer3_4;

class Caja<T> {
    private String color;
    private T elemento;

    public Caja(String color) {
        this.color = color;
    }
    public Caja() {}

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public T getElemento() {
        return elemento;
    }
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public static <T> void add(T elemento, Caja<T> caja) {
        caja.setElemento(elemento);
    }

    public void vaciar() {
        System.out.println("\nElemento eliminado: " + getElemento());
        elemento = null;
    }

    @Override
    public String toString() {
        return "Caja de color " + color + " con contenido: " + (elemento != null ? elemento.toString() : "vacío");
    }
}