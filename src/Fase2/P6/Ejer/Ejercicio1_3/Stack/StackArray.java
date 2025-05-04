package Stack;
import ExceptionIsEmpty.ExceptionIsEmpty;

public class StackArray<E> implements Stack<E>{
	private E[] array;
	private int top;
	
	public StackArray(int n) {
		this.array = (E[]) new Object[n];
		this.top = -1;
	}
	
	public void push(E x) {
		if (isFull()) return;
		array[++top] = x;
	}

	public E pop() throws ExceptionIsEmpty {
		if(isEmpty()) throw new ExceptionIsEmpty("Empty Stack");
		E aux = array[top];
		array[top--] = null;
		return aux;
	}

	public E top() throws ExceptionIsEmpty {
		if (isEmpty()) throw new ExceptionIsEmpty("Empty Stack");
		E aux = array[top];
		return aux;
	}

	public boolean isEmpty() {
		if(top == -1) return true;
		return false;
	}
	
	public boolean isFull() {
		return top == array.length - 1;
	}
}
