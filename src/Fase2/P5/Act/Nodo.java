package Fase2.P5.Act;

public class Nodo<T> {
    T dato;
    Nodo<T> next;

    public Nodo(T dato) {
        this.dato = dato;
        this.next = null;
    }
}

class ListLinked<T extends Comparable<T>> {
    private Nodo<T> head;

    public ListLinked() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length(){
        int cont = 0;
        if(head == null){
            return 0;
        }
        for (Nodo<T> i = head; i != null; i = i.next){
            cont++;
        }
        return cont;
    }

    public void destroyList(){
        head = null;
    }

    public int search(T nodo){
        Nodo<T> nodoCurrent = head;
        int cont = 0;
        while (nodoCurrent.next != null && !nodoCurrent.dato.equals(nodo)){
            nodoCurrent = nodoCurrent.next;
            cont++;
        }
        return cont;
    }

    public void insertFirst(T nodo){
        Nodo<T> newNode = new Nodo<T>(nodo);
        if(head == null) {
            head = newNode;
        } else {
            Nodo<T> nodoAux = head;
            head = newNode;
            head.next = nodoAux;
        }
    }

    public void insertLast(T nodo){
        Nodo<T> newNode = new Nodo<T>(nodo);
        if(head == null){
            head = newNode;
        }
        else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null){
                nodoCurrent = nodoCurrent.next;
            }
            nodoCurrent.next = newNode;
        }
    }

    public void removeNode(T valor){
        if(head == null){
            return;
        }
        else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null && !nodoCurrent.dato.equals(valor)) {
                nodoCurrent = nodoCurrent.next;
            }
            if (nodoCurrent.next.dato.equals(valor)){
                nodoCurrent.next = nodoCurrent.next.next;
            }
        }
    }

    public void insertionSort(T dato) {
        Nodo<T> newNode = new Nodo<>(dato);

        if (head == null || dato.compareTo(head.dato) < 0) {
            insertFirst(dato);
        } else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null && nodoCurrent.next.dato.compareTo(dato) < 0) {
                nodoCurrent = nodoCurrent.next;
            }
            newNode.next = nodoCurrent.next;
            nodoCurrent.next = newNode;
        }
    }
}

class Main {
    public static void main(String[] args) {
        ListLinked<Integer> lista = new ListLinked<>();

        System.out.println("¿La lista está vacía? " + lista.isEmpty());

        System.out.println("\nInsertamos al principio:");
//        lista.insertFirst(x); metodo original, cambiarlo santi
        lista.insertionSort(10);
        lista.insertionSort(20);
        lista.insertionSort(30);
        imprimirLista(lista);

        System.out.println("\nInsertamos al final:");
//        lista.insertLast(x); metodo original, cambiarlo santi
        lista.insertionSort(5);
        lista.insertionSort(1);
        imprimirLista(lista);

        int nodo = 20;
        System.out.printf("\nBuscamos el valor %d:\n", nodo);
        int posicion = lista.search(nodo);
        System.out.println("Posición de " + nodo + ": " + posicion);

        System.out.println("\nEliminamos el valor 20:");
        lista.removeNode(20);
        imprimirLista(lista);

        System.out.println("\nCantidad de elementos:");
        System.out.println(lista.length());

        System.out.println("\nDestruimos la lista:");
        lista.destroyList();
        imprimirLista(lista);
    }

    public static void imprimirLista(ListLinked<Integer> lista) {
        // metodo para imprimir, sugerencia de GPT
        try {
            java.lang.reflect.Field headField = ListLinked.class.getDeclaredField("head");
            headField.setAccessible(true);
            Nodo<Integer> nodo = (Nodo<Integer>) headField.get(lista);
            while (nodo != null) {
                System.out.print(nodo.dato + " -> ");
                nodo = nodo.next;
            }
            System.out.println("null");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
