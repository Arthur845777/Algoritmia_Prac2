package Fase2.P7.jose.BST;
import Fase2.P7.jose.Exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E>{
	private Node<E> root;

	public LinkedBST() {
		this.root = null;
	}
	
	public void destroyNodes() throws ExceptionIsEmpty {
	    if (isEmpty()) throw new ExceptionIsEmpty("BST is already empty");
	    destroy();
	}
	
	public void destroy() {
		this.root = null;
	}
	
	public void insert(E data) throws ItemDuplicated {
		this.root = insertRec(data, this.root);
	}
	
	public Node<E> insertRec(E data, Node<E> current) throws ItemDuplicated {
	    if(current == null) return new Node<E>(data);
	    
	    int compareResult = current.data.compareTo(data);
	    if (compareResult == 0) throw new ItemDuplicated("Item duplicated");
	    if (compareResult < 0) current.right = insertRec(data, current.right);
	    else current.left = insertRec(data, current.left);
	    
	    return current;
	}
	
	public E search(E data) throws ItemNotFound {
		Node<E> result = searchRec(data, this.root);
		if(result == null) throw new ItemNotFound("Item not found");
		return result.data;
	}
	
	public Node<E> searchRec(E data, Node<E> current) throws ItemNotFound{
		if(current == null) {
			return null;
		}
		else {
			int compareResult = current.data.compareTo(data);
			if(compareResult < 0) {
				return searchRec(data, current.right);
			}
			else if (compareResult > 0) {
				return searchRec(data, current.left);
			}
			else {
				return current;
			}
		}
	}
	
	public boolean contains(E data) {
	    return containsRec(data, this.root);
	}

	private boolean containsRec(E data, Node<E> current) {
	    if (current == null) return false;

	    int compareResult = current.data.compareTo(data);
	    if (compareResult < 0) return containsRec(data, current.right);
	    else if (compareResult > 0) return containsRec(data, current.left);
	    else return true;
	}
	
	public void delete(E data) throws ExceptionIsEmpty, ItemNotFound{
		if(isEmpty()) throw new ExceptionIsEmpty("Empty BST");
		this.root = deleteRec(data, this.root);
	}
	
	public Node<E> deleteRec(E data, Node<E> current) throws ItemNotFound {
		Node<E> result = current;
		if(current == null) {
			throw new ItemNotFound("Item not found");
		}
		int resultCompare = current.data.compareTo(data);

		if(resultCompare < 0) {
			current.right = deleteRec(data, current.right);
		}
		else if(resultCompare > 0) {
			current.left = deleteRec(data, current.left);
		}
		else {
			//hoja
			if(current.right == null && current.left == null) {
				return null;
			}

			//1 hijo
			else if(current.left == null) {
				return current.right;
			}
			else if(current.right == null) {
				return current.left;
			}
			//dos hijos
			else {
				Node<E> minNode = findMin(current.right);
				current.data = minNode.data;
				current.right = deleteRec(minNode.data, current.right);
			}
		}
		return current;
	}
	
	public Node<E> findMin(Node<E> node){
		while(node.left != null) node = node.left;
		return node;
	}
	public Node<E> findMax(Node<E> node){
		while(node.right != null) node = node.right;
		return node;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	//Recorridos
	// INORDER
	public String inOrder() {
	    return inOrderRec(this.root).trim();
	}

	private String inOrderRec(Node<E> current) {
	    if (current == null) return "";
	    return inOrderRec(current.left) + current.data + " " + inOrderRec(current.right);
	}

	// PREORDER
	public String preOrder() {
	    return preOrderRec(this.root).trim();
	}

	private String preOrderRec(Node<E> current) {
	    if (current == null) return "";
	    return current.data + " " + preOrderRec(current.left) + preOrderRec(current.right);
	}

	// POSTORDER
	public String postOrder() {
	    return postOrderRec(this.root).trim();
	}

	private String postOrderRec(Node<E> current) {
	    if (current == null) return "";
	    return postOrderRec(current.left) + postOrderRec(current.right) + current.data + " ";
	}
}
