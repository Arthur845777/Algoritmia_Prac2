package Fase2.P8.Test;

import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P8.AVL.AVLTree;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;

public class MainAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        try {
            avl.insert(15);
            avl.insert(6);
            avl.insert(20);
            avl.insert(4);
            avl.insert(10);
            avl.insert(5);
            avl.insert(12);
            avl.insert(13);

            System.out.println("Árbol AVL creado con éxito.\n");

            System.out.println("Estructura del árbol (parenthesize):");
            System.out.println(avl.parenthesize());

            avl.delete(12);

            System.out.println("Estructura del árbol (parenthesize):");
            System.out.println(avl.parenthesize());

            System.out.println(avl.height(avl.getNode())); // ALTURA
            System.out.println(avl.balanceFactor(avl.getNode())); // F_BALANCE

//            avl.printByLevels();


        } catch (ItemDuplicated | ExceptionIsEmpty e ) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
