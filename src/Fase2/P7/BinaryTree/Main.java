package Fase2.P7.BinaryTree;

import Fase2.P7.Exceptions.ItemDuplicated;

class Main {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        try {
            tree.insert(11);  // Raíz - elemento medio
            tree.insert(4);   // Medio del subárbol izquierdo
            tree.insert(50);  // Medio del subárbol derecho
            tree.insert(2);   // Medio del subárbol izquierdo del izquierdo
            tree.insert(9);   // Medio del subárbol derecho del izquierdo
            tree.insert(15);  // Medio del subárbol izquierdo del derecho
            tree.insert(75);  // Medio del subárbol derecho del derecho
            tree.insert(3);   // Completa el subárbol izquierdo
            tree.insert(6);   // Completa el subárbol izquierdo
            tree.insert(10);  // Completa el subárbol izquierdo
            tree.insert(12);  // Completa el subárbol derecho
            tree.insert(25);  // Completa el subárbol derecho
            tree.insert(62);  // Completa el subárbol derecho
            tree.insert(87);  // Completa el subárbol derecho
            tree.insert(37);  // Completa el subárbol derecho

        } catch (ItemDuplicated e) {
            throw new RuntimeException(e);
        }

//        // Muestra el árbol usando el método toString modificado
//        System.out.println(tree.toString());
//
//        // Alternativamente, se puede llamar directamente al método drawBST
//         System.out.println("Visualización del árbol:");
//         System.out.println(tree.drawBST());

        System.out.println("\nRepresentación con paréntesis:");
        System.out.println(tree.parenthesize());


    }
}
