//package ejer4;
//import org.jgrapht.Graph;
//import org.jgrapht.GraphPath;
//import org.jgrapht.alg.cycle.CycleDetector;
//import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
//import org.jgrapht.alg.connectivity.ConnectivityInspector;
//import org.jgrapht.graph.DefaultWeightedEdge;
//import org.jgrapht.graph.SimpleDirectedWeightedGraph;
//
//public class RedDistribucion {
//    public static void main(String[] args) {
//        // Crear grafo dirigido y ponderado
//        Graph<String, DefaultWeightedEdge> red = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
//
//        // Vértices = ciudades
//        red.addVertex("Lima");
//        red.addVertex("Arequipa");
//        red.addVertex("Cusco");
//        red.addVertex("Tacna");
//        red.addVertex("Puno");
//
//        // Aristas = rutas con distancias
//        red.setEdgeWeight(red.addEdge("Lima", "Arequipa"), 100.0);
//        red.setEdgeWeight(red.addEdge("Arequipa", "Cusco"), 80.0);
//        red.setEdgeWeight(red.addEdge("Cusco", "Puno"), 60.0);
//        red.setEdgeWeight(red.addEdge("Puno", "Tacna"), 90.0);
//        red.setEdgeWeight(red.addEdge("Tacna", "Lima"), 200.0); // Crea un ciclo
//
//        // 1. Camino más corto de Lima a Puno
//        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(red);
//        GraphPath<String, DefaultWeightedEdge> ruta = dijkstra.getPath("Lima", "Puno");
//        System.out.println("Ruta más corta de Lima a Puno: " + ruta.getVertexList());
//        System.out.println("Distancia total: " + ruta.getWeight());
//
//        // 2. ¿La red tiene ciclos?
//        CycleDetector<String, DefaultWeightedEdge> detector = new CycleDetector<>(red);
//        System.out.println("¿La red tiene ciclos?: " + detector.detectCycles());
//
//        // 3. ¿La red está conectada?
//        ConnectivityInspector<String, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(red);
//        System.out.println("¿Toda la red está conectada?: " + inspector.isConnected());
//    }
//}
