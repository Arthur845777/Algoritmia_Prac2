package Fase2.P6.Queue;

import Fase2.P6.ExceptionIsEmpty.ExceptionIsEmpty;

public class ArrayQueue<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int back;
    private static int counter = 0;

    public ArrayQueue(int n) {
        this.array = (E[]) new Object[n + 1];
        this.front = 0;
        this.back = 0;
    }

    public void enqueue(E x) {
        if (isFull()) {
            return;
        } else {
            array[back] = x; // se puede agregar un ++ despues de back
            back = (back + 1) % array.length; // esta de mas esta linea o el quitas nomas el +1
        }
        counter++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        } else {
            E item = array[front];
            array[front] = null; // se puede agregar un ++ y se cambia la direccion   
            front = (front + 1) % array.length; // igual esta linea esta de mas
            counter--;
            return item;
        }
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        } else {
            return array[front];
        }
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        int lastIndex = (back - 1 + array.length) % array.length;
        return array[lastIndex];
    }

    public boolean isEmpty() {
        return front == back;
    }

    public boolean isFull() {
        return (back + 1) % array.length == front;
    }

    public int size() {
        return counter;
    }
}
