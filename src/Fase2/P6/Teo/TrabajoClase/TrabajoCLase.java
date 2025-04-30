package Fase2.P6.Teo.TrabajoClase;

// Trabajo de Elizabeth Rodriquez (1) y Santiago Cusirramos (3), main respectivos a cada uno

public class TrabajoCLase<T extends Comparable<T>> {

    private Nodo<T> head;

    public TrabajoCLase() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Ejercicio 1
    public void intercambiar() {
        if (head == null || head.next == null) return;

        // Paso 1: contar nodos
        int n = 0;
        Nodo<T> temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        // Paso 2: encontrar el nodo anterior al inicio de la segunda mitad
        int mitad = n / 2;
        Nodo<T> prev = null;
        Nodo<T> actual = head;
        for (int i = 0; i < mitad; i++) {
            prev = actual;
            actual = actual.next;
        }

        // Paso 3: cortar la lista en dos
        prev.next = null;

        // Paso 4: encontrar el final de la segunda mitad
        Nodo<T> fin = actual;
        while (fin.next != null) {
            fin = fin.next;
        }

        // Paso 5: unir ambas mitades
        fin.next = head;
        head = actual;
    }

    // Métodos auxiliares para probar
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (head == null) {
            head = nuevo;
            return;
        }
        Nodo<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nuevo;
    }

    public void imprimir() {
        Nodo<T> temp = head;
        while (temp != null) {
            System.out.print(temp.dato + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }


    // Ejercicio 3
    public void colasIguales(Nodo<T> cola1, Nodo<T> cola2) {
        if(cola1 == null && cola2 == null) { // caso base donde ambos son nulos
            System.out.println("Son Iguales, ambos nulos");
            return;
        }

        Nodo<T> temp1 = cola1; // Usamos nodos temporales para no modificar las colas originales
        Nodo<T> temp2 = cola2;
        boolean iguales = true;

        while (temp1 != null && temp2 != null) {
            if(temp1.dato.compareTo(temp2.dato) != 0) { // comparamos elementos de ambas listas
                iguales = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // Verificamos si una cola es mas larga que la otra y viceversa
        if(temp1 != null || temp2 != null) {
            iguales = false;
        }

        if(iguales) { // si es verdad nos da que son iguales
            System.out.println("Son Iguales");
        } else { // si es falso nos da que son mentiras
            System.out.println("No son Iguales");
        }
    }

    public Nodo<T> getNodoCurrent() {
        return head;
    }
}


class Main1{
    public static void main(String[] args) {
        TrabajoCLase<Integer> lista = new TrabajoCLase<>();
        for (int i = 1; i <= 7; i++) {
            lista.agregarFinal(i);
        }

        System.out.print("Antes: ");
        lista.imprimir();

        lista.intercambiar();

        System.out.print("Después: ");
        lista.imprimir();
    }
}

class Main3 {
    public static void main(String[] args) {
        System.out.println("Prueba con lista de enteros:");
        TrabajoCLase<Integer> listaEnteros = new TrabajoCLase<>();
        listaEnteros.agregarFinal(10);
        listaEnteros.agregarFinal(20);
        listaEnteros.agregarFinal(30);
        listaEnteros.agregarFinal(40);
        listaEnteros.agregarFinal(50);
        listaEnteros.agregarFinal(60);

        TrabajoCLase<Integer> listaEnteros2 = new TrabajoCLase<>();
        listaEnteros2.agregarFinal(10);
        listaEnteros2.agregarFinal(20);
        listaEnteros2.agregarFinal(30);
        listaEnteros2.agregarFinal(40);
        listaEnteros2.agregarFinal(50);
        listaEnteros2.agregarFinal(60);

        listaEnteros.colasIguales(listaEnteros.getNodoCurrent(), listaEnteros2.getNodoCurrent());
    }
}