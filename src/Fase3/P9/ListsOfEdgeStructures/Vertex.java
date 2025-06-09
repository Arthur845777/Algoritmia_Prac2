package Fase3.P9.ListsOfEdgeStructures;

import java.util.Objects;

public class Vertex<V>{
    protected V data;
    protected boolean visited;

    public Vertex(V data){
        this.data = data;
        this.visited = false;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }


}