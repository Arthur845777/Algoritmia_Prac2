package Fase2.P6.Ejer.Ejercicio1_3.PriorityQueue;
import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;

public interface PriorityQueue<E, N> {
	void enqueue(E x, N pr);
	E dequeue() throws ExceptionIsEmpty;
	E front() throws ExceptionIsEmpty;
	E back() throws ExceptionIsEmpty;
	boolean isEmpty();
}
