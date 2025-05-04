package Fase2.P6.Queue;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public interface Queue<E> {
    public void enqueue(E e);
    public E dequeue() throws ExceptionIsEmpty;
    public E front() throws ExceptionIsEmpty;
    public E back() throws ExceptionIsEmpty;
    public boolean isEmpty();
}
