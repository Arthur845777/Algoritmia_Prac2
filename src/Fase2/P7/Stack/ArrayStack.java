package Fase2.P7.Stack;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class ArrayStack<E> implements Stack<E> {
    private E[] stack;
    private int top;

    public ArrayStack() {
        stack = (E[]) new Object[10];
        top = -1;
    }

    public void push(E e) {
        if(isFull()){
            System.out.println("Stack Full");
            return;
        }
        if(top < stack.length - 1){
            stack[++top] = e;
        }
    }

    public E pop() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        E auxStack = stack[top];
        stack[top--] = null;
        return auxStack;
//        return stack[top--] = null;
    }

    public E top() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        return stack[top];
    }

    public void destroyStack(){
        for (int i = 0; i <= top; i++) {
            stack[i] = null;
        }
        top = -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }

}
