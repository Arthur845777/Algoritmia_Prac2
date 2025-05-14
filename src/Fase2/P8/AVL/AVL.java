package Fase2.P8.AVL;
import Fase2.P8.Node.NodeAVL;

class AVL<T extends Comparable<T>> {
    protected NodeAVL<T> root;

    public AVL() {
        this.root = null;
    }

    protected NodeAVL<T> rotateSR(NodeAVL<T> node) {
        NodeAVL<T> hijo = node.getLeft();
        node.setLeft(hijo.getRight());
        hijo.setRight(node);
        node = hijo;
        return node;
    }

//    public void insert(T x);
//
//    public void remove(T x);
}
