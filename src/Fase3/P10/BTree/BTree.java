package Fase3.P10.BTree;

import Fase3.P10.BNode.BNode;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int order;
    private boolean up;
    private boolean down; // esto tambien lo vamos a necesitar para borrar
    private BNode<E> nDes;

    public BTree(int orden) {
        this.order = orden;
        this.root = null;
        this.up = false;
        this.down = false;
        this.nDes = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.order;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        mediana = push(this.root, cl);
        if (up) {
            BNode<E> pnew = new BNode<E>(this.order);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;

        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), cl);

            if (up) {
                if (current.nodeFull()) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;

        for (i = current.count; i >= k; i--) {
            if (i < current.count) {
                current.keys.set(i + 1, current.keys.get(i));
            }
            current.childs.set(i + 1, current.childs.get(i));
        }


        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        // Calcular posición de la mediana
        posMdna = (k <= this.order / 2) ? this.order / 2 : this.order / 2 + 1;
        nDes = new BNode<E>(this.order);

        // Mover claves y hijos al nuevo nodo
        for (i = posMdna; i < this.order - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.order - 1) - posMdna;
        current.count = posMdna;

        // Insertar el nuevo elemento en el nodo correspondiente
        if (k <= this.order / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }

        // Obtener la mediana que subirá
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            s = writeTree(this.root);
        }
        return s;
    }

    private String writeTree(BNode<E> current) {
        String s = "";
        if (current != null) {
            // Mostrar el contenido del nodo actual
            s += "[";
            for (int i = 0; i < current.count; i++) {
                s += current.keys.get(i);
                if (i < current.count - 1) {
                    s += ", ";
                }
            }
            s += "]";

            // Si no es una hoja, recorrer los hijos
            boolean isLeaf = true;
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) {
                    isLeaf = false;
                    break;
                }
            }

            if (!isLeaf) {
                s += "\n";
                for (int i = 0; i <= current.count; i++) {
                    if (current.childs.get(i) != null) {
                        // Agregar indentación para mostrar la estructura jerárquica
                        String childContent = writeTree(current.childs.get(i));
                        String[] lines = childContent.split("\n");
                        for (String line : lines) {
                            s += "  " + line + "\n";
                        }
                    }
                }
                // Eliminar el último salto de línea
                if (s.endsWith("\n")) {
                    s = s.substring(0, s.length() - 1);
                }
            }
        }
        return s;
    }

}