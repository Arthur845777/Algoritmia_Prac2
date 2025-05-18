package Fase2.P8.Test;

import Fase2.P8.AVL.AVLTree;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;

public class MainAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        try {
            avl.insert(100);
            avl.insert(110);
            avl.insert(150);
            avl.insert(90);
            avl.insert(95);

            System.out.println("Árbol AVL creado con éxito.\n");

            System.out.println("Estructura del árbol (parenthesize):");
            System.out.println(avl.parenthesize());


            System.out.println(avl.height(avl.getNode())); // ALTURA
            System.out.println(avl.balanceFactor(avl.getNode())); // F_BALANCE



        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
