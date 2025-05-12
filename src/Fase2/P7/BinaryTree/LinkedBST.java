package Fase2.P7.BinaryTree;

import Fase2.P7.BSTreeInterface.BinarySearchTree;
import Fase2.P7.Exceptions.ExceptionIsEmpty;
import Fase2.P7.Exceptions.ItemDuplicated;
import Fase2.P7.Exceptions.ItemNotFound;
import Fase2.P7.Node.NodeTree;
import Fase2.P7.Queue.LinkedQueue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private NodeTree<E> root;

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

    public boolean contains(E data) {
        return containsRec(root, data);
    }

    private boolean containsRec(NodeTree<E> currentNode, E data) {
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

    public NodeTree<E> findMin(NodeTree<E> node){
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

    public NodeTree<E> findMax(NodeTree<E> node){
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

    //Recorridos
    // INORDER
    public String inOrder() {
        return inOrderRec(this.root).trim();
    }

    private String inOrderRec(NodeTree<E> current) {
        if (current == null) return "";
        return inOrderRec(current.getLeft()) + current.getData() + " " + inOrderRec(current.getRight());
    }

    // PREORDER
    public String preOrder() {
        return preOrderRec(this.root).trim();
    }

    private String preOrderRec(NodeTree<E> current) {
        if (current == null) return "";
        return current.getData() + " " + preOrderRec(current.getLeft()) + preOrderRec(current.getRight());
    }

    // POSTORDER
    public String postOrder() {
        return postOrderRec(this.root).trim();
    }

    private String postOrderRec(NodeTree<E> current) {
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

    private int countAllNodesRecursive(NodeTree<E> nodo) {
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

    private int countInternalNodesRecursive(NodeTree<E> nodo) {
        if (nodo == null || (nodo.getLeft() == null && nodo.getRight() == null)) {
            return 0;
        }
        return 1 + countInternalNodesRecursive(nodo.getLeft()) + countInternalNodesRecursive(nodo.getRight());
    }
    //    d
    public int height(E dato) throws ItemNotFound {
        // Si el árbol está vacío
        if (root == null) {
            return -1;
        }

        // Buscar el nodo desde el cual queremos calcular la altura
        NodeTree<E> nodoX = new NodeTree<>(search(dato));

        if (nodoX == null) {
            return -1; // El dato no existe en el árbol
        }

        // Usamos BFS para calcular la altura
        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(nodoX); // Agregamos el nodo inicial

        int altura = -1; // Inicializamos en -1 porque incrementamos al principio de cada nivel

        try {
            while (!cola.isEmpty()) {
                altura++; // Incrementamos la altura al comenzar un nuevo nivel

                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size(); // Usando el nuevo método size()

                for (int i = 0; i < nodosEnNivel; i++) {
                    NodeTree<E> actual = cola.dequeue();

                    // Agregamos los hijos a la cola para procesarlos en el siguiente nivel
                    if (actual.getLeft() != null) {
                        cola.enqueue(actual.getLeft());
                    }
                    if (actual.getRight() != null) {
                        cola.enqueue(actual.getRight());
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            // Esto no debería ocurrir ya que verificamos isEmpty() antes
            System.err.println("Error: " + e.getMessage());
        }

        return altura;
    }

    public int amplitude(int nivel) {
        // Si el árbol está vacío o el nivel es negativo
        if (root == null || nivel < 0) {
            return 0;
        }

        // Usamos BFS para recorrer el árbol por niveles
        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        int nivelActual = 0;

        try {
            while (!cola.isEmpty() && nivelActual < nivel) {
                nivelActual++; // Pasamos al siguiente nivel

                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size();

                for (int i = 0; i < nodosEnNivel; i++) {
                    NodeTree<E> actual = cola.dequeue();

                    // Agregamos los hijos a la cola para procesarlos en el siguiente nivel
                    if (actual.getLeft() != null) {
                        cola.enqueue(actual.getLeft());
                    }
                    if (actual.getRight() != null) {
                        cola.enqueue(actual.getRight());
                    }
                }
            }

            // Si llegamos al nivel deseado, la cantidad de nodos en la cola es la amplitud
            if (nivelActual == nivel) {
                return cola.size();
            } else {
                return 0; // El nivel solicitado no existe en el árbol
            }
        } catch (ExceptionIsEmpty e) {
            // Esto no debería ocurrir ya que verificamos isEmpty() antes
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

    public int areaBST() {
        if (root == null) {
            return 0; // Árbol vacío tiene área 0
        }

        // Calcular la altura del árbol completo
        int altura;
        try {
            altura = height(root.getData());
        } catch (ItemNotFound e) {
            // Esto no debería ocurrir ya que estamos usando la raíz
            return 0;
        }

        // Contar nodos hoja usando un recorrido BFS iterativo
        int hojas = 0;
        LinkedQueue<NodeTree<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        try {
            while (!cola.isEmpty()) {
                NodeTree<E> actual = cola.dequeue();

                // Verificar si es un nodo hoja (sin hijos)
                if (actual.getLeft() == null && actual.getRight() == null) {
                    hojas++;
                }

                // Agregar hijos a la cola
                if (actual.getLeft() != null) {
                    cola.enqueue(actual.getLeft());
                }
                if (actual.getRight() != null) {
                    cola.enqueue(actual.getRight());
                }
            }
        } catch (ExceptionIsEmpty e) {
            // Esto no debería ocurrir ya que verificamos isEmpty() antes
            System.err.println("Error: " + e.getMessage());
        }

        // Calcular el área como: número de hojas × altura
        return hojas * altura;
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

    private void drawNode(NodeTree<E> node, String prefix, boolean isTail, StringBuilder result) {
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

    public <E extends Comparable<E>> boolean sameArea(LinkedBST<E> arbol1, LinkedBST<E> arbol2) {
        // Validación 1: Si ambos árboles son nulos, consideramos que tienen la misma área (área 0)
        if (arbol1 == null && arbol2 == null) {
            return true;
        }

        // Validación 2: Si solo uno de los árboles es nulo, no pueden tener la misma área
        if (arbol1 == null || arbol2 == null) {
            return false;
        }

        try {
            // Validación 3: Verificamos si los árboles están vacíos
            boolean arbol1Vacio = arbol1.isEmpty();
            boolean arbol2Vacio = arbol2.isEmpty();

            // Si ambos están vacíos, tienen la misma área (área 0)
            if (arbol1Vacio && arbol2Vacio) {
                return true;
            }

            // Si solo uno está vacío, no pueden tener la misma área
            if (arbol1Vacio || arbol2Vacio) {
                return false;
            }

            // Cálculo de áreas con manejo de posibles errores
            int areaArbol1 = arbol1.areaBST();
            int areaArbol2 = arbol2.areaBST();

            // Validación 4: Verificar posibles valores negativos o erróneos
            if (areaArbol1 < 0 || areaArbol2 < 0) {
                System.err.println("Advertencia: Se obtuvo un área negativa, posible error en el cálculo.");
                return false;
            }

            // Comparamos las áreas y retornamos el resultado
            return areaArbol1 == areaArbol2;

        } catch (Exception e) {
            // Manejo de cualquier excepción inesperada
            System.err.println("Error al comparar áreas: " + e.getMessage());
            e.printStackTrace();
            return false;
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

    private void inOrderToString(NodeTree<E> node, StringBuilder sb) {
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

    private void parenthesize(NodeTree<E> node, StringBuilder sb, int level) {
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