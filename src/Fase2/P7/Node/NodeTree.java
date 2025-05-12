package Fase2.P7.Node;

public class NodeTree<E> {
    private E data;
    private NodeTree<E> right;
	private NodeTree<E> left;

    public NodeTree(E data) {
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

	public NodeTree<E> getRight() {
		return right;
	}
	public void setRight(NodeTree<E> next) {
		this.right = next;
	}

	public NodeTree<E> getLeft() {
		return left;
	}
	public void setLeft(NodeTree<E> next) {
		this.left = next;
	}
}