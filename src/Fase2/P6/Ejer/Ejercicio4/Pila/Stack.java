package Fase2.P6.Ejer.Ejercicio4.Pila;
import Fase2.P6.Ejer.Ejercicio4.Excepciones.ExceptionIsEmpty;

public interface Stack<E> {
    void push(E x);
    E pop() throws ExceptionIsEmpty;
    E top() throws ExceptionIsEmpty;
    boolean isEmpty();
}
