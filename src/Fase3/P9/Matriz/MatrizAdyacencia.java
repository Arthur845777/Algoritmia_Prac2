package Fase3.P9.Matriz;

public class MatrizAdyacencia {
    private int[][] matriz;
    private int numVertices;

    public MatrizAdyacencia(int numVertices) {
        this.numVertices = numVertices;
        this.matriz = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    public void insertarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matriz[origen][destino] = 1;
        } else {
            System.out.println("error o,o");
        }
    }

    public void eliminarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matriz[origen][destino] = 0;
        } else {
            System.out.println("error o,o");
        }
    }

    public boolean existeArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            return matriz[origen][destino] == 1;
        }
        return false;
    }

    public void mostrarMatriz() {
        System.out.println("Matriz de Adyacencia:");
        System.out.print("   ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // esto Si ha sido IA nomas, de aqui en adelante, osea para abajo o,o
    // Método main para probar la clase
    public static void main(String[] args) {
        // Crear matriz de adyacencia para 5 vértices
        MatrizAdyacencia grafo = new MatrizAdyacencia(5);

        // Insertar algunas aristas
        grafo.insertarArista(0, 1);
        grafo.insertarArista(0, 2);
        grafo.insertarArista(1, 3);
        grafo.insertarArista(2, 4);
        grafo.insertarArista(3, 4);

        // Mostrar matriz
        grafo.mostrarMatriz();

        System.out.println("\n¿Existe arista entre 0 y 1? " + grafo.existeArista(0, 1));
        System.out.println("¿Existe arista entre 1 y 0? " + grafo.existeArista(1, 0));

        // Eliminar una arista
        System.out.println("\nEliminando arista entre 0 y 1...");
        grafo.eliminarArista(0, 1);

        // Mostrar matriz después de eliminar
        grafo.mostrarMatriz();
    }
}