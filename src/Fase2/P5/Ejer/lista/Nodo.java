package Fase2.P5.Ejer.lista;

public class Nodo <T extends Comparable<T>>{
    public T dato;
    public Nodo<T> before;
    public Nodo<T> next;

    public Nodo(T datito){
        this.dato= datito;
        this.next =null;
        this.before= null;
    }
}

