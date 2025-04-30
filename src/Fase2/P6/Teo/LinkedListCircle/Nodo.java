package Fase2.P6.Teo.LinkedListCircle;

class Nodo<E> {
    private E dato;
    private Nodo<E> next;

    public Nodo(E dato) {
        this.dato = dato;
        this.next = null;
    }

    public Nodo(E dato, Nodo<E> next) {
        this.dato = dato;
        this.next = next;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public Nodo<E> getNext() {
        return next;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }
}