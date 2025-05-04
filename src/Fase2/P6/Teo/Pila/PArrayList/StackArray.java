package Fase2.P6.Teo.Pila.PArrayList;

public class StackArray <E> {
    private E[] stack;
    private int top;

    public StackArray() {
        stack = (E[]) new Object[10];
        top = -1;
    }

    public void push(E item) {
        if(top < stack.length - 1) {
            stack[++top] = item;
        }
    }

    public E pop() throws ExceptionIsEmpty {
        if(isEmpy()) {
            throw new ExceptionIsEmpty();
        }
        return stack[top--];
    }

    public boolean isEmpy(){
        return top == -1;
    }

}


class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty() {
        super("El stack esta vacio");
    }
}

