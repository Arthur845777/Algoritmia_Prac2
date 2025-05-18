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

    protected NodeAVL insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {

            int compare = node.getData().compareTo(x); // right -1, left +1

            if (compare == 0) {
                throw new ItemDuplicated(x + " ya se encuentra en el árbol...");
            }

            if (compare < 0) { // right
                fat.setRight(insert(x, (NodeAVL)node.getRight()));

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
                fat.setLeft(insert(x, (NodeAVL)node.getLeft()));

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

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL)node.getLeft();

        switch(hijo.bf) {
            case -1:  // simple a la dere, este es el normalito, plan esta sobercargado pero normal o,o
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSRight(node);
                break;
            case 0:
                node.bf = -1;
                hijo.bf = 1;
                node = rotateSRight(node);
                break;
            case 1:   // doble, izq y luego der, que kilombo xd
                NodeAVL nieto = (NodeAVL)hijo.getRight();
                switch(nieto.bf) {
                    case 1:  node.bf = 0; hijo.bf = -1; break;
                    case 0:  node.bf = 0; hijo.bf = 0; break;
                    case -1: node.bf = 1; hijo.bf = 0; break;
                }
                nieto.bf = 0;
                node.setLeft(rotateSLeft(hijo));
                node = rotateSRight(node);
                break;
        }
        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL)node.getRight();

        switch(hijo.bf) {
            case 1: // simple a la izq, este es el normalito, plan esta sobercargado pero normal o,o
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSLeft(node);
                break;
            case 0:
                node.bf = 1;
                hijo.bf = -1;
                node = rotateSLeft(node);
                break;
            case -1: // doble, der y luego izq, que kilombo xd
                NodeAVL nieto = (NodeAVL)hijo.getLeft();
                switch(nieto.bf) {
                    case -1: node.bf = 0; hijo.bf = 1; break;
                    case 0:  node.bf = 0; hijo.bf = 0; break;
                    case 1:  node.bf = -1; hijo.bf = 0; break;
                }
                nieto.bf = 0;
                node.setRight(rotateSRight(hijo));
                node = rotateSLeft(node);
                break;
        }
        return node;
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



    // También necesitarás sobrescribir el método delete para mantener balanceado el árbol
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }

        height = false;
        root = deleteAVL(data, (NodeTree<E>)root);
    }

    private NodeTree<E> deleteAVL(E data, NodeTree<E> current) throws ExceptionIsEmpty {
        // Este método necesitaría implementarse para eliminar nodos y mantener el balanceo
        // Similar a insertAVL pero con lógica para eliminar nodos

        // Por ahora, dejo un esqueleto básico
        if (current == null) {
            throw new ExceptionIsEmpty("El elemento no está en el árbol");
        }

        // Aquí iría la implementación completa

        return current;
    }
}