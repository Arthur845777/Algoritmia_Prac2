package Fase2.P7.Node;

public class Node<E> {
    private E data;
    private Node<E> right;
	private Node<E> left;

    public Node(E data) {
        this.data = data;
        this.right = null;
		this.left = null;
    }

	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getRight() {
		return right;
	}
	public void setRight(Node<E> next) {
		this.right = next;
	}

	public Node<E> getLeft() {
		return left;
	}
	public void setLeft(Node<E> next) {
		this.left = next;
	}
}