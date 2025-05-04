package Test;

import Queue.ExceptionIsEmpty;
import Queue.QueueLink;

public class TestQueueLink {
	public static void main(String[] args) throws ExceptionIsEmpty {
		QueueLink<Integer> queueLink = new QueueLink<>();
		//queueLink.front();
		queueLink.enqueue(10);
		queueLink.enqueue(20);
		System.out.println(queueLink.dequeue());
	}
}
