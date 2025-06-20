package Fase3.P10.BTree;

import Fase3.P10.BTree.BTree;

public class Main {
    public static void main(String[] args) {
        // Create a B-tree with order 3 (max 2 keys per node)
        BTree<Integer> bTree = new BTree<>(3);

        // Insert some values
        System.out.println("Inserting values...");
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);

        // Print the tree
        System.out.println("\nB-Tree structure:");
        System.out.println(bTree.toString());

//         Search for values
        System.out.println("\nSearching for values:");
        System.out.println("Contains 5? " + bTree.search(5));
        System.out.println("Contains 15? " + bTree.search(15));
        System.out.println("Contains 20? " + bTree.search(20));

        // Remove some values
        System.out.println("\nRemoving values...");
        bTree.remove(6);
        bTree.remove(12);
        bTree.remove(17);

        // Print the tree after removal
        System.out.println("\nB-Tree after removal:");
        System.out.println(bTree.toString());

        // Test building from file
        try {
            System.out.println("\nBuilding B-Tree from file...");
            BTree<Integer> fileTree = new BTree<>(3);
            fileTree.building_BTree("btree_data.txt"); // Make sure this file exists
            System.out.println("\nB-Tree from file:");
            System.out.println(fileTree.toString());
        } catch (Exception e) {
            System.out.println("Error building tree from file: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        System.out.println((19200 * 19200) );
//        System.out.println((118 % 40 ) );
//        System.out.println((93 % 40 ) );
//    }
}