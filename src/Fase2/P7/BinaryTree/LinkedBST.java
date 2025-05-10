package Fase2.P7.BinaryTree;

import Fase2.P7.BSTreeInterface.BinarySearchTree;
import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNoFound;
import Fase2.P7.Node.Node;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private Node<E> root;

    public LinkedBST() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        root = insertBinary(root, x);
    }

    private Node<E> insertBinary(Node<E> node, E x) throws ItemDuplicated {
        if(node == null) {
            return new Node<>(x);
        }

        int compare = x.compareTo(node.getData());

        if(compare < 0) {
            node.setLeft(insertBinary(node.getLeft(), x));
        }
        else if (compare > 0) {
            node.setRight(insertBinary(node.getRight(), x));
        } else {
            throw new ItemDuplicated("El elemento ya está en el árbol");
        }
        return node;
    }

    @Override
    public E search(E x) throws ItemNoFound {
        E result = searchBinary(root, x);
        if (isEmpty()) {
            throw new ItemNoFound("El elemento no se encuentra en el árbol");
        }
        return result;
    }

    private E searchBinary(Node<E> node, E x) {
        if (node == null) {
            return null;
        }

        int compare = x.compareTo(node.getData());

        if (compare < 0) {
            return searchBinary(node.getLeft(), x);
        } else if (compare > 0) {
            return searchBinary(node.getRight(), x);
        } else {
            return node.getData();
        }
    }

    @Override
    public void delete(E x) throws ExceptionIsEmpty{ // ItemNoFound
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }

        boolean found = deleteBinary(null, root, x); // null como padre inicial, no hay dirección

//        if (!found) {
//            throw new ItemNoFound("El elemento no se encuentra en el árbol");
//        }
    }


    private boolean deleteBinary(Node<E> parent, Node<E> current, E x) {
        if (current == null) {
            return false;
        }

        int compare = x.compareTo(current.getData());

        if (compare < 0) {
            return deleteBinary(current, current.getLeft(), x);
        }

        else if (compare > 0) {
            return deleteBinary(current, current.getRight(), x);
        }

        else {
            if (current.getLeft() == null && current.getRight() == null) {
                current = null;
            }

            else if (current.getLeft() != null && current.getRight() == null) {
                parent.setLeft(current.getLeft());
                current = parent;
            }

            else if (current.getLeft() == null && current.getRight() != null) {
                parent.setRight(current.getRight());
                current = parent;
            }

            else { // root.getRight() != null && root.getLeft() != null
                Node<E> auxNode = findMin(current, current.getRight());
                auxNode.setRight(current.getRight());
                parent.setRight(auxNode);
                current = auxNode;
            }
            return true;
        }
    }

    private Node<E> findMin(Node<E> parent, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            parent.setLeft(node.getLeft());
            return node;
        }
        return findMin(node,node.getLeft());
    }

    private Node<E> findMax(Node<E> parent, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() == null) {
            parent.setRight(node.getLeft());
            return node;
        }
        return findMax(node, node.getRight());
    }

//    @Override
//    public String toString() {
//        return toString(root, 0);
//    }
//
//    private String toString(Node<E> node, int level) {
//        StringBuilder sb = new StringBuilder();
//        if (node == null) {
//            sb.append(indent(level)).append("null\n");
//        } else {
//            sb.append(toString(node.getRight(), level + 1));
//            sb.append(indent(level)).append(node.getData()).append("\n");
//            sb.append(toString(node.getLeft(), level + 1));
//        }
//        return sb.toString();
//    }
//
//    private String indent(int level) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < level; i++) {
//            sb.append("    ");
//        }
//        return sb.toString();
//    }

    @Override // In Order
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Binary Search Tree: [");
        inOrderToString(root, sb);
        sb.append("]");
        return sb.toString();
    }

    private void inOrderToString(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderToString(node.getLeft(), sb);
            if (sb.length() > "Binary Search Tree: [".length()) {
                sb.append(", ");
            }
            sb.append(node.getData());
            inOrderToString(node.getRight(), sb);
        }
    }
}

class Main{
    public static void main(String[] args) {
        LinkedBST<Integer> tree = new LinkedBST<>();
        try {
            tree.insert(5);
            tree.insert(3);
            tree.insert(7);
            tree.insert(2);
            tree.insert(4);
        } catch (ItemDuplicated e) {
            throw new RuntimeException(e);
        }


        System.out.println(tree.toString());
    }
}