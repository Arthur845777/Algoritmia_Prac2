package Fase3.P9.graph.GraphLink;

import Fase3.P9.graph.Edge.Edge;
import Fase3.P9.graph.ListLinked.LinkedList;
import Fase3.P9.graph.ListLinked.Node;
import Fase3.P9.graph.Vertex.Vertex;

public class GraphLink<E> {
    protected LinkedList<Vertex<E>> listVertex; // lista de vertices, estos son los que bajan
    protected boolean isDirected; // dirigido o no
    protected boolean isWeighted; // peso o no

    public GraphLink(boolean isDirected, boolean isWeighted) {
        listVertex = new LinkedList<Vertex<E>>();
        isDirected = isDirected;
        this.isWeighted = isWeighted;
    }

    public void insertVertex(E data) {
        if(data == null) {
            return;
        }

        Vertex<E> newVertex = new Vertex<E>(data);

        if(listVertex.search(newVertex) == -1){
            listVertex.insertLast(newVertex);
        }
    }

    public void insertEdge(E verOri, E verDes){
        insertEdge(verOri, verDes, -1);
    }

    public void insertEdge(E verOri, E verDes, int weight) { // origin ' destiantion
        if(verOri == null || verDes == null){
            return;
        }

        Vertex<E> vertexOri  = findVertex(verOri);
        Vertex<E> vertexDes  = findVertex(verDes);

        if(vertexOri == null || vertexDes == null) {
            return;
        }

        if(isWeighted){ // Aqui el peso esta de mas, es mas, genero redundancia con mi constructor, pero venga, ya vemos como hacerlo
            Edge<E> edge = new Edge<E>(vertexDes, weight);
            vertexOri.getListAdj().insertLast(edge);
        } else {
            Edge<E> edge = new Edge<E>(vertexDes);
            vertexOri.getListAdj().insertLast(edge);
        }

        if(!isDirected){
            Edge<E> edgeInverse = new Edge<E>(vertexDes, weight);
            vertexDes.getListAdj().insertLast(edgeInverse);
        }
    }

    public boolean searchVertex(E data){
        if(data == null){
            return false;
        }
        if(findVertex(data) == null){
            return false;
        } else {
            return true;
        }
    }

    private Vertex<E> findVertex(E data) {
        Node<Vertex<E>> current = listVertex.getHead();

        while(current != null) {
            if(current.getData().getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean searchEdge(E data1, E data2) {
        // si es necesario :v
        if(data1 == null || data2 == null){
            return false;
        }

        Vertex<E> vertex1  = findVertex(data1);
        Vertex<E> vertex2  = findVertex(data2);

        if(vertex1 == null || vertex2 == null) {
            return false;
        }

        boolean found = searchAdjacencyList(vertex1, vertex2);

        if(!isDirected && !found) { // xd, lo mismo nomas, preguntar, justificacion, si es necesario ya que puede o no ser directo
            found = searchAdjacencyList(vertex2, vertex1);
        }

        return found;
    }

    public boolean removeVertex(E data) { // ya fue ya xd ' re mareado
        if (data == null) {
            return false;
        }

        Vertex<E> vertexToRemove = findVertex(data);

        if (vertexToRemove == null) {
            return false;
        }

        if (isDirected) {
            vertexToRemove.getListAdj().destroyList();

            // me voy a marear horrible xd
            // aca tomamos la lista de los vertices, esto para entrar a cada uno y poder borrar las referencias

            Node<Vertex<E>> currentVertex = listVertex.getHead();

            while (currentVertex != null) {
                Vertex<E> edge = currentVertex.getData();
                Node<Edge<E>> edgeNode = edge.getListAdj().getHead();

                while (edgeNode != null) { // simple, recorremos la lista de aristas hasta hallar el vertice respectivo y borramos
                    if (edgeNode.getData().getRefDest().equals(vertexToRemove)) {
                        edge.getListAdj().removeNode(edgeNode.getData());
                        break;
                    }

                    edgeNode = edgeNode.getNext();
                }
                currentVertex = currentVertex.getNext(); // obvio o,o - el dato puede que no este en un solo vertex, puede q este en varios, razon de la iteracion
            }
        } else {
            Node<Edge<E>> edgeNode = vertexToRemove.getListAdj().getHead();

            while (edgeNode != null) {
                Vertex<E>  vertexConnected = edgeNode.getData().getRefDest();
                vertexConnected.getListAdj().removeNode(new Edge<E>(vertexToRemove));
                edgeNode = edgeNode.getNext();

            }
        }

        return listVertex.removeNode(vertexToRemove);
    }

    public boolean removeEdge(E verOri, E verDes) {
        if (verOri == null || verDes == null) {
            return false;
        }

        Vertex<E> vertexOri = findVertex(verOri);
        Vertex<E> vertexDes = findVertex(verDes);

        if (vertexOri == null || vertexDes == null) {
            return false;
        }

        // dirigido ori -> des
        boolean removed = vertexOri.getListAdj().removeNode(new Edge<>(vertexDes));

        // no dirigido -> eliminar des -> ori
        if (!isDirected && removed) {
            vertexDes.getListAdj().removeNode(new Edge<>(vertexOri));
        }

        return removed;

    }

    // aqui no es necesario preguntar, esto si lo veo necesario, es como un metodo extra entre comillas
    private boolean searchAdjacencyList(Vertex<E> item1, Vertex<E> item2) {

        LinkedList<Edge<E>> adjList1 = item1.getListAdj();

        Node<Edge<E>> current = adjList1.getHead();

        while(current != null) {
            if(current.getData().getRefDest().equals(item2)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }



}