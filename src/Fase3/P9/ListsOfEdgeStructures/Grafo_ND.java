package Fase3.P9.ListsOfEdgeStructures;
import Fase3.P9.Exceptions.ExceptionIsEmpty;
import Fase3.P9.LinkedList.*;

public class Grafo_ND<E> {
    protected LinkedList<Vertex<E>> vertices;
    protected LinkedList<Edge<E>> aristas;
    protected boolean isDirected; // dirigido o no
    protected boolean isWeighted = false; // peso o no

    public Grafo_ND(boolean isWeighted, boolean isDirected){
        vertices = new LinkedList<>();
        aristas = new LinkedList<>();
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
    }

    public boolean getWeighted(){
        return isWeighted;
    }

    public boolean searchVertex(E dato){
        Vertex<E> vertice = new Vertex<E>(dato);        
        return vertices.search(vertice);
    }

    private Vertex<E> findVertex(E data) {
        Node<Vertex<E>> current = vertices.getHead();

        while(current != null) {
            if(current.getData().getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    // ya quedo, ahora esta resumido search check
    public boolean searchEdge_NP(E inicio, E fin){
        return searchEdge_P(inicio, fin, -1);
    }

    public boolean searchEdge_P(E inicio, E fin, int weight){
        if(inicio == null || fin == null){
            return false;
        }

        Vertex<E> ini = new Vertex<E>(inicio);
        Vertex<E> fi = new Vertex<E>(fin);
        Edge<E> nueva;

        if(isWeighted){
            nueva = new Edge<>(ini, fi,weight);
        } else {
            nueva = new Edge<>(ini, fi);
        }

        Node<Edge<E>> current = aristas.getHead();

        while(current != null ){
            if(current.getData().equals(nueva)){ // equalsDirect_NP en relacion a esta es lo mismo
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void insertVertex(E dato){
        if(dato == null) {
            return;
        }

        Vertex<E> vertice = new Vertex<E>(dato);

        if(searchVertex(dato)){ //en caso halla datos repetidos esto lo cambio por el bool
            return;
        }

        if(vertices.isEmpty()){ // esto es buneo, no es como List de adyacencia, esto maneja otra estructura, esta condicional esta genial
            vertices.insertFirst(vertice);
        }else{ // creo si se puede borrar, el insertLasst deberia ser capaz de abarcar los 2 casos
            vertices.insertLast(vertice);
        }
    }

    // ya modificado
    public void insertEdge_NP(E inicio, E fin){
        insertEdge_P(inicio, fin, -1);
    }

    public void insertEdge_P(E inicio, E fin, int weight){
        if(inicio == null || fin == null){
            return;
        }

        Vertex<E> ini = findVertex(inicio);
        Vertex<E> fi = findVertex(fin);


        // ojo, esta condicion cumple para ambos casos, si directo o no
        // directo -> ya que si no tienen regsitro, lo registra
        // no directo -> sii encuentra uno, si infiere que el otro tambien
        // condicion good
        if(searchEdge_P(inicio, fin, weight)){
            return;
        }

        if(isWeighted){
            Edge<E> nueva = new Edge<>(ini, fi,weight);
            aristas.insertLast(nueva);
        } else {
            Edge<E> nueva = new Edge<>(ini, fi);
            aristas.insertLast(nueva);
        }

        if(!isDirected){
            Edge<E> nueva = new Edge<>(fi, ini);
            aristas.insertLast(nueva);
        }

    }

    // aqui es lo que me interesa o,o
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
            } else if (arista.getFin().equals(vertex)) {
                Vertex<E> adyacente = arista.getInicio();
                if (!adyacentes.search(adyacente)) {
                    adyacentes.insertLast(adyacente);
                }
            }
            current = current.getNext();
        }
        return adyacentes;
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

    public int grado(E dato) {
        Vertex<E> v = new Vertex<>(dato);
        int count = 0;
        Node<Edge<E>> current = aristas.getHead();
        while (current != null) {
            Edge<E> e = current.getData();
            if (e.getInicio().equals(v) || e.getFin().equals(v)) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    public boolean esCamino() {
        int numVertices = vertices.length();
        int numAristas = aristas.length();
    
        if (numAristas != numVertices - 1) {
            return false;
        }
        int gradoUno = 0;
        int gradoDos = 0;
    
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            E dato = current.getData().getData();
            int grado = grado(dato);
        
            if (grado == 1) {
                gradoUno++;
            } else if (grado == 2) {
                gradoDos++;
            } else {
                return false; 
            }
        
            current = current.getNext();
        }
        return gradoUno == 2 && gradoDos == (numVertices - 2);
    }

    public boolean esCiclo() {
        int numVertices = vertices.length();  
        int numAristas = aristas.length();
    
        if (numAristas != numVertices || numVertices < 3) {
            return false;
        }
    
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            if (grado(current.getData().getData()) != 2) {
                return false;
            }
            current = current.getNext();
        }
    
        return true;
    }

    public boolean esRueda() {
        int numVertices = vertices.length();
        int numAristas = aristas.length();

        // Validación rápida: mínimo W₃ (4 vértices), aristas = 2(n-1)
        if (numVertices < 4 || numAristas != 2 * (numVertices - 1)) {
            return false;
        }

        int verticesBorde = numVertices - 1;
        int centros = 0;
        int bordeVertices = 0;

        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            int grado = grado(current.getData().getData());

            if (grado == verticesBorde) {
                centros++;
            } else if (grado == 3) {
                bordeVertices++;
            } else {
                return false; // Grado inválido para rueda
            }

            current = current.getNext();
        }

        // Debe haber exactamente 1 centro y (n-1) vértices de borde
        return centros == 1 && bordeVertices == verticesBorde;
    }

    public boolean esCompleto() {
        int numVertices = vertices.length();
        int numAristas = aristas.length();

        // Validación rápida: Kₙ tiene n(n-1)/2 aristas
        int aristasEsperadas = (numVertices * (numVertices - 1)) / 2;
        if (numAristas != aristasEsperadas || numVertices < 1) {
            return false;
        }

        // Caso especial: K₁ (un solo vértice)
        if (numVertices == 1) {
            return numAristas == 0;
        }

        int gradoEsperado = numVertices - 1;

        // Verificar que todos los vértices tengan grado (n-1)
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            if (grado(current.getData().getData()) != gradoEsperado) {
                return false;
            }
            current = current.getNext();
        }

        return true;
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

    public int gradoDirigido(E dato) {
        return gradoSalida(dato) + gradoEntrada(dato);
    }
}
