package Fase2.P6.Ejer.Ejercicio1_3.Stack;
import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;

public interface Stack<E> {
	void push(E x);
	E pop() throws ExceptionIsEmpty;
	E top() throws ExceptionIsEmpty;
	boolean isEmpty();
}
