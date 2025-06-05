package Fase3.P9.graph.Vertex;

import Fase3.P9.graph.Edge.Edge;
import Fase3.P9.graph.ListLinked.LinkedList;

public class Vertex<E> {
    private E data;
    private LinkedList<Edge<E>> listAdj; // estos son los que siguen - este usa los edge, osea en si el peso y la referencia

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new LinkedList<Edge<E>>();
    }

    public E getData() {
        return data;
    }

    public LinkedList<Edge<E>> getListAdj() {
        return listAdj;
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