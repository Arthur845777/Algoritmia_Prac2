package Fase2.P6.Teo.LinkedListCircle;

public class ListCircle <E> {
    private Nodo<E> first;

    public ListCircle() {
        first = null;
    }

    public void insert(E x) {
        if (first == null) {
            first = new Nodo<E>(x);
            first.setNext(first);
        } else {
            first.setNext(new Nodo<E>(x, first.getNext()));
        }
    }

    public void insertAtEnd(E x) {
        if (first == null) {
            first = new Nodo<E>(x);
            first.setNext(first);
        } else {
            Nodo<E> currentNode = first;
            while (currentNode.getNext() != first) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Nodo<E>(x, first));
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (first == null) {
            return "Lista vacía";
        } else {
            Nodo<E> aux = first;
            do {
                str.append(aux.getDato()).append(" ");
                aux = aux.getNext();
            } while (aux != first);
        }

        return str.toString();
    }

    public static void main(String[] args) {
        ListCircle<String> circle = new ListCircle<String>();
        circle.insert("A");
        circle.insert("B");
        circle.insert("C");
        circle.insert("D");

        System.out.println(circle);

    }
}