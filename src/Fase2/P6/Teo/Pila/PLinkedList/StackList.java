package Fase2.P6.Teo.Pila.PLinkedList;

public class StackList <E> { // QueueLink nuevo nombre :v
    private Nodo<E> head;
    private Nodo<E> tail;

    public StackList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(E x){
        Nodo<E> newNode = new Nodo<>(x);
        if(isEmpty()){
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

    }

    public E dequeue(){
        if(isEmpty()){
            return null;
        }
        E ele = head.dato;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return ele;
    }

    // back y front





}
