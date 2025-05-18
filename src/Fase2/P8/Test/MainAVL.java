package Fase2.P8.Test;

import Fase2.P8.AVL.AVLTree;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;

public class MainAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        try {
            avl.insert(9);
            avl.insert(10);
            avl.insert(5);
            avl.insert(1);
            avl.insert(7);
            avl.insert(6);


            System.out.println("Árbol AVL creado con éxito.\n");

            // Imprimir estructura del árbol en formato parenthesize
            System.out.println("Estructura del árbol (parenthesize):");
            System.out.println(avl.parenthesize());

            // Buscar un elemento
            Integer toSearch = 25;
            Integer result = avl.search(toSearch);

//            System.out.println("Elemento encontrado: " + result);
            System.out.println(avl.height(avl.getNode()));
            System.out.println(avl.balanceFactor(avl.getNode()));

        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.err.println("Elemento no encontrado: " + e.getMessage());
        }
    }
}
