package Fase3.P9.graph.GraphLink;

import Fase3.P9.graph.Edge.Edge;
import Fase3.P9.graph.ListLinked.LinkedList;
import Fase3.P9.graph.ListLinked.Node;
import Fase3.P9.graph.Vertex.Vertex;

public class GraphLink<E> {
    protected LinkedList<Vertex<E>> listVertex;
    protected boolean isDirected; // para permitir los grafos dirigidos o no, igual con la ing, o puede ser bueno o malo

    public GraphLink() {
        listVertex = new LinkedList<Vertex<E>>();
        isDirected = true; // por el momento que sea dirigido nomas o,o
    }

    public GraphLink(boolean isDirected) { // complementario del directo o no
        listVertex = new LinkedList<Vertex<E>>();
        isDirected = isDirected;
    }

    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<E>(data);
        // preguntar, aqui creo que tenemos que meter una validacion o metemos el dato nomas?
        // pondre la validacion entre comillas por siaca, bueno en si es un if o,o

//        if(listVertex.search(newVertex) == -1){
            listVertex.insertLast(newVertex);
//        }
    }

    public void insertEdge(E verOri, E verDes){
        insertEdge(verOri, verDes, -1);
        // como un por siaca, en caso no tenga peso pss el -1, en caso si
        // este metodo no trabajaria, aqui es full interfaz, decicion de cual usar
        // preguntar a la ing
    }

    public void insertEdge(E verOri, E verDes, int weight) { // origin ' destiantion
        if(verOri == null || verDes == null){
            return;
        }

        Vertex<E> vertexOri  = new Vertex<E>(verOri);
        Vertex<E> vertexDes  = new Vertex<E>(verDes);

        Edge<E> edge = new Edge<E>(vertexDes, weight);
        vertexOri.getListAdj().insertLast(edge);

        // igual, preguntar a la ing, aqui va lo del no dirigido, osea registro doble o,o
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
        // creo q esto no es necesario, el finvertex de por si valida si es un nulo
        // con la validacion de mas abajito, comprobamos si se puede seguir o no
        // lo dejare por el momento, preguntar a la ing
        if(data1 == null || data2 == null){
            return false;
        }

        Vertex<E> vertex1  = findVertex(data1);
        Vertex<E> vertex2  = findVertex(data2);

        if(vertex1 == null || vertex2 == null) {
            return false;
        }

        boolean found = searchAdjacencyList(vertex1, vertex2);

        if(!isDirected && !found) { // xd, lo mismo nomas, preguntar
            found = searchAdjacencyList(vertex2, vertex1);
        }

        return found;
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