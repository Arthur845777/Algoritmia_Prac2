package Fase1.P2.Ejercicio.NuevaPractica.ejer2;

public class Chocolatina implements Comparable<Chocolatina> {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "marca = " + marca;
    }

    @Override
    public int compareTo(Chocolatina otra) {
        return this.marca.compareTo(otra.marca);
    }
}
