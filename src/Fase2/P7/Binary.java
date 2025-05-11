package Fase2.P7;

import Fase2.P7.BSTreeInterface.BinarySearchTree;
import Fase2.P7.Exceptions.*;
import Fase2.P7.Node.Node;
import Fase2.P7.Queue.*;

public class Binary<E extends Comparable<E>> implements BinarySearchTree<E> {
    // Raíz del árbol
    public Node<E> root;
    
    // Constructor
    public Binary() {
        root = null;
    }
    
    public void insert(E valor) throws ItemDuplicated {
        root = insertarRecursivo(root, valor);
    }
    
    // Método recursivo para insertar un valor
    private Node<E> insertarRecursivo(Node<E> nodo, E valor) throws ItemDuplicated  {
        // Si el árbol está vacío
        if (nodo == null) {
            nodo = new Node<E>(valor);
            return nodo;
        }

        int cpt=valor.compareTo(nodo.getData());

        // De lo contrario, recorremos el árbol
        if (cpt < 0)
            nodo.setLeft(insertarRecursivo(nodo.getLeft(), valor));
        else if (cpt > 0)
            nodo.setRight(insertarRecursivo(nodo.getRight(), valor));
        else{
            throw new ItemDuplicated("El valor ya se encuentra en el arbol");
        }
        // Devolver la referencia del nodo
        return nodo;
    }
    
    // Método público para eliminar un valor
    public void delete(E valor) throws ExceptionIsEmpty {
        root = eliminar(root, valor);
    }
    
    // Método recursivo para eliminar un valor
    private Node<E> eliminar(Node<E> nodo, E valor) throws ExceptionIsEmpty {
        // Caso base: si el árbol está vacío
        if (nodo == null)
            throw new ExceptionIsEmpty("El arbol esta vacio");
            
        // Buscar el nodo a eliminar
        int cpt=valor.compareTo(nodo.getData());

        if (cpt < 0)
            nodo.setLeft(eliminar(nodo.getLeft(), valor));
        else if (cpt > 0)
            nodo.setRight(eliminar(nodo.getRight(), valor));
        else {
            if (nodo.getLeft() == null && nodo.getRight() == null)
                return null;
                
            // Caso 2: Nodo tiene solo un hijo
            else if (nodo.getLeft() == null)
                return nodo.getRight();
            else if (nodo.getRight() == null)
                return nodo.getLeft();
        
            Node<E> min = encontrarValorMinimo(nodo.getRight());
            
            //swap 
            Node<E> temp=nodo;
            nodo.setData(min.getData());
            min.setData(temp.getData());;

            // Eliminar el sucesor inmediato
            nodo.setRight(eliminar(nodo.getRight(), nodo.getData()));
        }
        
        return nodo;
    }
    
    // Método para encontrar el valor mínimo en un subárbol
    private Node<E> encontrarValorMinimo(Node<E> nodo) {
        
        // El valor mínimo estará en el nodo más a la izquierda
        while (nodo.getLeft() != null) {
            nodo.setLeft(nodo.getLeft());
        }
        
        return nodo;
    }
    
    // Método para buscar un valor en el árbol
    public E search(E dato) throws ItemNotFound{
        return buscar(root, dato);
    }
    
    // Método recursivo para buscar un valor
    private E buscar(Nodo<E> nodo, E dato)throws ItemNotFound{
        // Caso base: árbol vacío o valor encontrado
        if (nodo == null)
            throw new ItemNotFound("El valor no se encuentra en el arbol");
        if (nodo.data.equals(dato))
            return nodo.data;
            
        int cpt=dato.compareTo(nodo.data);

        if (cpt < 0){
            return buscar(nodo.left, dato);
        }
        else{
            return buscar(nodo.right, dato);
        }
    }
    
