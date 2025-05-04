package Fase2.P6.Stack;
import Fase2.P6.Node.Node;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class LinkedStack<E> implements Stack<E> {
	private Node<E> stack;
	private static int counter = 0;

	public LinkedStack() {
		this.stack = null;
	}
	
	public void push(E x) {
		Node<E> newNode = new Node<>(x);
        newNode.setNext(stack);
        stack = newNode;
		counter++;
	}

	public E pop() throws ExceptionIsEmpty {
		if(isEmpty()) {
			throw new ExceptionIsEmpty();
		}
		E aux = stack.getData();
		stack = stack.getNext();
		return aux;
	}

	public E top() throws ExceptionIsEmpty {
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
