package Fase2.P5.Ejer.LinkedList;

public class LinkedListFunctions<T extends Comparable<T>> {

    // Busca un elemento en la lista
    public static <T extends Comparable<T>> boolean search(LinkedList<T> list, T elemento) {
        Node<T> currentNode = list.head;
        while (currentNode != null) {
            if (currentNode.data.compareTo(elemento) == 0) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    // Invierte el orden de los elementos de la lista
    public static <T extends Comparable<T>> void invertir(LinkedList<T> list) {
        if (list.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Node<T> inicio = list.head;
        Node<T> fin = list.tail;
        int pivot = list.length() / 2; // Hasta la mitad basta para invertir
        
        System.out.println("La lista inicial es: " + toString(list));

        for (int i = 0; i < pivot; i++) {
            // Intercambia los datos de los nodos
            T temp = inicio.data;
            inicio.data = fin.data;
            fin.data = temp;

            inicio = inicio.next;
            fin = fin.before;
        }

        System.out.println("La lista invertida es: " + toString(list));
    }

    // Inserta un elemento al final de la lista
    public static <T extends Comparable<T>> void insertar(LinkedList<T> list, T elemento) {
        Node<T> node = new Node<>(elemento);

        if (list.isEmpty()) {
            list.head = node;
            list.tail = node;
        } else {
            list.tail.next = node;
            node.before = list.tail;
            list.tail = node;
        }

        System.out.println("La lista será: " + toString(list));
    }

    // Cuenta el número de nodos en la lista
    public static <T extends Comparable<T>> int contar(LinkedList<T> list) {
        Node<T> currentNode = list.head;
        int contador = 0;
        while (currentNode != null) {
            contador++;
            currentNode = currentNode.next;
        }
        return contador;
    }

    // Compara dos listas: mismo tamaño, mismos elementos y mismo orden
    public static <T extends Comparable<T>> boolean comparar(LinkedList<T> list1, LinkedList<T> list2) {
        if (list1.length() != list2.length()) {
            return false;
        }

        Node<T> currentNode1 = list1.head;
        Node<T> currentNode2 = list2.head;

        while (currentNode1 != null && currentNode2 != null) {
            if (currentNode1.data.compareTo(currentNode2.data) != 0) {
                return false;
            }
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
        }

        return true;
    }

    // Une dos listas: conecta la cola de la primera con la cabeza de la segunda
    public static <T extends Comparable<T>> void unir(LinkedList<T> list1, LinkedList<T> list2) {
        if (list1.isEmpty()) {
            list1.head = list2.head;
            list1.tail = list2.tail;
        } else if (!list2.isEmpty()) {
            list1.tail.next = list2.head;
            list2.head.before = list1.tail;
            list1.tail = list2.tail;
        }
        System.out.println("La nueva lista sería: " + toString(list1));
    }

    // Representación en texto de la lista
    public static <T extends Comparable<T>> String toString(LinkedList<T> list) {
        StringBuilder lista = new StringBuilder();
        Node<T> currentNode = list.head;
        while (currentNode != null) {
            lista.append(currentNode.data).append(" ");
            currentNode = currentNode.next;
        }
        return lista.toString().trim();
    }
}
