package Fase2.P6.Ejer.Ejercicio4.Cola;
import Fase2.P6.Ejer.Ejercicio4.Excepciones.ExceptionIsEmpty;

public class Colas<E extends Comparable<E>> implements Queue<E>{
    private Node<E> tail;
    private Node<E> head;

    public Colas(){
        this.head=null;
        this.tail=null;
    }

    public void enqueue(E x){
        Node<E> nuevoNodo = new Node<E>(x);
        if(head==null){
            head = nuevoNodo;
        }else{
            nuevoNodo.next=head;
            head=nuevoNodo;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        E elemento = head.data;
        head=head.next;
        return elemento;
    }

    public E front() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        return head.data;
    }

    public E back() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        return tail.data;
    }

    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        return false;
    }

}
