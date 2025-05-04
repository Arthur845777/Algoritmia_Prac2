package Stack;
import Node.Node;
import ExceptionIsEmpty.ExceptionIsEmpty;

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
		if(isEmpty()) throw new ExceptionIsEmpty("Empty stack");
		E aux = top.getData();
		top = top.getNext();
		return aux;
	}

	public E top() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty("Empty stack");
		return top.getData();
	}

	public boolean isEmpty() {
		if(top == null) return true;
		return false;
	}

}
