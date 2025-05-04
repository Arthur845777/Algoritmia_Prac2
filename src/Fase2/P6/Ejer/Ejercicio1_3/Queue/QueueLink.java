package Fase2.P6.Ejer.Ejercicio1_3.Queue;
import Fase2.P6.Ejer.Ejercicio1_3.Node.Node;
import Fase2.P6.Ejer.Ejercicio1_3.ExceptionIsEmpty.ExceptionIsEmpty;

public class QueueLink<E> implements Queue<E>{
	private Node<E> front;
	private Node<E> rear;
	
	public QueueLink() {
		this.front = this.rear = null;
	}
	
	public void enqueue(E x) {
		Node<E> newNode = new Node<>(x);
		if (isEmpty()) {
			this.front = this.rear = newNode;
		}
		else {
			rear.setNext(newNode);
			rear = newNode;
		}
	}

	public E dequeue() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
		E aux = front.getData();
		front = front.getNext();
		if (front == null) rear = null;
		return aux;
	}

	public E front() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
		return front.getData();
	}

	public E back() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty("Empty Queue");
		return rear.getData();
	}

	public boolean isEmpty() {
		if(front == null) return true;
		return false;
	}

}
