package Fase2.P7.BinaryTree;

import Fase2.P7.BSTreeInterface.BinarySearchTree;
import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;
import Fase2.P7.Node.NodeTree;
import Fase2.P7.Queue.LinkedQueue;

public class BSTree<E extends Comparable<E>> implements BinarySearchTree<E> {
    protected NodeTree<E> root;

    public BSTree() {
        root = null;
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        root = insertBinary(root, x);
    }

    private NodeTree<E> insertBinary(NodeTree<E> currentNode, E data) throws ItemDuplicated {
        if(currentNode == null) {
            return new NodeTree<>(data);
        }

        int compare = data.compareTo(currentNode.getData());

        if(compare < 0) {
            currentNode.setLeft(insertBinary(currentNode.getLeft(), data));
        }
        else if (compare > 0) {
            currentNode.setRight(insertBinary(currentNode.getRight(), data));
        } else {
            throw new ItemDuplicated("El elemento ya está en el árbol");
        }
        return currentNode;
    }

    @Override
    public void delete(E currentNode) throws ExceptionIsEmpty{ // ItemNoFound, lo metemos?
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }
        root = eliminar(root, currentNode); // 2do metodo
    }

    private boolean deleteBinary(NodeTree<E> parent, NodeTree<E> current, E data) {
        if (current == null) {
            return false;
        }

        int compare = data.compareTo(current.getData());

        if (compare < 0) {
            return deleteBinary(current, current.getLeft(), data);
        }

        else if (compare > 0) {
            return deleteBinary(current, current.getRight(), data);
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
                NodeTree<E> auxNode = findMin(current, current.getRight());
                auxNode.setRight(current.getRight());
                parent.setRight(auxNode);
                current = auxNode;
            }
            return true;
        }
    }

    private NodeTree<E> eliminar(NodeTree<E> current, E valor) throws ExceptionIsEmpty {
        if (current == null) {
            throw new ExceptionIsEmpty("El arbol esta vacio");
        }

        int resultCompare = valor.compareTo(current.getData());

        if (resultCompare < 0) {
            current.setLeft(eliminar(current.getLeft(), valor));
        }

        else if (resultCompare > 0) {
            current.setRight(eliminar(current.getRight(), valor));
        }

        else {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            else if (current.getLeft() == null) {
                return current.getRight();
            }

            else if (current.getRight() == null) {
                return current.getLeft();
            }
            else {
                NodeTree<E> minNode = findMin(current.getRight());
                current.setData(minNode.getData());
                current.setRight(eliminar(current.getRight(), minNode.getData()));
            }
        }

        return current;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        E result = searchBinary(root, data);

        if (isEmpty()) {
            throw new ItemNotFound("El elemento no se encuentra en el árbol");
        }
        return result;
    }

    private E searchBinary(NodeTree<E> currentNode, E data) {
        if (currentNode == null) {
            return null;
        }

        int compare = data.compareTo(currentNode.getData());

        if (compare < 0) {
            return searchBinary(currentNode.getLeft(), data);
        } else if (compare > 0) {
            return searchBinary(currentNode.getRight(), data);
        } else {
            return currentNode.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public void RecorridoInOrder(){
    inOrder(root);
}

    public void RecorridoPreOrder(){
        preOrder(root);
    }

    public void RecorridoPostorder(){
        postOrder(root);
    }

    private void inOrder(NodeTree<E> nodo) {
        if (nodo != null) {
            inOrder(nodo.getLeft());
            System.out.print(nodo.getData() + " ");
            inOrder(nodo.getRight());
        }
    }

    private void preOrder(NodeTree<E> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getData() + " ");
            preOrder(nodo.getLeft());
            preOrder(nodo.getRight());
        }
    }

    private void postOrder(NodeTree<E> nodo) {
        if (nodo != null) {
            postOrder(nodo.getLeft());
            postOrder(nodo.getRight());
            System.out.print(nodo.getData() + " ");
        }
    }

    private NodeTree<E> findMin(NodeTree<E> node){
        while(node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private NodeTree<E> findMin(NodeTree<E> parent, NodeTree<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            parent.setLeft(node.getLeft());
            return node;
        }
        return findMin(node,node.getLeft());
    }

    private NodeTree<E> findMax(NodeTree<E> node){
        while(node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    private NodeTree<E> findMax(NodeTree<E> parent, NodeTree<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getRight() == null) {
            parent.setRight(node.getLeft());
            return node;
        }
        return findMax(node, node.getRight());
    }


//------------------------------------------EJERCICIOS-1-------------------------------------------------------
    public void destroyNodes() throws ExceptionIsEmpty{ // a
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol ya estaba vacío");
        }
        root = null;
    }

    public int countAllNodes() { // b
        return countAllNodesRecursive(root);
    }

    private int countAllNodesRecursive(NodeTree<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + countAllNodesRecursive(nodo.getLeft()) + countAllNodesRecursive(nodo.getRight());
    }

    public int countInternalNodes() { // c
        return countInternalNodesRecursive(root);
    }

    private int countInternalNodesRecursive(NodeTree<E> nodo) {
        if (nodo == null || (nodo.getLeft() == null && nodo.getRight() == null)) {
            return 0;
        }
        return 1 + countInternalNodesRecursive(nodo.getLeft()) + countInternalNodesRecursive(nodo.getRight());
    }

    public int height(E dato) throws ItemNotFound { // d
        if (root == null) {
            return -1;
        }

        NodeTree<E> nodoX = new NodeTree<>(search(dato)); // Ubicamos el nodo, si existe o no, y donde partir

        if (nodoX == null) {
            return -1; // El dato no existe en el árbol
        }

        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(nodoX);

        int altura = -1;

        try {
            while (!cola.isEmpty()) {
                altura++;

                int nodosEnNivel = cola.size();

                for (int i = 0; i < nodosEnNivel; i++) {
                    NodeTree<E> actual = cola.dequeue();

                    if (actual.getLeft() != null) {
                        cola.enqueue(actual.getLeft());
                    }
                    if (actual.getRight() != null) {
                        cola.enqueue(actual.getRight());
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
        return altura;
    }

    public int amplitude(int nivel) {
        if (root == null || nivel < 0) {
            return 0;
        }

        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        int nivelActual = 0;

        try {
            while (!cola.isEmpty() && nivelActual < nivel) {
                nivelActual++; // next node

                int nodosEnNivel = cola.size();

                for (int i = 0; i < nodosEnNivel; i++) {
                    NodeTree<E> actual = cola.dequeue();

                    if (actual.getLeft() != null) {
                        cola.enqueue(actual.getLeft());
                    }
                    if (actual.getRight() != null) {
                        cola.enqueue(actual.getRight());
                    }
                }
            }

            if (nivelActual == nivel) {
                return cola.size();
            } else {
                return 0;
            }
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
            return 0;
        }
    }

    public int maxAmplitude() throws ItemNotFound {
        if (root == null) {
            return 0;
        }

        int altura = height(root.getData()); // Altura total del árbol
        int maxAnchura = 0;

        // Comprobamos la amplitud de cada nivel y nos quedamos con el máximo
        for (int nivel = 0; nivel <= altura; nivel++) {
            int anchuraNivel = amplitude(nivel);
            if (anchuraNivel > maxAnchura) {
                maxAnchura = anchuraNivel;
            }
        }

        return maxAnchura;
    }

    //------------------------------------------EJERCICIOS-2-------------------------------------------------------
    public int areaBST() {
        if (root == null) {
            return 0;
        }

        int altura;

        try {
            altura = height(root.getData());
        } catch (ItemNotFound e) {
            return 0;
        }

        int hojas = 0;
        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        try {
            while (!cola.isEmpty()) {
                NodeTree<E> actual = cola.dequeue();

                if (actual.getLeft() == null && actual.getRight() == null) {
                    hojas++;
                }

                if (actual.getLeft() != null) {
                    cola.enqueue(actual.getLeft());
                }
                if (actual.getRight() != null) {
                    cola.enqueue(actual.getRight());
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }

        return hojas * altura;
    }

    //  dibujar arbol
    public String drawBST() {
        if (isEmpty()) {
            return "El árbol está vacío";
        }

        StringBuilder result = new StringBuilder();
        drawNode(root, "", true, result);
        return result.toString();
    }

    private void drawNode(NodeTree<E> node, String prefix, boolean isTail, StringBuilder result) {
        if (node != null) {
            result.append(prefix)
                    .append(isTail ? "└── " : "├── ")
                    .append(node.getData())
                    .append("\n");

            String newPrefix = prefix + (isTail ? "    " : "│   ");

            boolean hasLeft = node.getLeft() != null;
            boolean hasRight = node.getRight() != null;

            if (hasRight) {
                drawNode(node.getRight(), newPrefix, !hasLeft, result);
            }

            if (hasLeft) {
                drawNode(node.getLeft(), newPrefix, true, result);
            }
        }
    }


    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        int altura = 0;
        int nivelActual = 0;

        try {
            // Imprimir la raíz primero
            System.out.println(root.getData() + " (");

            while (!cola.isEmpty()) {
                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size();
                nivelActual++;
                String indent = "  ".repeat(nivelActual);

                for (int i = 0; i < nodosEnNivel; i++) {
                    NodeTree<E> actual = cola.dequeue();

                    // Agregamos los hijos a la cola para procesarlos en el siguiente nivel
                    if (actual.getLeft() != null) {
                        System.out.println(indent + actual.getLeft().getData());
                        cola.enqueue(actual.getLeft());
                    }

                    if (actual.getRight() != null) {
                        System.out.println(indent + actual.getRight().getData());
                        cola.enqueue(actual.getRight());
                    }

                    // Si es el último nodo del nivel actual (i+1 == nodosEnNivel) y hay nodos en el siguiente nivel
                    if (i + 1 == nodosEnNivel && !cola.isEmpty()) {
                        System.out.println(indent + "(");
                        altura = nivelActual + 1;  // Actualizamos la altura máxima
                    }
                }
            }

            // Imprimir los paréntesis de cierre en orden inverso de indentación
            for (int i = altura; i >= 1; i--) {
                String indent = "  ".repeat(i - 1);
                System.out.println(indent + ")");
            }

        } catch (ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public <E extends Comparable<E>> boolean sameArea(BSTree<E> arbol2) {

        if (root == null && arbol2 == null) {
            return true;
        }

        if (root == null || arbol2 == null) {
            return false;
        }

        try {

            int areaArbol1 = areaBST();
            int areaArbol2 = arbol2.areaBST();

            if (areaArbol1 < 0 || areaArbol2 < 0) {
                System.err.println("Stop: Se tiene un area negativa, no es posible continuar :'v.");
                return false;
            }

            return areaArbol1 == areaArbol2;

        } catch (Exception e) {
            System.err.println("Error al comparar areas: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //------------------------------------------EJERCICIOS-3-------------------------------------------------------
    public String parenthesize() {
        if (isEmpty()) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        parenthesize(root, sb, 0);
        return sb.toString();
    }

    private void parenthesize(NodeTree<E> node, StringBuilder sb, int level) {
        appendIndentation(sb, level);

        if (node == null) {
            sb.append("()\n");
            return;
        }

        sb.append("(").append(node.getData());

        if (node.getLeft() == null && node.getRight() == null) {
            sb.append(")\n");
            return;
        }

        sb.append("\n");

        parenthesize(node.getRight(), sb, level + 1);
        parenthesize(node.getLeft(), sb, level + 1);

        appendIndentation(sb, level);
        sb.append(")\n");
    }

    private void appendIndentation(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("    "); // 4 espacios por nivel
        }
    }
}