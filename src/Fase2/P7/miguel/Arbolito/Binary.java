package Fase2.P7.miguel.Arbolito;
import Fase2.P7.miguel.Excepciones.*;

public class Binary<E extends Comparable<E>> implements BinarySearchTree<E> {
    // Definición de la clase Nodo genérica
    class Nodo {
        public E data;
        public Nodo left, right;
        
        public Nodo(E data) {
            this.data=data;
            this.left=null;
            this.right=null;
        }

        
    }
    
    // Raíz del árbol
    private Nodo root;
    
    // Constructor
    public Binary() {
        root = null;
    }
    
    public void insert(E valor) throws ItemDuplicated {
        insertarRecursivo(root, valor);
    }
    
    // Método recursivo para insertar un valor
    private Nodo insertarRecursivo(Nodo nodo, E valor) throws ItemDuplicated  {
        // Si el árbol está vacío
        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }

        int cpt=valor.compareTo(nodo.data);

        // De lo contrario, recorremos el árbol
        if (cpt < 0)
            nodo.left = insertarRecursivo(nodo.left, valor);
        else if (cpt > 0)
            nodo.right = insertarRecursivo(nodo.right, valor);
        else{
            throw new ItemDuplicated("El valor ya se encuentra en el arbol");
        }
        // Devolver la referencia del nodo
        return nodo;
    }
    
    // Método público para eliminar un valor
    public void delete(E valor) throws ExceptionIsEmpty {
        root=eliminar(root, valor);
    }
    
    // Método recursivo para eliminar un valor
    private Nodo eliminar(Nodo nodo, E valor) throws ExceptionIsEmpty {
        // Caso base: si el árbol está vacío
        if (nodo == null)
            throw new ExceptionIsEmpty("El arbol esta vacio");
            
        // Buscar el nodo a eliminar
        int cpt=valor.compareTo(nodo.data);

        if (cpt < 0)
            nodo.left = eliminar(nodo.left, valor);
        else if (cpt > 0)
            nodo.right = eliminar(nodo.right, valor);
        else {
            if (nodo.left == null && nodo.right == null)
                return null;
                
            // Caso 2: Nodo tiene solo un hijo
            else if (nodo.left == null)
                return nodo.right;
            else if (nodo.right == null)
                return nodo.left;
        
            Nodo min = encontrarValorMinimo(nodo.right);
            
            //swap 
            Nodo temp = nodo;
            nodo.data = min.data;
            min.data = temp.data;

            // Eliminar el sucesor inmediato
            nodo.right = eliminar(nodo.right, nodo.data);
        }
        
        return nodo;
    }
    
    // Método para encontrar el valor mínimo en un subárbol
    private Nodo encontrarValorMinimo(Nodo nodo) {
        
        // El valor mínimo estará en el nodo más a la izquierda
        while (nodo.left != null) {
            nodo = nodo.left;
        }
        
        return nodo;
    }
    
    // Método para buscar un valor en el árbol
    public E search(E dato) throws ItemNotFound{
        return buscar(root, dato);
    }
    
    // Método recursivo para buscar un valor
    private E buscar(Nodo nodo, E dato)throws ItemNotFound{
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

    private void inOrder(Nodo nodo) {
        if (nodo != null) {                         
            inOrder(nodo.left); 
            System.out.print(nodo.data + " ");       
            inOrder(nodo.right);   
        }
    }

    private void preOrder(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.data + " ");         
            preOrder(nodo.left); 
            preOrder(nodo.right);  
        }
    }

    private void postOrder(Nodo nodo) {
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

    private E MinNode(Nodo root)throws ItemNotFound{
        Nodo current=root;
        while(current.left!=null){
            current=current.left;
        }
        return search(current.data);
    }

    private E MaxNode(Nodo root)throws ItemNotFound{
        Nodo current=root;
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
}