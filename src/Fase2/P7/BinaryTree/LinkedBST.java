package Fase2.P7.BinaryTree;

import Fase2.P7.BSTreeInterface.BinarySearchTree;
import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;
import Fase2.P7.Node.Node;
import Fase2.P7.Queue.LinkedQueue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private Node<E> root;

    public LinkedBST() {
        root = null;
    }

    public void destroy() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        root = insertBinary(root, x);
    }

    private Node<E> insertBinary(Node<E> currentNode, E data) throws ItemDuplicated {
        if(currentNode == null) {
            return new Node<>(data);
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
    public E search(E data) throws ItemNotFound {
        E result = searchBinary(root, data);
        if (isEmpty()) {
            throw new ItemNotFound("El elemento no se encuentra en el árbol");
        }
        return result;
    }

    private E searchBinary(Node<E> currentNode, E data) {
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

    public boolean contains(E data) {
        return containsRec(root, data);
    }

    private boolean containsRec(Node<E> currentNode, E data) {
        if (currentNode == null) {
            return false;
        }

        int compare = data.compareTo(currentNode.getData());

        if (compare > 0) {
            return containsRec(currentNode.getRight(), data);
        }
        else if (compare < 0) {
            return containsRec(currentNode.getLeft(), data);
        }
        else {
            return true;
        }
    }

    @Override
    public void delete(E currentNode) throws ExceptionIsEmpty{ // ItemNoFound
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }

        boolean found = deleteBinary(null, root, currentNode); // null como padre inicial, no hay dirección
        root = eliminar(root, currentNode);
//        if (!found) {
//            throw new ItemNoFound("El elemento no se encuentra en el árbol");
//        }
    }

    private boolean deleteBinary(Node<E> parent, Node<E> current, E data) {
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
                Node<E> auxNode = findMin(current, current.getRight());
                auxNode.setRight(current.getRight());
                parent.setRight(auxNode);
                current = auxNode;
            }
            return true;
        }
    }

    private Node<E> eliminar(Node<E> current, E valor) throws ExceptionIsEmpty {
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
                Node<E> minNode = findMin(current.getRight());
                current.setData(minNode.getData());
                current.setRight(eliminar(current.getRight(), minNode.getData()));
            }
        }

        return current;
    }

    public Node<E> findMin(Node<E> node){
        while(node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
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

    public Node<E> findMax(Node<E> node){
        while(node.getRight() != null) {
            node = node.getRight();
        }
        return node;
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

    //Recorridos
    // INORDER
    public String inOrder() {
        return inOrderRec(this.root).trim();
    }

    private String inOrderRec(Node<E> current) {
        if (current == null) return "";
        return inOrderRec(current.getLeft()) + current.getData() + " " + inOrderRec(current.getRight());
    }

    // PREORDER
    public String preOrder() {
        return preOrderRec(this.root).trim();
    }

    private String preOrderRec(Node<E> current) {
        if (current == null) return "";
        return current.getData() + " " + preOrderRec(current.getLeft()) + preOrderRec(current.getRight());
    }

    // POSTORDER
    public String postOrder() {
        return postOrderRec(this.root).trim();
    }

    private String postOrderRec(Node<E> current) {
        if (current == null) return "";
        return postOrderRec(current.getLeft()) + postOrderRec(current.getRight()) + current.getData() + " ";
    }

//    ------------------
public void RecorridoInOrder(){
    inOrder(root);
}

    public void RecorridoPreOrder(){
        preOrder(root);
    }

    public void RecorridoPostorder(){
        postOrder(root);
    }

    private void inOrder(Node<E> nodo) {
        if (nodo != null) {
            inOrder(nodo.getLeft());
            System.out.print(nodo.getData() + " ");
            inOrder(nodo.getRight());
        }
    }

    private void preOrder(Node<E> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getData() + " ");
            preOrder(nodo.getLeft());
            preOrder(nodo.getRight());
        }
    }

    private void postOrder(Node<E> nodo) {
        if (nodo != null) {
            postOrder(nodo.getLeft());
            postOrder(nodo.getRight());
            System.out.print(nodo.getData() + " ");
        }
    }

//   ejercicio 1  ------------------------------
//    a
    public void destroyNodes() throws ExceptionIsEmpty{
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol ya estaba vacío");
        }
        root = null;
    }
//    b
// Cuenta todos los nodos del árbol (hojas y no hojas)
    public int countAllNodes() {
        return countAllNodesRecursive(root);
    }

    private int countAllNodesRecursive(Node<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + countAllNodesRecursive(nodo.getLeft()) + countAllNodesRecursive(nodo.getRight());
    }
//    c
    // Cuenta solo los nodos internos (con al menos un hijo)
    public int countInternalNodes() {
        return countInternalNodesRecursive(root);
    }

    private int countInternalNodesRecursive(Node<E> nodo) {
        if (nodo == null || (nodo.getLeft() == null && nodo.getRight() == null)) {
            return 0;
        }
        return 1 + countInternalNodesRecursive(nodo.getLeft()) + countInternalNodesRecursive(nodo.getRight());
    }
