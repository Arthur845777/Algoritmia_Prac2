package Fase2.P5.Act.ClaseNodo;

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
            Nodo<Object> nodo = (Nodo<Object>) headField.get(lista);
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
