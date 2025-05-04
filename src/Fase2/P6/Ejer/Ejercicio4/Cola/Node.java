package Fase2.P6.Ejer.Ejercicio4.Cola;

public class Node <T extends Comparable<T>>{
    public T data;
    public Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }
}

