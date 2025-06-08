package Fase3.P9.LinkedList;

import Fase3.P9.Exceptions.ExceptionIsEmpty;

public class LinkedStack<E> {
    private Node<E> stack;
	private int counter = 0;

	public LinkedStack() {
		this.stack = null;
	}
	
	public void push(E x) { // poner
		Node<E> newNode = new Node<>(x);
        newNode.setNext(stack);
        stack = newNode;
		counter++;
	}

	public E pop() throws ExceptionIsEmpty { // quitar eliminando
		if(isEmpty()) {
			throw new ExceptionIsEmpty();
		}
		E aux = stack.getData();
		stack = stack.getNext();
		counter--;
		return aux;
	}

	public E top() throws ExceptionIsEmpty { // retoran sin borrar
		if(isEmpty()) {
			throw new ExceptionIsEmpty();
		}
		return stack.getData();
	}

	public void destroyStack(){
		stack = null;
	}

	public boolean isEmpty() {
		return stack == null;
	}

	public int totalAmount() {
		return counter;
	}
}
