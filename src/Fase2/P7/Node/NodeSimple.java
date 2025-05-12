package Fase2.P7.Node;

public class NodeSimple<E> {
    private E data;
    private NodeSimple<E> next;

    public NodeSimple(E data) {
        this.data = data;
        this.next = null;
    }

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public NodeSimple<E> getNext() {
		return next;
	}

	public void setNext(NodeSimple<E> next) {
		this.next = next;
	}
}