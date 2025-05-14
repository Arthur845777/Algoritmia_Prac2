package Fase2.P8.Node;

public class NodeAVL<E> {
    private E data;
    private NodeAVL<E> right;
	private NodeAVL<E> left;

    public NodeAVL(E data) {
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

	public NodeAVL<E> getRight() {
		return right;
	}
	public void setRight(NodeAVL<E> next) {
		this.right = next;
	}

	public NodeAVL<E> getLeft() {
		return left;
	}
	public void setLeft(NodeAVL<E> next) {
		this.left = next;
	}
}