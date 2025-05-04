package Fase2.P6.Act.PriorityQueue;

class Node<E> {
    private E dato;
    private Node<E> next;

    public Node(E dato) {
        this.dato = dato;
        this.next = null;
    }

    public E getDato() {
        return dato;
    }
    public Node<E> getNext() {
        return next;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