    public boolean isEmpty(){
        return root==null;
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

    private void inOrder(Nodo<E> nodo) {
        if (nodo != null) {                         
            inOrder(nodo.left); 
            System.out.print(nodo.data + " ");       
            inOrder(nodo.right);   
        }
    }

    private void preOrder(Nodo<E> nodo) {
        if (nodo != null) {
            System.out.print(nodo.data + " ");         
            preOrder(nodo.left); 
            preOrder(nodo.right);  
        }
    }

    private void postOrder(Nodo<E> nodo) {
        if (nodo != null) {
            postOrder(nodo.left); 
            postOrder(nodo.right);   
            System.out.print(nodo.data + " ");          
        }
    }

    public E findMinNode()throws ItemNotFound{
        return MinNode(root);
    }

    public E findMaxNode()throws ItemNotFound{
        return MaxNode(root);
    }

    private E MinNode(Nodo<E> root)throws ItemNotFound{
        Nodo<E> current=root;
        while(current.left!=null){
            current=current.left;
        }
        return search(current.data);
    }

    private E MaxNode(Nodo<E> root)throws ItemNotFound{
        Nodo<E> current=root;
        while(current.right!=null){
            current=current.right;
        }
        return search(current.data);
    }
    
    public void destroyNodes() throws ExceptionIsEmpty{
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol ya estaba vacío");
        }
        root=null;
    }

    // Cuenta todos los nodos del árbol (hojas y no hojas)
    public int countAllNodes() {
        return countAllNodesRecursive(root);
    }

    private int countAllNodesRecursive(Nodo<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + countAllNodesRecursive(nodo.left) + countAllNodesRecursive(nodo.right);
    }

    // Cuenta solo los nodos internos (con al menos un hijo)
    public int countInternalNodes() {
        return countInternalNodesRecursive(root);
    }

    private int countInternalNodesRecursive(Nodo<E> nodo) {
        if (nodo == null || (nodo.left == null && nodo.right == null)) {
            return 0;
        }
        return 1 + countInternalNodesRecursive(nodo.left) + countInternalNodesRecursive(nodo.right);
    }

    public int height(E dato) {
        // Si el árbol está vacío
        if (root == null) {
            return -1;
        }
    
        // Buscar el nodo desde el cual queremos calcular la altura
        Nodo<E> nodoX = buscarNodo(dato);
    
        if (nodoX == null) {
            return -1; // El dato no existe en el árbol
        }
    
        // Usamos BFS para calcular la altura
        Colas<Nodo<E>> cola = new Colas<>();
        cola.enqueue(nodoX); // Agregamos el nodo inicial
    
        int altura = -1; // Inicializamos en -1 porque incrementamos al principio de cada nivel
    
        try {
            while (!cola.isEmpty()) {
                altura++; // Incrementamos la altura al comenzar un nuevo nivel
            
                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size(); // Usando el nuevo método size()
            
                for (int i = 0; i < nodosEnNivel; i++) {
                    Nodo<E> actual = cola.dequeue();
                
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

    private Nodo<E> buscarNodo(E x) {
        Nodo<E> actual = root;
        while (actual != null) {
            int comparacion = x.compareTo(actual.data);
            if (comparacion == 0) {
                return actual;
            } else if (comparacion < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }
        return null; 
    }

    public int amplitude(int nivel) {
        // Si el árbol está vacío o el nivel es negativo
        if (root == null || nivel < 0) {
            return 0;
        }

        // Usamos BFS para recorrer el árbol por niveles
        Colas<Nodo<E>> cola = new Colas<>();
        cola.enqueue(root);
    
        int nivelActual = 0;
    
        try {
            while (!cola.isEmpty() && nivelActual < nivel) {
                nivelActual++; // Pasamos al siguiente nivel
            
                // Procesamos todos los nodos en el nivel actual
                int nodosEnNivel = cola.size();
            
                for (int i = 0; i < nodosEnNivel; i++) {
                    Nodo<E> actual = cola.dequeue();
                
                    // Agregamos los hijos a la cola para procesarlos en el siguiente nivel
                    if (actual.left != null) {
                        cola.enqueue(actual.left);
                    }
                    if (actual.right != null) {
                        cola.enqueue(actual.right);
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
    public int maxAmplitude() {
        if (root == null) {
            return 0;
        }
    
        int altura = height(root.data); // Altura total del árbol
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
}