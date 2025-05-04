package Queue;
import ExceptionIsEmpty.ExceptionIsEmpty;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int back;

    public QueueArray(int n) {
        this.array = (E[]) new Object[n + 1];
        this.front = 0;
        this.back = 0;
    }

    public void enqueue(E x) {
        if (isFull()) return;
        array[back] = x;
        back = (back + 1) % array.length;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
        E aux = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        return aux;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
        return array[front];
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
        int lastIndex = (back - 1 + array.length) % array.length;
        return array[lastIndex];
    }

    public boolean isEmpty() {
        return front == back;
    }

    public boolean isFull() {
        return (back + 1) % array.length == front;
    }
}
