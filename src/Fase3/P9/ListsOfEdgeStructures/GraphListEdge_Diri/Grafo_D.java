package Fase3.P9.ListsOfEdgeStructures.GraphListEdge_Diri;

import Fase3.P9.Exceptions.ExceptionIsEmpty;
import Fase3.P9.LinkedList.*;
import Fase3.P9.ListsOfEdgeStructures.GraphListEdge_ND.*;

public class Grafo_D<E> extends Grafo_ND<E> {
    public Grafo_D(boolean ponderado) {
        super(ponderado);
    }

    @Override
    public boolean searchEdge_NP(E inicio, E fin){
        Vertex<E> ini = new Vertex<E>(inicio);
        Vertex<E> fi = new Vertex<E>(fin);
        Edge<E> nueva = new Edge<>(ini, fi);
        Node<Edge<E>> current = aristas.getHead();
        while(current != null ){
            if(current.getData().equalsDirect_NP(nueva)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean searchEdge_P(E inicio, E fin, int weight){
        Vertex<E> ini = new Vertex<E>(inicio);
        Vertex<E> fi = new Vertex<E>(fin);
        Edge<E> nueva = new Edge<>(ini, fi,weight);
        Node<Edge<E>> current = aristas.getHead();
        while(current != null ){
            if(current.getData().equals(nueva)){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void insertEdge_P(E inicio, E fin, int weight){
        if(!searchVertex(inicio) || !searchVertex(fin)){
            return;
        }

        Vertex<E> ini = new Vertex<E>(inicio);
        Vertex<E> fi = new Vertex<E>(fin);
        Edge<E> nueva = new Edge<>(ini, fi,weight);
        if(searchEdge_P(inicio, fin, weight)){
            return;
        }
        
        if(aristas.isEmpty()){
            aristas.insertFirst(nueva);
        }else{
            aristas.insertLast(nueva);
        }
        
    }

    @Override
    public void insertEdge_NP(E inicio, E fin){
        if(!searchVertex(inicio) || !searchVertex(fin)){
            return;
        }
        Vertex<E> ini = new Vertex<E>(inicio);
        Vertex<E> fi = new Vertex<E>(fin);
        Edge<E> nueva = new Edge<>(ini, fi);
        if(searchEdge_NP(inicio, fin)){
            return;
        }
        
        if(aristas.isEmpty()){
            aristas.insertFirst(nueva);
        }else{
            aristas.insertLast(nueva);
        }
    }

    public void dfs(E startVertex) {
        if (!searchVertex(startVertex)) {
            System.out.println("El vértice " + startVertex + " no existe en el grafo");
            return;
        }

        LinkedList<Vertex<E>> visitados = new LinkedList<>();    
        LinkedStack<Vertex<E>> pila = new LinkedStack<>();
        try {
            Vertex<E> startVertexObj = new Vertex<>(startVertex);
            pila.push(startVertexObj);
            System.out.print("Recorrido DFS (Profundidad) desde " + startVertex + ": ");
            while (!pila.isEmpty()) {
                Vertex<E> verticeActual = pila.pop();

                if (!visitados.search(verticeActual)) {
                    visitados.insertLast(verticeActual);
                    System.out.print(verticeActual.getData() + " ");

                    LinkedList<Vertex<E>> vecinos = getAdjacentVertices(verticeActual);
                    Node<Vertex<E>> current = vecinos.getHead();
                    LinkedStack<Vertex<E>> pilaTemp = new LinkedStack<>();
                    while (current != null) {
                        Vertex<E> vecino = current.getData();
                        if (!visitados.search(vecino)) {
                            pilaTemp.push(vecino);
                        }
                        current = current.getNext();
                    }
                    while (!pilaTemp.isEmpty()) {
                        pila.push(pilaTemp.pop());
                    }
                }
            }
            System.out.println();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error en DFS: " + e.getMessage());
        }
    }


    public void bfs(E startVertex) {
        if (!searchVertex(startVertex)) {
            System.out.println("El vértice " + startVertex + " no existe en el grafo");
            return;
        }

        LinkedList<Vertex<E>> visitados = new LinkedList<>();
        LinkedQueue<Vertex<E>> cola = new LinkedQueue<>();

        try {
            Vertex<E> startVertexObj = new Vertex<>(startVertex);
        
            visitados.insertLast(startVertexObj);
            cola.enqueue(startVertexObj);
    
            System.out.print("Recorrido BFS (Amplitud) desde " + startVertex + ": ");
    
            while (!cola.isEmpty()) {
                Vertex<E> verticeActual = cola.dequeue();
                System.out.print(verticeActual.getData() + " ");
                LinkedList<Vertex<E>> vecinos = getAdjacentVertices(verticeActual);
        
                Node<Vertex<E>> current = vecinos.getHead();
                while (current != null) {
                    Vertex<E> vecino = current.getData();
            
                    if (!visitados.search(vecino)) {
                        visitados.insertLast(vecino);
                        cola.enqueue(vecino);
                    }
            
                    current = current.getNext();
                }
            }
            System.out.println();
    
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error en BFS: " + e.getMessage());
        }
    }

    private LinkedList<Vertex<E>> getAdjacentVertices(Vertex<E> vertex) {
        LinkedList<Vertex<E>> adyacentes = new LinkedList<>();
        Node<Edge<E>> current = aristas.getHead();
        while (current != null) {
            Edge<E> arista = current.getData();
            if (arista.getInicio().equals(vertex)) {
                Vertex<E> adyacente = arista.getFin();
                if (!adyacentes.search(adyacente)) {
                     adyacentes.insertLast(adyacente);
                }
            }
            current = current.getNext();
        }
        return adyacentes;
    }

    public int gradoEntrada(E dato) {
        Vertex<E> v = new Vertex<>(dato);
        int count = 0;
        Node<Edge<E>> current = aristas.getHead();
        while (current != null) {
            if (current.getData().getFin().equals(v)) count++;
            current = current.getNext();
        }
        return count;
    }

    public int gradoSalida(E dato) {
        Vertex<E> v = new Vertex<>(dato);
        int count = 0;
        Node<Edge<E>> current = aristas.getHead();
        while (current != null) {
            if (current.getData().getInicio().equals(v)) count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public int grado(E dato) {
        return gradoSalida(dato) + gradoEntrada(dato);
    }
}
