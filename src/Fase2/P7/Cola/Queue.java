package Fase2.P7.Cola;
import Fase2.P7.Exceptions.ExceptionIsEmpty;

public interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
} 