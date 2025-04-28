package Fase2.P5;

class Nodo<E> {
    E dato;
    Nodo<E> next;

    public Nodo(E dato) {
        this.dato = dato;
        this.next = null;
    }
    public Nodo(E dato, Nodo<E> next) {
        this.dato = dato;
        this.next = null;
    }

    public E getDato() {
        return dato;
    }

    public Nodo<E> getNext() {
        return next;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", next=" + next +
                '}';
    }
}
