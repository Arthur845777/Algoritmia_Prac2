package Fase2.P6.PriorityQueue;

import Fase2.P6.Node.Node;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }

        public E getData() {
            return data;
        }
        public N getPriority() {
            return priority;
        }

        public void setData(E data) {
            this.data = data;
        }
        public void setPriority(N priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "EntryNode{" +
                    "data=" + data +
                    ", priority=" + priority +
                    '}';
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr) {
        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            first = last = newNode;
            return;
        }

        if (pr.compareTo(first.getData().getPriority()) < 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }

        Node<EntryNode> currentNode = first;
        while (currentNode.getNext() != null && pr.compareTo(currentNode.getNext().getData().priority) >= 0) {
            currentNode = currentNode.getNext();
        }

        newNode.setNext(currentNode.getNext());
        currentNode.setNext(newNode);

        if (newNode.getNext() == null) {
            last = newNode;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }

        E item = first.getData().data;
        first = first.getNext();

        if (first == null) {
            last = null;
        }
        return item;
    }

    public E front() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return first.getData().getData();
        }
    }

    public E back() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return last.getData().getData();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<EntryNode> current = first;
        while (current != null) {
            sb.append(current.getData().toString());
            if (current.getNext() != null)
                sb.append(" -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}
