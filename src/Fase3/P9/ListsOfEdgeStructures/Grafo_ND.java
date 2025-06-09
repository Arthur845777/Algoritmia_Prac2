package Fase3.P9.ListsOfEdgeStructures;
import Fase3.P9.Exceptions.ExceptionIsEmpty;
import Fase3.P9.LinkedList.*;

public class Grafo_ND<E> {
    protected LinkedList<Vertex<E>> vertices;
    protected LinkedList<Edge<E>> aristas;
    protected boolean isDirected; // dirigido o no
    protected boolean isWeighted; // peso o no

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

    public void dfs(E startVertex) {
        if (!searchVertex(startVertex)) return;

        resetVisitedFlags();
        dfsRecursive(findVertex(startVertex));
        System.out.println();
    }

    private void dfsRecursive(Vertex<E> current) {
        current.setVisited(true);
        System.out.print(current.getData() + " "); // Procesar vértice

        Node<Edge<E>> edgeNode = aristas.getHead();
        while (edgeNode != null) {
            Edge<E> edge = edgeNode.getData();
            Vertex<E> neighbor = null;

            if (edge.getInicio().equals(current) && !edge.getFin().isVisited()) {
                neighbor = edge.getFin();
            } else if (!isDirected && edge.getFin().equals(current) && !edge.getInicio().isVisited()) {
                neighbor = edge.getInicio();
            }

            if (neighbor != null) {
                dfsRecursive(neighbor);
            }
            edgeNode = edgeNode.getNext();
        }
    }

