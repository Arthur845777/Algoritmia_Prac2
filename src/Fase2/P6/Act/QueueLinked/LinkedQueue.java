package Fase2.P6.Act.QueueLinked;

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
            E item = first.getDato();
            first = first.getNext();
            return item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public E front() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return first.getDato();
        }
    }

    public E back() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        } else {
            return last.getDato();
        }
    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }
}
