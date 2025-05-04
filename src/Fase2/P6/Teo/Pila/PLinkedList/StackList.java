package Fase2.P6.Teo.Pila.PLinkedList;

public class StackList <E> { // QueueLink nuevo nombre :v
    private Nodo<E> first;
    private Nodo<E> tail;

    public StackList() {
        first = null;
        tail = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(E x){
        Nodo<E> newNode = new Nodo<>(x);
        if(isEmpty()){
            first = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

    }

    public E dequeue(){
        if(isEmpty()){
            return null;
        }
        E ele = first.dato;
        first = first.next;
        if(first == null){
            tail = null;
        }
        return ele;
    }

    // back y front





}
