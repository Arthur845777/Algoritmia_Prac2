//package Fase2.P8.AVL;
//import Fase2.P8.Exceptions.ItemDuplicated;
//import Fase2.P8.Node.Node;
//import Fase2.P8.Node.NodeAVL;
//
//class AVLTree<E extends Comparable<E>> {
//    class NodeAVL<E> extends Node{
//        protected int bf; // balance factor
//
//        public NodeAVL(E data) {
//            super(data);
//            bf = 0;
//        }
//    }
//
//    protected Node<E> root;
//    private boolean height;
//
//    public AVLTree() {
//        this.root = null;
//    }
//
//    public void insert (E x) throws ItemDuplicated{
//        this.height = false;
//        this.root = insert(x, (NodeAVL) this.root);
//    }
//
//    protected Node<E> insert(E x, NodeAVL node)throws ItemDuplicated {
//        NodeAVL fat = node;
//
//        if(node == null){
//            this.height = true;
//            fat = new NodeAVL(x);
//        } else {
//            int resC = node.getData()
//        }
//
//    }
//
//    protected Node<E> rotateSRight(Node<E> node) {
//        Node<E> hijo = node.getLeft();
//        node.setLeft(hijo.getRight());
//        hijo.setRight(node);
//        node = hijo;
//        return node;
//    }
//
//    protected Node<E> rotateSLeft(Node<E> node) {
//        Node<E> hijo = node.getRight();
//        node.setRight(hijo.getLeft());
//        hijo.setLeft(node);
//        node = hijo;
//        return hijo;
//    }
//
//}
