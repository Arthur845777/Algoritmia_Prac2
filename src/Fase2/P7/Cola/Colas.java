package Fase2.P7.Cola;
import Fase2.P7.Exceptions.ExceptionIsEmpty;

public class Colas<E> implements Queue<E> {
    private Node<E> head; 
    private Node<E> tail; 
    private int size;

    public Colas() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(E x) {
        Node<E> nuevoNodo = new Node<E>(x);
        
        if (isEmpty()) {
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.next = nuevoNodo;
            tail = nuevoNodo;
        }
        
        size++; 
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        
        E elemento = head.data;
        head = head.next;
        
        if (head == null) {
            tail = null;
        }
        
        size--; 
        return elemento;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return head.data;
    }

    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return tail.data;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        return size;
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}