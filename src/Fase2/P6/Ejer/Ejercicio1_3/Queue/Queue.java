package Fase2.P6.Ejer.Ejercicio1_3.Queue;
import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;

public interface Queue<E> {
	void enqueue(E x);
	E dequeue() throws ExceptionIsEmpty;
	E front() throws ExceptionIsEmpty;
	E back() throws ExceptionIsEmpty;
	boolean isEmpty();
} 
