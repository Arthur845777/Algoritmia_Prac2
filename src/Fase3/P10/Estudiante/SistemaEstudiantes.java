package Fase3.P10.Estudiante;

import Fase3.P10.BNode.BNode;
import Fase3.P10.BTree.BTree;

public class SistemaEstudiantes {
    private BTree<RegistroEstudiante> arbolEstudiantes;

    public SistemaEstudiantes() {
        arbolEstudiantes = new BTree<>(4); // Árbol B de orden 4
    }

    public void insertarEstudiante(int codigo, String nombre) {
        RegistroEstudiante nuevo = new RegistroEstudiante(codigo, nombre);
        arbolEstudiantes.insert(nuevo);
    }

    public String buscarNombre(int codigo) {
        RegistroEstudiante buscado = new RegistroEstudiante(codigo, "");
        if (arbolEstudiantes.search(buscado)) {
            return buscarNombreRecursivo(arbolEstudiantes.getRoot(), codigo);
        }
        return "No encontrado";
    }

    private String buscarNombreRecursivo(BNode<RegistroEstudiante> nodo, int codigo) {
        if (nodo == null) return null;

        int[] pos = new int[1];
        RegistroEstudiante temp = new RegistroEstudiante(codigo, "");
        boolean encontrado = nodo.searchNode(temp, pos);

        if (encontrado) {
            return nodo.keys.get(pos[0]).getNombre();
        }

        if (pos[0] < nodo.childs.size() && nodo.childs.get(pos[0]) != null) {
            return buscarNombreRecursivo(nodo.childs.get(pos[0]), codigo);
        }

        return null;
    }

    public void eliminarEstudiante(int codigo) {
        RegistroEstudiante aEliminar = new RegistroEstudiante(codigo, "");
        arbolEstudiantes.remove(aEliminar);
    }

    public static void main(String[] args) {
        SistemaEstudiantes sistema = new SistemaEstudiantes();

        // Insertar estudiantes
        sistema.insertarEstudiante(103, "Ana");
        sistema.insertarEstudiante(110, "Luis");
        sistema.insertarEstudiante(101, "Carlos");
        sistema.insertarEstudiante(120, "Lucía");
        sistema.insertarEstudiante(115, "David");
        sistema.insertarEstudiante(125, "Jorge");
        sistema.insertarEstudiante(140, "Camila");
        sistema.insertarEstudiante(108, "Rosa");
        sistema.insertarEstudiante(132, "Ernesto");
        sistema.insertarEstudiante(128, "Denis");
        sistema.insertarEstudiante(145, "Enrique");
        sistema.insertarEstudiante(122, "Karina");
        sistema.insertarEstudiante(108, "Juan"); // Duplicado, no debería insertarse

        // Operaciones solicitadas
        System.out.println("Buscar 115: " + sistema.buscarNombre(115)); // David
        System.out.println("Buscar 132: " + sistema.buscarNombre(132)); // Ernesto
        System.out.println("Buscar 999: " + sistema.buscarNombre(999)); // No encontrado

        sistema.eliminarEstudiante(101);
        sistema.insertarEstudiante(106, "Sara");
        System.out.println("Buscar 106: " + sistema.buscarNombre(106)); // Sara
    }
}