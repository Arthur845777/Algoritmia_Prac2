package Fase2.P5.Ejer.LinkedList;

public class Node <T extends Comparable<T>>{
    public T data;
    public Node<T> before;
    public Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
        this.before= null;
    }
}

