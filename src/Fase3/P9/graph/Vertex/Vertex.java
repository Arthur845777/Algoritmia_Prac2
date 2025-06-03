package Fase3.P9.graph.Vertex;

import Fase3.P9.graph.Edge.Edge;
import Fase3.P9.graph.ListLinked.LinkedList;

public class Vertex<E> {
    private E data;
    private LinkedList<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new LinkedList<Edge<E>>();
    }

    public E getData() {
        return data;
    }

    public boolean equals(Object o){
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.getData());
        }
        return false;
    }

    public String toString(){
        return this.data + "-->" + this.listAdj.toString() + "\n";
    }

}