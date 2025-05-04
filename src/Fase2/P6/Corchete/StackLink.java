package Fase2.P6.Corchete;

import Fase2.P6.Node.Node;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;
import Fase2.P6.Stack.Stack;

public class StackLink<E> implements Stack<E> {
	private Node<E> top;

	public StackLink() {
		this.top = null;
	}

	public void push(E x) {
		Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
	}

	public E pop() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty();
		E aux = top.getData();
		top = top.getNext();
		return aux;
	}

	public E top() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty();
		return top.getData();
	}

	public boolean isEmpty() {
		if(top == null) return true;
		return false;
	}

}
