package Fase2.P5.Ejer.lista;

public class LinkedList<T extends Comparable<T>> {
    public Nodo<T> head;
    public Nodo<T> tail; 

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmphy() {
        return head == null;
    }

    public int length() {
        Nodo<T> nodocurrent = head;
        int contador = 0;
        while (nodocurrent != null) {
            contador++;
            nodocurrent = nodocurrent.next;
        }
        return contador;
    }

    public void InsertionF(Nodo<T> node) {
        if (isEmphy()) {
            this.head = node;
            this.tail = node; 
            return;
        }
        node.next = head;
        head.before = node;
        head = node;
    }

    public void InsertionL(Nodo<T> node) {
        if (isEmphy()) {
            this.head = node;
            this.tail = node; 
            return;
        }
  
        tail.next = node;
        node.before = tail;
        tail = node;
    }

    public void remove(Nodo<T> node) {
        if (isEmphy()) {
            return;
        }
        Nodo<T> current = head;
        
        while (current != null) {
            if (current.dato.compareTo(node.dato) == 0) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.before = null;
                    } else {
                        tail = null;
                    }
                } 
                else if (current == tail) {
                    tail = current.before;
                    tail.next = null;
                } 
                else {
                    current.before.next = current.next;
                    current.next.before = current.before;
                }
                current.next = null;
                current.before = null;
                return;
            }
            current = current.next;
        }
    }

    public boolean search(Nodo<T> node){
        Nodo<T> nodocurrent =head;
        while(nodocurrent!= null){
            if(nodocurrent.dato.compareTo(node.dato)==0){
                return true;
            }
            nodocurrent=nodocurrent.next;
        }
        return false;
    }

    public void sort() {
        if (length()<=1) {
            return; 
        }
        
        boolean swapped;
        do {
            swapped = false;
            Nodo<T> current = head;
            
            while (current.next != null) {
                if (current.dato.compareTo(current.next.dato) > 0) {
                    T temp = current.dato;
                    current.dato = current.next.dato;
                    current.next.dato = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped); 
    }

}