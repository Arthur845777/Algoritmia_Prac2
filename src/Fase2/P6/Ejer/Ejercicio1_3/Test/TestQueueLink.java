package Fase2.P6.Ejer.Ejercicio1_3.Test;

import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;
import Fase2.P6.Ejer.Ejercicio1_3.Queue.QueueLink;

public class TestQueueLink {
	public static void main(String[] args) throws ExceptionIsEmpty {
		QueueLink<Integer> queueLink = new QueueLink<>();
		//queueLink.front();
		queueLink.enqueue(10);
		queueLink.enqueue(20);
		System.out.println(queueLink.dequeue());
	}
}
