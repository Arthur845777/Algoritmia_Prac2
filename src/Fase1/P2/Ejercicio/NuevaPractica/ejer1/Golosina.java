package Fase1.P2.Ejercicio.NuevaPractica.ejer1;

public class Golosina implements Comparable<Golosina> {
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "nombre = " + nombre + ",peso = " + peso;
    }

    @Override
    public int compareTo(Golosina otra) {
        return this.nombre.compareTo(otra.nombre);
    }
}
