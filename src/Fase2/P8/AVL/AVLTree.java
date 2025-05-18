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

//    ---------------------------ELIMINAR-Santi-----------------------------------
@Override
public void delete(E data) throws ExceptionIsEmpty {
    if (isEmpty()) {
        throw new ExceptionIsEmpty("El árbol está vacío");
    }

    this.height = false; // Inicializamos la variable de altura
    root = deleteAVL(data, (NodeAVL)root);
}

    private NodeAVL deleteAVL(E data, NodeAVL current) throws ExceptionIsEmpty {
        if (current == null) {
            throw new ExceptionIsEmpty("El elemento no está en el árbol");
        }

        NodeAVL nodoCurrent = current;

        int resultCompare = data.compareTo(current.getData()); // right +1, left -1

        if (resultCompare < 0) { // left
            current.setLeft(deleteAVL(data, (NodeAVL)current.getLeft()));

            if (this.height) { // aca no se compliquen, es si es que la altura cambio :v

                switch (current.bf) {
                    case -1: // Estaba cargado a la izquierda
                        current.bf = 0;
                        break;

                    case 0: // Estaba equilibrado
                        current.bf = 1;
                        this.height = false; // La altura del árbol no ha cambiado
                        break;

                    case 1: // Estaba cargado a la derecha
                        // El subárbol derecho ahora tiene 2 niveles más que el izquierdo
                        nodoCurrent = balanceToLeft(current);
                        break;
                }
            }
        }
        else if (resultCompare > 0) { // right
            current.setRight(deleteAVL(data, (NodeAVL)current.getRight()));


            if (this.height) {

                switch (current.bf) {
                    case 1: // Estaba cargado a la derecha
                        current.bf = 0;
                        break;
                    case 0: // Estaba equilibrado
                        current.bf = -1;
                        this.height = false; // La altura del árbol no ha cambiado
                        break;
                    case -1: // Estaba cargado a la izquierda
                        // El subárbol izquierdo ahora tiene 2 niveles más que el derecho
                        nodoCurrent = balanceToRight(current);
                        break;
                }
            }
        }
        else { // aca eliminas nomas
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            else if (current.getLeft() == null) {
                this.height = true; // ojita aqio, la altura esta disminuyendo, por eso les decia que ntp, plan solo es un indicador de altura
                return (NodeAVL)current.getRight();
            }

            else if (current.getRight() == null) {
                this.height = true; // no voy a escribir otra vez
                return (NodeAVL)current.getLeft();
            }

            else {
                NodeAVL successor = findMinAVL((NodeAVL)current.getRight());

                current.setData(successor.getData());

                current.setRight(deleteAVL(successor.getData(), (NodeAVL)current.getRight()));

                if (this.height) {
                    switch (current.bf) {
                        case 1: // Estaba cargado a la derecha
                            current.bf = 0;
                            break;

                        case 0: // Estaba equilibrado
                            current.bf = -1;
                            this.height = false; // La altura del árbol no ha cambiado
                            break;

                        case -1: // Estaba cargado a la izquierda
                            // El subárbol izquierdo ahora tiene 2 niveles más que el derecho
                            nodoCurrent = balanceToRight(current);
                            break;
                    }
                }
            }
        }

        return nodoCurrent;
    }


    private NodeAVL findMinAVL(NodeAVL node) {
        NodeAVL current = node;
        while (current.getLeft() != null) {
            current = (NodeAVL)current.getLeft();
        }
        return current;
    }
//    ---------------------------ELIMINAR-Miguel--------------------------
    /**
     * Elimina un nodo con el valor especificado del árbol AVL,
     * manteniendo la propiedad de balanceo.
     *
     * @param data Valor a eliminar
     * @param current Nodo actual en la recursión
     * @return La nueva raíz del subárbol después de la eliminación
     * @throws ExceptionIsEmpty Si el elemento no está en el árbol
     */
    private NodeTree<E> deleteAVL(E data, NodeTree<E> current) throws ExceptionIsEmpty {
        if (current == null) {
            throw new ExceptionIsEmpty("El elemento no está en el árbol");
        }

        NodeAVL node = (NodeAVL) current;
        int compareResult = data.compareTo(node.getData()); // right +1 / left -1


        if (compareResult < 0) { // left
            node.setLeft(deleteAVL(data, node.getLeft()));
            return balanceAfterDelete(node);

        } else if (compareResult > 0) {
            // El elemento está en el subárbol derecho
            node.setRight(deleteAVL(data, node.getRight()));
            return balanceAfterDelete(node);

        } else {
            // Encontramos el nodo a eliminar
            return deleteFoundNode(node);
        }
    }


    private NodeAVL deleteFoundNode(NodeAVL node) throws ExceptionIsEmpty {
        // Caso 1: Nodo hoja (sin hijos)
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        }

        // Caso 2: Nodo con un solo hijo
        if (node.getLeft() == null) {
            return (NodeAVL) node.getRight();
        }
        if (node.getRight() == null) {
            return (NodeAVL) node.getLeft();
        }

        NodeAVL successor = findMinNode((NodeAVL) node.getRight());
        node.setData(successor.getData());
        node.setRight(deleteAVL(successor.getData(), node.getRight()));

        return balanceAfterDelete(node);
    }

    /**
     * Encuentra el nodo con el valor mínimo en un subárbol
     *
     * @param node Raíz del subárbol
     * @return El nodo con el valor mínimo
     */
    private NodeAVL findMinNode(NodeAVL node) {
        NodeAVL current = node;
        while (current.getLeft() != null) {
            current = (NodeAVL) current.getLeft();
        }
        return current;
    }

    /**
     * Balancea el árbol después de una eliminación
     *
     * @param node Nodo a balancear
     * @return El nodo balanceado
     */
    private NodeAVL balanceAfterDelete(NodeAVL node) {
        // Actualizar el factor de balance
        updateBalanceFactor(node);

        // Verificar si se necesita rebalanceo
        if (node.bf > 1) {
            // Desbalance hacia la izquierda
            return balanceToRight(node);
        } else if (node.bf < -1) {
            // Desbalance hacia la derecha
            return balanceToLeft(node);
        }

        // No se necesita balanceo
        return node;
    }

    /**
     * Actualiza el factor de balance de un nodo
     *
     * @param node Nodo cuyo factor de balance se actualizará
     */
    private void updateBalanceFactor(NodeAVL node) {
        int heightLeft = height((NodeAVL) node.getLeft());
        int heightRight = height((NodeAVL) node.getRight());

        node.bf = heightLeft - heightRight;

        // Actualizar la bandera de altura si hubo cambio
        if (Math.abs(node.bf) >= 2) {
            height = true;
        }
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