package Fase3.P9.AdjacencyLists.Edge;

import Fase3.P9.AdjacencyLists.Vertex.Vertex;

public class Edge<E> {
    private Vertex<E> refDest; // referencia de la arista que le continua
    private int weight;

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    // tengo la sospecha de que necesitaremos este constructor mas :v
    public Edge(Vertex<E> refDest) {
        this.refDest = refDest;
        this.weight = -1;
    }

    public boolean equals(Object o){
        if(o instanceof Edge<?>){
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    public Vertex<E> getRefDest() {
        return refDest;
    }

    public int getWeight() {
        return weight;
    }

    public String toString(){
        if(this.weight > -1){
            return this.refDest.getData() + " [" + this.weight + "]";
        } else {
            return refDest.getData() + " , ";
        }
    }

}
