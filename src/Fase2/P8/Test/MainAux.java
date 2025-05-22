package Fase2.P8.Test;

import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;
import Fase2.P8.AVL.AVLTree;
import Fase2.P8.GUI.AVLTreeVisualizer;


public class MainAux {
    public static void main(String[] args) {
        demoPrebuiltTree();
    }

    private static void demoPrebuiltTree() {
        AVLTree<Integer> avl = new AVLTree<>();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          DEMOSTRACIÓN ÁRBOL AVL          ║");
        System.out.println("╚══════════════════════════════════════════╝");

        try {
            // Inserción de elementos
            System.out.println("\n[1] INSERCIÓN DE ELEMENTOS");
            System.out.println("Insertando: 50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 55, 1, 3");

            int[] elements = {50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 55, 1, 3, 4, 2, 16, 17};
            for (int element : elements) {
                avl.insert(element);
                System.out.println("  → Insertado: " + element);
            }

            // Información del árbol
            System.out.println("\n[2] INFORMACIÓN DEL ÁRBOL");
            System.out.println("  → Altura del árbol: " + avl.height(avl.getNode()));
            System.out.println("  → Factor de balance de la raíz: " + avl.balanceFactor(avl.getNode()));

            // Estructura del árbol
            System.out.println("\n[3] ESTRUCTURA DEL ÁRBOL");
            System.out.println("  → Representación parentizada: " + avl.parenthesize());

            System.out.println("\n[4] RECORRIDO POR NIVELES");
            avl.printByLevels();

            // Búsqueda
            int searchItem = 27;
            System.out.println("\n[5] BÚSQUEDA DE ELEMENTO");
            System.out.println("  → Buscando elemento: " + searchItem);
            try {
                System.out.println("  → Elemento encontrado: " + avl.search(searchItem));
                System.out.println("  → Altura del nodo " + searchItem + ": " + avl.heightof(searchItem));
            } catch (ItemNotFound e) {
                System.out.println("  → " + e.getMessage());
            }

            // Eliminación
            System.out.println("\n[6] ELIMINACIÓN DE ELEMENTOS");
            System.out.println("Eliminando: 15, 25");

            int[] elementsToDelete = {15, 25};
            for (int element : elementsToDelete) {
                System.out.println("  → Antes de eliminar " + element + ":");
                System.out.println("    Factor de balance raíz: " + avl.balanceFactor(avl.getNode()));
                avl.delete(element);
                System.out.println("  → Después de eliminar " + element + ":");
                System.out.println("    Factor de balance raíz: " + avl.balanceFactor(avl.getNode()));
            }

            // Estructura final
            System.out.println("\n[7] ESTRUCTURA FINAL DEL ÁRBOL");
            System.out.println("  → Representación parentizada: " + avl.parenthesize());
            System.out.println("  → Altura final: " + avl.height(avl.getNode()));

            System.out.println("\n[8] RECORRIDO FINAL POR NIVELES");
            avl.printByLevels();

            // Mostrar visualización gráfica del árbol de demostración
            System.out.println("\n[9] ABRIENDO VISUALIZACIÓN GRÁFICA DEL ÁRBOL...");
            AVLTreeVisualizer.showTree(avl);

        } catch (ItemDuplicated | ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}