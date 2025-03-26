package Fase1.P2.Ejercicio.NuevaPractica.ejer1;

public class Main {
    public static void main(String[] args) {
        // arreglo de Chocolatina
        Chocolatina[] chocolates = {
            new Chocolatina("Nestlé"),
            new Chocolatina("Ferrero"),
            new Chocolatina("Hershey's")
        };

        // arreglo de Golosina
        Golosina[] golosinas = {
            new Golosina("Caramelo", 10.5),
            new Golosina("Chicle", 5.2),
            new Golosina("Gomita", 12.3)
        };

        // metodo exist()
        System.out.println(exist(chocolates, new Chocolatina("Ferrero"))); // true
        System.out.println(exist(golosinas, new Golosina("Chicle", 5.2))); // true
        System.out.println(exist(chocolates, new Chocolatina("Milka")));   // false
    }

    static <T extends Comparable<T>> boolean exist(T[] lista, T elemento) {
        for (T i : lista) {
            if (elemento.compareTo(i) == 0) {
                return true;
            }
        }
        return false;
    }
}
