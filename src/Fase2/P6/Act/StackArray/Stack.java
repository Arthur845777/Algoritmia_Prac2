package Fase2.P6.Act.StackArray;

public interface Stack<E> {
    public void push(E e);
    public E pop()throws ExceptionIsEmpty;
    public E top()throws ExceptionIsEmpty;
    public boolean isEmpty();
}