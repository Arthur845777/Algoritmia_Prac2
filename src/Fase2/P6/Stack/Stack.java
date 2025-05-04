package Fase2.P6.Stack;
import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public interface Stack<E> {
    void push(E e);
    E pop()throws ExceptionIsEmpty;
    E top()throws ExceptionIsEmpty;
    boolean isEmpty();
}