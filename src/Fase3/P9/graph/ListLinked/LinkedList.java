package Fase3.P9.graph.ListLinked;

public class LinkedList<T> {
    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int cont = 0;
        for (Node<T> i = head; i != null; i = i.getNext()) {
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

        Node<T> NodeCurrent = head;
        int cont = 0;

        while (NodeCurrent != null) {
            if (NodeCurrent.getData().equals(nodo)) {
                return cont;
            }
            NodeCurrent = NodeCurrent.getNext();
            cont++;
        }
        return -1;
    }

    public boolean removeNode(T valor) {
        if (head == null) {
            return false;
        }
        if (head.getData().equals(valor)) {
            head = head.getNext();
            return true;
        }

        Node<T> NodeCurrent = head;

        while (NodeCurrent.getNext() != null) {
            if (NodeCurrent.getNext().getData().equals(valor)) {
                NodeCurrent.setNext(NodeCurrent.getNext().getNext());
                return true;
            }
            NodeCurrent = NodeCurrent.getNext();
        }

        return false;
    }

    public void insertFirst(T valor) {
        Node<T> newNode = new Node<T>(valor);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void insertLast(T valor) {
        Node<T> newNode = new Node<T>(valor);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> NodeCurrent = head;
            while (NodeCurrent.getNext() != null) {
                NodeCurrent = NodeCurrent.getNext();
            }
            NodeCurrent.setNext(newNode);
        }
    }

    public int countNode(){
        if (head == null) {
            return 0;
        } else {
            Node<T> nodoCurrent = head;
            int count = 1;
            while (nodoCurrent.getNext() != null){
                nodoCurrent = nodoCurrent.getNext();
                count++;
            }
            return count;
        }
    }

    public void reverseLinkedList() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node<T> prevNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = null;

        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(prevNode);
            prevNode = currentNode;
            currentNode = nextNode;
        }
        head = prevNode;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}



//    public void sortLinkedList(T dato) {
//        Node<T> newNode = new Node<>(dato);
//
//        if (head == null || dato.compareTo(head.getData()) < 0) {
//            newNode.setNext(head);
//            head = newNode;
//        } else {
//            Node<T> current = head;
//            while (current.getNext() != null && current.getNext().getData().compareTo(dato) < 0) {
//                current = current.getNext();
//            }
//            newNode.setNext(current.getNext());
//            current.setNext(newNode);
//        }
//    }
//
//    public void insertionSort() {
//        if (head == null || head.getNext() == null) {
//            return;
//        }
//
//        LinkedList<T> sortedList = new LinkedList<>();
//
//        Node<T> nodoCurrent = head;
//        while (nodoCurrent != null) {
//            Node<T> next = nodoCurrent.getNext();
//
//            sortedList.sortLinkedList(nodoCurrent.getData());
//
//            nodoCurrent = next;
//        }
//
//        this.head = sortedList.head;
//    }