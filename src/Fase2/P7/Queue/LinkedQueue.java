package Fase2.P7.Queue;
import Fase2.P7.Node.NodeSimple;
import Fase2.P7.Exceptions.ExceptionIsEmpty;

public class LinkedQueue<E> implements Queue<E> {
    private NodeSimple<E> first;
    private NodeSimple<E> last;
    private int size;

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(E e) {
        NodeSimple<E> newNode = new NodeSimple<>(e);
        if(isEmpty()){
            first = last = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            E item = first.getData();
            first = first.getNext();
            size--;
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

    public int size() {
        return size;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }
}
