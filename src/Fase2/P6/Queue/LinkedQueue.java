package Fase2.P6.Queue;
import Fase2.P6.Node.Node;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;

    public LinkedQueue() {
        first = null;
        last = null;
    }
    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e);
        if(isEmpty()){
            first = last = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            E item = first.getData();
            first = first.getNext();
            return item;
        }
    }

    public E front() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return first.getData();
        }
    }

    public E back() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return last.getData();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }
}
