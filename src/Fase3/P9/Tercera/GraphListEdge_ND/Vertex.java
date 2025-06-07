package Fase3.P9.Tercera.GraphListEdge_ND;

public class Vertex<V>{
    protected V data;

    public Vertex(V data){
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;
        if(this.data.equals(vertex.data)){
            return true;
        }
        return false;
    }

}