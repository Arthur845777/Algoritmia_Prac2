package Fase2.P6.Queue;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public interface Queue<E> {
    void enqueue(E e);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
}
