package Fase2.P6.Ejer.Ejercicio4.Cola;
import Fase2.P6.Ejer.Ejercicio4.Excepciones.ExceptionIsEmpty;

public interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
} 