    public void bfs(E startVertex) throws ExceptionIsEmpty {
        if (!searchVertex(startVertex)) return;

        resetVisitedFlags();
        LinkedQueue<Vertex<E>> cola = new LinkedQueue<>();
        Vertex<E> inicio = findVertex(startVertex);

        inicio.setVisited(true);
        cola.enqueue(inicio);

        System.out.print("BFS: ");

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.dequeue();
            System.out.print(actual.getData() + " ");

            Node<Edge<E>> edgeNode = aristas.getHead();
            while (edgeNode != null) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> vecino = null;

                if (edge.getInicio().equals(actual) && !edge.getFin().isVisited()) {
                    vecino = edge.getFin();
                } else if (!isDirected && edge.getFin().equals(actual) && !edge.getInicio().isVisited()) {
                    vecino = edge.getInicio();
                }

                if (vecino != null) {
                    vecino.setVisited(true);
                    cola.enqueue(vecino);
                }
                edgeNode = edgeNode.getNext();
            }
        }
        System.out.println();
    }

    private void resetVisitedFlags() {
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            current.getData().setVisited(false);
            current = current.getNext();
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

    public boolean esConexo() throws ExceptionIsEmpty {
        if (vertices.isEmpty()) return true; // Grafo vacío se considera conexo

        resetVisitedFlags();
        LinkedQueue<Vertex<E>> cola = new LinkedQueue<>();
        int verticesVisitados = 0;

        // Empezamos desde el primer vértice disponible
        Vertex<E> inicio = vertices.getHead().getData();
        inicio.setVisited(true);
        cola.enqueue(inicio);
        verticesVisitados++;

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.dequeue();
            LinkedList<Vertex<E>> vecinos = getAdjacentVertices(actual);
            Node<Vertex<E>> vecinoNode = vecinos.getHead();

            while (vecinoNode != null) {
                Vertex<E> vecino = vecinoNode.getData();
                if (!vecino.isVisited()) {
                    vecino.setVisited(true);
                    cola.enqueue(vecino);
                    verticesVisitados++;
                }
                vecinoNode = vecinoNode.getNext();
            }
        }

        return verticesVisitados == vertices.length();
    }

    public boolean esCamino() throws ExceptionIsEmpty {
        int numVertices = vertices.length();

        if (numVertices == 0) {
            return false;
        }

        if (aristas.length() != numVertices - 1) {
            return false;
        }

        int verticesGrado1 = 0;

        Node<Vertex<E>> current = vertices.getHead();

        while (current != null) {
            int gradoActual = grado(current.getData().getData());

            if (gradoActual == 1) {
                verticesGrado1++;
            } else if (gradoActual != 2) {
                return false;
            }

            current = current.getNext();
        }

        return verticesGrado1 == 2 && esConexo();
    }

    public boolean esCiclo() throws ExceptionIsEmpty {
        int numVertices = vertices.length();
        if (numVertices < 3) return false;

        if (aristas.length() != numVertices) {
            return false;
        }

        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            if (grado(current.getData().getData()) != 2) {
                return false;
            }
            current = current.getNext();
        }

        return esConexo();
    }

    private LinkedList<Vertex<E>> getAdjacentVertices(Vertex<E> vertex) {
        LinkedList<Vertex<E>> adyacentes = new LinkedList<>();
        Node<Edge<E>> current = aristas.getHead();

        while (current != null) {
            Edge<E> arista = current.getData();
            if (arista.getInicio().equals(vertex)) {
                adyacentes.insertLast(arista.getFin());
            }
            else if (!isDirected && arista.getFin().equals(vertex)) {
                adyacentes.insertLast(arista.getInicio());
            }
            current = current.getNext();
        }
        return adyacentes;
    }

    public boolean esRueda() throws ExceptionIsEmpty {
        int numVertices = vertices.length();

        // Una rueda debe tener al menos 4 vértices (W₃)
        if (numVertices < 4 || !this.esConexo()) {
            return false;
        }

        // Contadores para vértices centrales y de borde
        int centroCount = 0;
        int bordeCount = 0;
        int gradoEsperadoCentro = numVertices - 1;
        int gradoEsperadoBorde = 3; // Los vértices de borde están conectados al centro y a 2 vecinos

        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            int gradoActual = grado(current.getData().getData());

            if (gradoActual == gradoEsperadoCentro) {
                centroCount++;
            } else if (gradoActual == gradoEsperadoBorde) {
                bordeCount++;
            } else {
                return false; // Grado no coincide con rueda
            }

            current = current.getNext();
        }

        // Debe haber exactamente 1 centro y (n-1) vértices de borde
        return centroCount == 1 && bordeCount == numVertices - 1;
    }

    public boolean esCompleto() {
        int numVertices = vertices.length();

        // Grafo completo debe tener al menos 1 vértice
        if (numVertices < 1) {
            return false;
        }

        // Caso especial: K₁ (un solo vértice sin aristas)
        if (numVertices == 1) {
            return aristas.length() == 0;
        }

        // Para Kₙ, número de aristas debe ser n(n-1)/2 (no dirigido)
        int aristasEsperadas = numVertices * (numVertices - 1) / 2;
        if (aristas.length() != aristasEsperadas) {
            return false;
        }

        // Todos los vértices deben tener grado (n-1)
        int gradoEsperado = numVertices - 1;
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            if (grado(current.getData().getData()) != gradoEsperado) {
                return false;
            }
            current = current.getNext();
        }

        return true;
    }

    // todo isomorfo:
    public boolean isIsomorfo(Grafo_ND<E> otroGrafo) {
        // Verificación básica de tamaños
        if (this.vertices.length() != otroGrafo.vertices.length() ||
                this.aristas.length() != otroGrafo.aristas.length()) {
            return false;
        }

        // Obtener secuencias de grados de ambos grafos
        LinkedList<Integer> gradosThis = obtenerSecuenciaGrados();
        LinkedList<Integer> gradosOtro = otroGrafo.obtenerSecuenciaGrados();

        // Ordenar ambas listas
        gradosThis = ordenarListaGrados(gradosThis);
        gradosOtro = ordenarListaGrados(gradosOtro);

        // Comparar las listas ordenadas
        return listasIguales(gradosThis, gradosOtro);
    }

    private LinkedList<Integer> obtenerSecuenciaGrados() {
        LinkedList<Integer> grados = new LinkedList<>();
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            grados.insertLast(grado(current.getData().getData()));
            current = current.getNext();
        }
        return grados;
    }

    private LinkedList<Integer> ordenarListaGrados(LinkedList<Integer> lista) {
        // Implementación de ordenación burbuja para listas enlazadas
        boolean swapped;
        do {
            swapped = false;
            Node<Integer> current = lista.getHead();
            while (current != null && current.getNext() != null) {
                if (current.getData() > current.getNext().getData()) {
                    // Intercambiar valores
                    int temp = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);
        return lista;
    }

    private boolean listasIguales(LinkedList<Integer> lista1, LinkedList<Integer> lista2) {
        if (lista1.length() != lista2.length()) return false;

        Node<Integer> node1 = lista1.getHead();
        Node<Integer> node2 = lista2.getHead();

        while (node1 != null && node2 != null) {
            if (!node1.getData().equals(node2.getData())) {
                return false;
            }
            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return true;
    }

    // todo plano
    public boolean esPlano() {
        // Verificación usando fórmula de Euler para grafos conexos planos: V - E + F = 2
        try {
            if (!this.esConexo()) return false;
        } catch (ExceptionIsEmpty e) {
            return false;
        }

        int V = vertices.length();
        int E = aristas.length();

        // Para grafos simples conexos planos con V >= 3
        if (V >= 3 && E > 3 * V - 6) {
            return false;
        }

        // Verificación adicional sin usar arrays
        // Contar caras (F) usando la fórmula de Euler
        // F = 2 - V + E (para grafos conexos planos)
        int F = 2 - V + E;

        // Debe haber al menos una cara (la exterior)
        return F >= 1;
    }

    // todo auto complementario
    public boolean esAutoComplementario() {
        // Crear el grafo complemento
        Grafo_ND<E> complemento = new Grafo_ND<>(false, false);

        // Insertar todos los vértices
        Node<Vertex<E>> vertexNode = vertices.getHead();
        while (vertexNode != null) {
            complemento.insertVertex(vertexNode.getData().getData());
            vertexNode = vertexNode.getNext();
        }

        // Insertar aristas que no están en el grafo original
        Node<Vertex<E>> current = vertices.getHead();
        while (current != null) {
            Vertex<E> v1 = current.getData();
            Node<Vertex<E>> next = current.getNext();

            while (next != null) {
                Vertex<E> v2 = next.getData();
                E dato1 = v1.getData();
                E dato2 = v2.getData();

                if (!this.searchEdge_NP(dato1, dato2)) {
                    complemento.insertEdge_NP(dato1, dato2);
                }

                next = next.getNext();
            }

            current = current.getNext();
        }

        // Verificar isomorfismo entre el grafo original y su complemento
        return this.isIsomorfo(complemento);
    }

}
