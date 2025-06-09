package Fase3.P9.ListsOfEdgeStructures;

public class Edge<V> {
    private Vertex<V> inicio;
    private Vertex<V> fin;
    private int weight = -1;

    public Edge(Vertex<V> inicio, Vertex<V> fin, int weight) {
        this.inicio = inicio;
        this.fin = fin;
        this.weight = weight;
    }

    public Edge(Vertex<V> inicio, Vertex<V> fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null ) {
            return false;
        }

        if(o instanceof Edge<?>){
            Edge<?> e = (Edge<?>) o;
            if(this.inicio.equals(e.inicio) && this.fin.equals(e.fin) && this.weight==e.weight) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsDirect_NP(Edge<V> other) {
        return (this.inicio.equals(other.inicio) && this.fin.equals(other.fin ));
    }

    public boolean equalsUndirected_P(Edge<V> other) {
        return (this.inicio.equals(other.inicio) && this.fin.equals(other.fin ) && this.weight==other.weight) ||
           (this.inicio.equals(other.fin) && this.fin.equals(other.inicio) && this.weight==other.weight);
    }

     public boolean equalsUndirected_NP(Edge<V> other) {
        return (this.inicio.equals(other.inicio) && this.fin.equals(other.fin )) ||
           (this.inicio.equals(other.fin) && this.fin.equals(other.inicio));
    }

    public boolean contiene(Vertex<V> vertex){
        return this.inicio.equals(vertex) || this.fin.equals(vertex);
    }

    public Vertex<V> getInicio() {
        return inicio;
    }

    public Vertex<V> getFin() {
        return fin;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        if (weight != -1) {
            return "(" + inicio.getData() + " -> " + fin.getData() + ", peso=" + weight + ")";
        } else {
            return "(" + inicio.getData() + " -> " + fin.getData() + ")";
        }
    }

}