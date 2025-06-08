package Fase3.P9.LinkedList;

public class LinkedList<T> {
    private Node<T> head;
    private int countNodes;

    public LinkedList() {
        this.head = null;
        countNodes = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        return countNodes;
    }

    public void destroyList() {
        head = null;
    }

    public int findIndex(T nodo) {
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

    public boolean search(T nodo) {
        if (head == null) {
            return false;
        }

        Node<T> NodeCurrent = head;

        while (NodeCurrent != null) {
            if (NodeCurrent.getData().equals(nodo)) {
                return true;
            }
            NodeCurrent = NodeCurrent.getNext();
        }
        return false;
    }

    public T getNodeAtIndex(int index) {
        if (index < 0 || index >= this.length()) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
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
                this.countNodes--;
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
        this.countNodes++;
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
        this.countNodes++;
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