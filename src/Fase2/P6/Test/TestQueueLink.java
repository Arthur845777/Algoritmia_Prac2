package Fase2.P6.Test;

import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;
import Fase2.P6.Queue.LinkedQueue;

public class TestQueueLink {
	public static void main(String[] args) throws ExceptionIsEmpty {
		LinkedQueue<Integer> queueLink = new LinkedQueue<>();
		//queueLink.front();
		queueLink.enqueue(10);
		queueLink.enqueue(20);
		System.out.println(queueLink.dequeue());
	}
}
