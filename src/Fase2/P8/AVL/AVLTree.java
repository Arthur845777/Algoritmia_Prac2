package Fase2.P8.AVL;

import Fase2.P7.BinaryTree.BSTree;
import Fase2.P7.Exceptions.*;
import Fase2.P7.Node.NodeTree;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    private boolean height; // para la altura

    protected class NodeAVL extends NodeTree<E> {
        protected int bf; // balance factor

        public NodeAVL(E data) {
            super(data);
            bf = 0;
        }

        @Override
        public String toString() {
            return "NodeAVL{" +
                    "data=" + getData() +
                    ", bf=" + bf +
                    '}';
        }
    }

    public AVLTree() {
        super();
        height = false;
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL)this.root);
    }

    protected NodeAVL insert(E data, NodeAVL current) throws ItemDuplicated {
        NodeAVL fat = current;

        if (current == null) {
            this.height = true;
            fat = new NodeAVL(data);
        } else {

            int compare = data.compareTo(current.getData()); // right +1, left -1

            if (compare == 0) {
                throw new ItemDuplicated(data + " ya se encuentra en el árbol...");
            }

            if (compare > 0) { // right
                fat.setRight(insert(data, (NodeAVL) current.getRight()));

                if (this.height) {
                    switch (fat.bf) {
                        case -1: // cargado left -> balance
                            fat.bf = 0;
                            this.height = false;
                            break;

                        case 0:  // balance ->  cargado right
                            fat.bf = 1;
                            this.height = true;
                            break;

                        case 1:  // cargado right -> ahora aun mas cargado xD +2
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            }

            else { // left
                fat.setLeft(insert(data, (NodeAVL) current.getLeft()));

                if (this.height) {
                    switch (fat.bf) {
                        case 1: // cargado right -> balanceado
                            fat.bf = 0;
                            this.height = false;
                            break;

                        case 0: // balanceado-> cargado left
                            fat.bf = -1;
                            this.height = true;
                            break;

                        case -1: // cargado left -> ahora aun mas cargado xD -2
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }

    private NodeAVL balanceToRight(NodeAVL abuelo) {
        NodeAVL hijo = (NodeAVL) abuelo.getLeft();

        switch(hijo.bf) {
            case -1:  // simple a la dere, este es el normalito, plan esta sobercargado pero normal o,o
                abuelo.bf = 0;
                hijo.bf = 0;
                abuelo = rotateSRight(abuelo);
                break;

            case 0:
                abuelo.bf = -1;
                hijo.bf = 1;
                abuelo = rotateSRight(abuelo);
                break;

            case 1:   // doble, izq y luego der, que kilombo xd
                NodeAVL nieto = (NodeAVL)hijo.getRight();
                switch(nieto.bf) {
                    case 1:
                        abuelo.bf = 0;
                        hijo.bf = -1;
                        break;

                    case 0:
                        abuelo.bf = 0;
                        hijo.bf = 0;
                        break;

                    case -1:
                        abuelo.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                abuelo.setLeft(rotateSLeft(hijo));
                abuelo = rotateSRight(abuelo);
                break;
        }
        return abuelo;
    }

    private NodeAVL balanceToLeft(NodeAVL abuelo) {
        NodeAVL hijo = (NodeAVL) abuelo.getRight();

        switch(hijo.bf) {
            case 1: // simple a la izq, este es el normalito, plan esta sobercargado pero normal o,o
                abuelo.bf = 0;
                hijo.bf = 0;
                abuelo = rotateSLeft(abuelo);
                break;

            case 0:
                abuelo.bf = 1;
                hijo.bf = -1;
                abuelo = rotateSLeft(abuelo);
                break;

            case -1: // doble, der y luego izq, que kilombo xd
                NodeAVL nieto = (NodeAVL)hijo.getLeft();
                switch(nieto.bf) {
                    case -1:
                        abuelo.bf = 0;
                        hijo.bf = 1;
                        break;

                    case 0:
                        abuelo.bf = 0;
                        hijo.bf = 0;
                        break;

                    case 1:
                        abuelo.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                abuelo.setRight(rotateSRight(hijo));
                abuelo = rotateSLeft(abuelo);
                break;
        }
        return abuelo;
    }

// bien, comprobados
    private NodeAVL rotateSLeft(NodeAVL node) {
        NodeAVL p = (NodeAVL)node.getRight();
        node.setRight(p.getLeft()); // bien
        p.setLeft(node); // bien
        node = p;
        return node;
    }
    private NodeAVL rotateSRight(NodeAVL node) {
        NodeAVL p = (NodeAVL)node.getLeft();
        node.setLeft(p.getRight());
        p.setRight(node);
        node = p;
        return node;
    }

    public int height(NodeAVL nodo){
        if(nodo == null){
            return 0;

        } else {
            int hleft = height((NodeAVL)nodo.getLeft());
            int hright = height((NodeAVL)nodo.getRight());
            if (hleft > hright) {
                return hleft + 1;
            } else {
                return hright + 1;
            }
        }
    }

    public int balanceFactor(NodeAVL nodo){
        return height((NodeAVL)nodo.getRight()) - height((NodeAVL)nodo.getLeft());
    }

    public NodeAVL getNode() {
        return (NodeAVL)root;
    }


//    ------------------------------MIGUEL-------------------------------------------
    @Override
    public void RecorridoPreOrder(){
        preOrder((NodeAVL)root);
    }

    private void preOrder(NodeAVL nodo) {
        if (nodo != null) {
            System.out.print(nodo.getData() + " ");
            preOrder((NodeAVL)nodo.getLeft());
            preOrder((NodeAVL)nodo.getRight());
        }
    }

//    public int heightof(E data) throws ExceptionIsEmpty {
//        if (isEmpty()) {
//            throw new ExceptionIsEmpty("El árbol está vacío");
//        }
//
//        NodeAVL node = searchBinary((NodeAVL)root, data);
//        if (node == null) {
//            return -1; // El nodo no existe
//        }
//
//        return heightRecursive(node);
//    }
//
//    private int heightRecursive(NodeAVL node) {
//        if (node == null) {
//            return -1;
//        }
//
//        int leftHeight = heightRecursive((NodeAVL) node.getLeft());
//
//        int rightHeight = heightRecursive((NodeAVL) node.getRight());
//
//        return Math.max(leftHeight, rightHeight) + 1;
//    }

    @Override
    public E search(E data) throws ItemNotFound {
        NodeAVL result = searchBinary((NodeAVL)root, data);

        if (result == null) {
            throw new ItemNotFound("El elemento no se encuentra en el árbol");
        }
        return result.getData();
    }

    private NodeAVL searchBinary(NodeAVL currentNode, E data) {
        if (currentNode == null) {
            return null;
        }

        int compare = data.compareTo(currentNode.getData());

        if (compare < 0) {
            return searchBinary((NodeAVL)currentNode.getLeft(), data);
        } else if (compare > 0) {
            return searchBinary((NodeAVL)currentNode.getRight(), data);
        } else {
            return currentNode;
        }
    }

    public void printByLevels() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }

        int height = height((NodeAVL) root);

        for (int level = 0; level <= height; level++) {
            System.out.print("Nivel " + level + ": ");
            printGivenLevel((NodeAVL)root, level);
            System.out.println(); // Salto de línea después de cada nivel
        }
    }


    private void printGivenLevel(NodeAVL node, int level) {
        if (node == null) {
            return;
        }

        if (level == 0) {
            // Estamos en el nivel que queremos imprimir
            System.out.print(node.getData() + " ");
        } else if (level > 0) {
            // Recursivamente bajar al nivel adecuado
            printGivenLevel((NodeAVL)node.getLeft(), level - 1);
            printGivenLevel((NodeAVL)node.getRight(), level - 1);
        }
    }
}