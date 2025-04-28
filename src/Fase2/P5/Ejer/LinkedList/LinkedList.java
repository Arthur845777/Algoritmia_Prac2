package Fase2.P5.Ejer.LinkedList;

public class LinkedList<T extends Comparable<T>> {
    
    public Node<T> head; // Primer nodo de la lista
    public Node<T> tail; // Último nodo de la lista 

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Verifica si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    // Retorna la cantidad de nodos en la lista
    public int length() {
        Node<T> currentNode = head;
        int count = 0;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    // Destruye la lista (elimina todos los nodos)
    public void destroyList() {
        this.head = null;
        this.tail = null; 
    }
    
    // Inserta un nodo al inicio de la lista
    public void insertFirst(Node<T> node) {
        if (isEmpty()) {
            this.head = node;
            this.tail = node; 
        } else {
            node.next = head;
            head.before = node;
            head = node;
        }
    }

    // Inserta un nodo al final de la lista
    public void insertLast(Node<T> node) {
        if (isEmpty()) {
            this.head = node;
            this.tail = node; 
        } else {
            tail.next = node;
            node.before = tail;
            tail = node;
        }
    }

    // Elimina un nodo específico de la lista
    public void removeNode(Node<T> node) {
        if (isEmpty() || node == null) { // Agregado chequeo de nulo para seguridad
            return;
        }
        
        Node<T> current = head;
        
        while (current != null) {
            if (current.data.compareTo(node.data) == 0) { // Compara por dato
                if (current == head) { // Si el nodo es el head
                    head = current.next;
                    if (head != null) {
                        head.before = null;
                    } else {
                        tail = null; // Si también era el único nodo
                    }
                } 
                else if (current == tail) { // Si el nodo es el tail
                    tail = current.before;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null; // Solo un elemento
                    }
                } 
                else { // Nodo en medio
                    current.before.next = current.next;
                    current.next.before = current.before;
                }
                current.next = null; // Desenlazar completamente
                current.before = null;
                return;
            }
            current = current.next;
        }
    }

    // Busca si un nodo con el mismo dato existe en la lista
    public boolean search(Node<T> node) {
        if (node == null) return false; // Seguridad
        
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.compareTo(node.data) == 0) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    // Ordena la lista de menor a mayor usando Bubble Sort
    public void sort() {
        if (length() <= 1) {
            return; 
        }
        
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            
            while (current != null && current.next != null) { // Asegurar que current.next existe
                if (current.data.compareTo(current.next.data) > 0) {
                    // Intercambia datos de nodos
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped); 
    }
}
