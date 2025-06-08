package Fase3.P9.AdjacencyLists.Vertex;

import Fase3.P9.AdjacencyLists.Edge.Edge;
import Fase3.P9.Exceptions.ExceptionIsEmpty;
import Fase3.P9.LinkedList.LinkedList;
import Fase3.P9.LinkedList.Node;

public class Vertex<E> {
    private E data;
    private LinkedList<Edge<E>> listAdj; // estos son los que siguen - este usa los edge, osea en si el peso y la referencia
    private boolean visited;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new LinkedList<Edge<E>>();
        this.visited = false;
    }

    public E getData() {
        return data;
    }

    public LinkedList<Edge<E>> getListAdj() {
        return listAdj;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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