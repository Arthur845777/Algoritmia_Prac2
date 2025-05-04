package Fase2.P6.Act.StackArray;

public class ArrayStack<E> implements Stack<E> {
    private E[] stack;
    private int top;

    public ArrayStack() {
        stack = (E[]) new Object[10];
        top = -1;
    }

    public void push(E e) {
        if(top == stack.length - 1){
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
        return stack[top--];
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

    public int isFull(){
        return top + 1;
    }

}
