package Fase2.P5.Act.ClaseNodo;

class ListLinked<T extends Comparable<T>> {
    private Nodo<T> head;

    public ListLinked() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int cont = 0;
        if (head == null) {
            return 0;
        }
        for (Nodo<T> i = head; i != null; i = i.next) {
            cont++;
        }
        return cont;
    }

    public void destroyList() {
        head = null;
    }

    public int search(T nodo) {
        Nodo<T> nodoCurrent = head;
        int cont = 0;
        while (nodoCurrent.next != null && !nodoCurrent.dato.equals(nodo)) {
            nodoCurrent = nodoCurrent.next;
            cont++;
        }
        return cont;
    }

    public void insertFirst(T nodo) {
        Nodo<T> newNode = new Nodo<T>(nodo);
        if (head == null) {
            head = newNode;
        } else {
            Nodo<T> nodoAux = head;
            head = newNode;
            head.next = nodoAux;
        }
    }

    public void insertLast(T nodo) {
        Nodo<T> newNode = new Nodo<T>(nodo);
        if (head == null) {
            head = newNode;
        } else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null) {
                nodoCurrent = nodoCurrent.next;
            }
            nodoCurrent.next = newNode;
        }
    }

    public void removeNode(T valor) {
        if (head == null) {
            return;
        } else {
            Nodo<T> nodoCurrent = head;
            while (nodoCurrent.next != null && !nodoCurrent.dato.equals(valor)) {
                nodoCurrent = nodoCurrent.next;
            }
            if (nodoCurrent.next.dato.equals(valor)) {
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
