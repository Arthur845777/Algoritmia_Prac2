package Fase2.P5.Act.TareaSanti;

class LinkedList<T extends Comparable<T>> {
    private Nodo<T> head;

    public LinkedList() {
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
        if (head == null) {
            return -1;
        }

        Nodo<T> nodoCurrent = head;
        int cont = 0;

        while (nodoCurrent != null) {
            if (nodoCurrent.dato.equals(nodo)) {
                return cont;
            }
            nodoCurrent = nodoCurrent.next;
            cont++;
        }

        return -1;
    }

    public boolean removeNode(T valor) {
        if (head == null) {
            return false;
        }

        Nodo<T> nodoCurrent = head;
        while (nodoCurrent.next != null && !nodoCurrent.dato.equals(valor)) {
            if (nodoCurrent.next.dato.equals(valor)) {
                nodoCurrent.next = nodoCurrent.next.next;
                return true;
            }
            nodoCurrent = nodoCurrent.next;
        }
        return false;
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

    public void sortList() {
        if (head == null || head.next == null) {
            return;
        }

        LinkedList<T> sortedList = new LinkedList<>();

        Nodo<T> nodoCurrent = head;
        while (nodoCurrent != null) {
            Nodo<T> next = nodoCurrent.next;

            sortedList.insertionSort(nodoCurrent.dato);

            nodoCurrent = next;
        }

        this.head = sortedList.head;
    }

    public void printNode(){
        if (head == null) {
            System.out.println("Lista Vacia");
            return;
        } else {
            Nodo<T> nodoCurrent = head;
            do {
                System.out.print(nodoCurrent.dato + "\n");
                nodoCurrent = nodoCurrent.next;
            }while (nodoCurrent != null);
        }
    }

    public int countNode(){
        if (head == null) {
            return 0;
        } else {
            Nodo<T> nodoCurrent = head;
            int count = 0;
            while (nodoCurrent.next != null){
                nodoCurrent = nodoCurrent.next;
                count++;
            }
            return count;
        }
    }

    public T maxPriorityTask(){
        if (head == null) {
            return null;
        }
        Nodo<T> nodoCurrent = head;
        T maxElement = head.dato;

        while (nodoCurrent != null) {
            if(nodoCurrent.dato.compareTo(maxElement) > 0) {
                maxElement = nodoCurrent.dato;
            }
            nodoCurrent = nodoCurrent.next;
        }
        return maxElement;
    }

    public void reverseLinkedList() {
        if (head == null || head.next == null) {
            return;
        }

        Nodo<T> prevNode = null;
        Nodo<T> currentNode = head;
        Nodo<T> nextNode = null;

        while (currentNode != null) {
            nextNode = currentNode.next;

            currentNode.next = prevNode;

            prevNode = currentNode;
            currentNode = nextNode;
        }
        head = prevNode;
    }

}