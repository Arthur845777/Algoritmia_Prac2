package Fase2.P6.Teo.SparceMatrix;

class Node<E> {
    private int row;
    private int col;
    private E value;
    private Node<E> down;
    private Node<E> right;

    public Node(int row, int col, Node<E> down, Node<E> right, E value) {
        this.row = row;
        this.col = col;
        this.down = down;
        this.right = right;
        this.value = value;
    }

    public Node() {
        this(-1, -1, null, null, null);
        this.down = this;
        this.right = this;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public E getValue() {
        return value;
    }
    public Node<E> getDown() {
        return down;
    }
    public Node<E> getRight() {
        return right;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setValue(E value) {
        this.value = value;
    }
    public void setDown(Node<E> down) {
        this.down = down;
    }
    public void setRight(Node<E> right) {
        this.right = right;
    }
}