//    d
    public int height(E dato) {
        // Si el árbol está vacío
        if (root == null) {
            return -1;
        }

        // Buscar el nodo desde el cual queremos calcular la altura
        E nodoX = searchBinary(root, dato);

        if (nodoX == null) {
            return -1;
        }

        // Usamos BFS para calcular la altura
        LinkedQueue<E> cola = new LinkedQueue<>();
        cola.enqueue(nodoX); // Agregamos el nodo inicial

        int altura = -1; // Inicializamos en -1 porque incrementamos al principio de cada nivel

        try {
            while (!cola.isEmpty()) {
                altura++; // Incrementamos la altura al comenzar un nuevo nivel

                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size(); // Usando el nuevo método size()

                for (int i = 0; i < nodosEnNivel; i++) {
                    E actual = cola.dequeue();

                    // Agregamos los hijos a la cola para procesarlos en el siguiente nivel
                    if (actual.left != null) {
                        cola.enqueue(actual.left);
                    }
                    if (actual.right != null) {
                        cola.enqueue(actual.right);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            // Esto no debería ocurrir ya que verificamos isEmpty() antes
            System.err.println("Error: " + e.getMessage());
        }

        return altura;
    }


// toString
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

//    @Override // In Order
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Binary Search Tree: [");
//        inOrderToString(root, sb);
//        sb.append("]");
//        return sb.toString();
//    }

    // Método para dibujar el árbol
    public String drawBST() {
        if (isEmpty()) {
            return "El árbol está vacío";
        }

        StringBuilder result = new StringBuilder();
        drawNode(root, "", true, result);
        return result.toString();
    }

    private void drawNode(Node<E> node, String prefix, boolean isTail, StringBuilder result) {
        if (node != null) {
            // Dibuja el nodo actual
            result.append(prefix)
                    .append(isTail ? "└── " : "├── ")
                    .append(node.getData())
                    .append("\n");

            // Prepara el prefijo para los hijos
            String newPrefix = prefix + (isTail ? "    " : "│   ");

            // Determina si el nodo tiene hijos
            boolean hasLeft = node.getLeft() != null;
            boolean hasRight = node.getRight() != null;

            // Dibuja el hijo derecho primero (aparecerá arriba en la visualización)
            if (hasRight) {
                drawNode(node.getRight(), newPrefix, !hasLeft, result);
            }

            // Dibuja el hijo izquierdo
            if (hasLeft) {
                drawNode(node.getLeft(), newPrefix, true, result);
            }
        }
    }

    @Override // In Order
    public String toString() {
        if (isEmpty()) {
            return "El árbol está vacío";
        }

        // Retorna la representación visual del árbol
        return "Representación visual del árbol:\n" + drawBST() +
                "\nRecorrido en orden: " + inOrderTraversal();
    }

    // Método auxiliar para obtener el recorrido en orden
    private String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        inOrderToString(root, sb);
        sb.append("]");
        return sb.toString();
    }

    private void inOrderToString(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderToString(node.getLeft(), sb);
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(node.getData());
            inOrderToString(node.getRight(), sb);
        }
    }

//    private void inOrderToString(Node<E> node, StringBuilder sb) {
//        if (node != null) {
//            inOrderToString(node.getLeft(), sb);
//            if (sb.length() > "Binary Search Tree: [".length()) {
//                sb.append(", ");
//            }
//            sb.append(node.getData());
//            inOrderToString(node.getRight(), sb);
//        }
//    }

    public String parenthesize() {
        if (isEmpty()) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        parenthesize(root, sb, 0);
        return sb.toString();
    }

    private void parenthesize(Node<E> node, StringBuilder sb, int level) {
        if (node == null) {
            return;
        }

        // Indentación según el nivel
        appendIndentation(sb, level);

        // Agregar el valor del nodo
        sb.append(node.getData()).append(" (\n");

        // Procesar los hijos (para un BST, solo hay izquierdo y derecho)
        if (node.getLeft() != null) {
            parenthesize(node.getLeft(), sb, level + 1);
        }

        if (node.getRight() != null) {
            parenthesize(node.getRight(), sb, level + 1);
        }

        // Cerrar el paréntesis con la indentación apropiada
        appendIndentation(sb, level);
        sb.append(")\n");
    }

    // Método auxiliar para agregar la indentación adecuada
    private void appendIndentation(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("    "); // 4 espacios por nivel
        }
    }

}

class Main{
    public static void main(String[] args) {
        LinkedBST<Integer> tree = new LinkedBST<>();
        try {
            tree.insert(5);
            tree.insert(6);
            tree.insert(8);
            tree.insert(9);
            tree.insert(10);
            tree.insert(3);
            tree.insert(2);
            tree.insert(4);

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