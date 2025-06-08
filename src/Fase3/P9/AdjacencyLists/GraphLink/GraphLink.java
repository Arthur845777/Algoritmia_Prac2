package Fase3.P9.AdjacencyLists.GraphLink;

import Fase3.P9.AdjacencyLists.Edge.Edge;
import Fase3.P9.Exceptions.ExceptionIsEmpty;
import Fase3.P9.LinkedList.LinkedList;
import Fase3.P9.LinkedList.LinkedQueue;
import Fase3.P9.LinkedList.Node;
import Fase3.P9.AdjacencyLists.Vertex.Vertex;

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

        if(!listVertex.search(newVertex)){
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

    public void dfs(E data) throws ExceptionIsEmpty { // comentado - no recursivo
        if (data == null) {
            return;
        }

        Vertex<E> startVertex = findVertex(data);

        if (startVertex == null) {
            return;
        }

        resetVisitedFlags();
        LinkedList<Vertex<E>> visitedVertices = dfsRecursive(startVertex, new LinkedList<Vertex<E>>());


        Node<Vertex<E>> current = visitedVertices.getHead();
        while (current != null) {
            System.out.print(current.getData().getData() + " ");
            current = current.getNext();
        }
    }

    private LinkedList<Vertex<E>> dfsRecursive(Vertex<E> vertex, LinkedList<Vertex<E>> visitedList) {
        vertex.setVisited(true);
        visitedList.insertLast(vertex);

        Node<Edge<E>> edgeNode = vertex.getListAdj().getHead();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().getRefDest();

            if (!neighbor.isVisited()) {
                neighbor.setVisited(true);
                dfsRecursive(neighbor, visitedList); // Llamada recursiva
            }
            edgeNode = edgeNode.getNext();
        }

        return visitedList;
    }

    // este es mi metodo aux para explorar y ponerlos todos como que no han sido visitaos
    private void resetVisitedFlags() {
        Node<Vertex<E>> current = listVertex.getHead();

        while (current != null) {
            current.getData().setVisited(false);
            current = current.getNext();
        }
    }

    public void bfs(E data) throws ExceptionIsEmpty {
        if (data == null) {
            return;
        }
        Vertex<E> startVertex = findVertex(data);

        if (startVertex == null) {
            return;
        }

        resetVisitedFlags();

        LinkedQueue<Vertex<E>> queue = new LinkedQueue<>();
        queue.enqueue(startVertex);
        startVertex.setVisited(true);

        while (!queue.isEmpty()) {
            Vertex<E> currentVertex = queue.dequeue();

            System.out.print(currentVertex.getData() + " ");

            Node<Edge<E>> edgeNode = currentVertex.getListAdj().getHead();

            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();

                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    queue.enqueue(neighbor);
                }

                edgeNode = edgeNode.getNext();
            }
        }
    }


    public LinkedList<E> bfsPath(E v, E z) { // repasar al mill millones por ciento
        Vertex<E> start = findVertex(v);
        Vertex<E> end = findVertex(z);
        if (start == null || end == null) {
            return null;
        }

        resetVisitedFlags();
        LinkedQueue<Vertex<E>> queue = new LinkedQueue<>();

        LinkedList<Vertex<E>> vertices = new LinkedList<>();
        LinkedList<Vertex<E>> parents = new LinkedList<>();

        queue.enqueue(start);
        start.setVisited(true);
        vertices.insertLast(start);
        parents.insertLast(null);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Vertex<E> current;
            try {
                current = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                break;
            }

            // Si llegamos al destino
            if (current.equals(end)) {
                found = true;
                break;
            }

            // Explorar vecinos
            Node<Edge<E>> edgeNode = current.getListAdj().getHead();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();

                if (!neighbor.isVisited()) {
                    neighbor.setVisited(true);
                    queue.enqueue(neighbor);

                    // Guardar relación padre-hijo
                    vertices.insertLast(neighbor);
                    parents.insertLast(current);
                }
                edgeNode = edgeNode.getNext();
            }
        }

        // Reconstruir camino si se encontró
        if (found) {
            return reconstructPath(vertices, parents, end);
        }

        return null;
    }

    // Método auxiliar para reconstruir el camino
    private LinkedList<E> reconstructPath(LinkedList<Vertex<E>> vertices, LinkedList<Vertex<E>> parents, Vertex<E> end) {
        LinkedList<E> path = new LinkedList<>();
        Vertex<E> current = end;

        while (current != null) {
            path.insertFirst(current.getData());
            current = findParent(vertices, parents, current);
        }

        return path;
    }

    // Método auxiliar para encontrar el padre
    private Vertex<E> findParent(LinkedList<Vertex<E>> vertices, LinkedList<Vertex<E>> parents, Vertex<E> child) {
        Node<Vertex<E>> vertexNode = vertices.getHead();
        Node<Vertex<E>> parentNode = parents.getHead();

        while (vertexNode != null && parentNode != null) {
            if (vertexNode.getData().equals(child)) {
                return parentNode.getData();
            }
            vertexNode = vertexNode.getNext();
            parentNode = parentNode.getNext();
        }

        return null;
    }

    public LinkedList<E> shortPath(E v, E z) {
        return bfsPath(v, z); // En grafos no ponderados, BFS ya da el camino más corto
    }

    public boolean isConexo() { // sencillito este emtodo xddd
        if (listVertex.isEmpty()) {
            return true; // Un grafo vacío se considera conexo
        }

        resetVisitedFlags();
        Vertex<E> startVertex = listVertex.getHead().getData();
        LinkedList<Vertex<E>> visitedVertices = dfsRecursive(startVertex, new LinkedList<Vertex<E>>());

        return visitedVertices.length() == listVertex.length();
    }

    // Dijsstrack


}

//    // metodos DFS - amplitud
//    public void dfs(E data) throws ExceptionIsEmpty {
//        if (data == null) {
//            return;
//        }
//
//        Vertex<E> startVertex = findVertex(data);
//
//        if (startVertex == null) {
//            return;
//        }
//
//        // aux para poner como no visitados - explicado en teo y en practicas o,o
//        resetVisitedFlags();
//
//        LinkedStack<Vertex<E>> stack = new LinkedStack<>();
//        stack.push(startVertex);
//        startVertex.setVisited(true);
//
//        while (!stack.isEmpty()) {
//            Vertex<E> currentVertex = stack.pop(); // misma q el metodo pasado, agarramos el vertex y chambeamos con eso
//            System.out.print(currentVertex.getData() + " ");
//
//            Node<Edge<E>> edgeNode = currentVertex.getListAdj().getHead(); // agarramos sus seguidos, osea adyacencia
//
//            // el comportamiento es correcto pero es un poco pesa ya que usamos una pila temporal, per oen si esta bien
//            LinkedStack<Vertex<E>> tempStack = new LinkedStack<>();
//
//            while (edgeNode != null) {
//                Vertex<E> neighbor = edgeNode.getData().getRefDest();
//
//                if (!neighbor.isVisited()) {
//                    neighbor.setVisited(true);
//                    tempStack.push(neighbor);
//                }
//                edgeNode = edgeNode.getNext();
//            }
//
//            // Pasar adyacentes a la pila principal (en orden correcto)
//            while (!tempStack.isEmpty()) {
//                stack.push(tempStack.pop());
//            }
//        }
//    }