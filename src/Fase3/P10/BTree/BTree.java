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
        E mediana; // esta es la que sube
        mediana = push(this.root, cl);
        if (up) {
            BNode<E> pnew = new BNode<E>(this.order); // nodo padre especial -> root
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
                if (current.nodeFull(this.order - 1)) {
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

        for (i = current.count - 1; i >= k; i--) {

            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i));
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

    public boolean search(E data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(BNode<E> current, E data) {
        if (current == null) {
            return false;
        }

        int[] pos = new int[1];
        boolean found = current.searchNode(data, pos);

        if (found) {
            return true;
        }

        if (pos[0] < current.childs.size() && current.childs.get(pos[0]) != null) {
            return searchRecursive(current.childs.get(pos[0]), data);
        }
        return false;
    }

    //    ---------------------------Delete------------------------------------
    public void remove(E cl) {
        down = false;
        delete(root, cl);
        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }
    }

    public boolean delete(BNode<E> node, E key) {
        if (node == null) {
            return false;
        }

        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            if (node.childs.get(pos[0]) != null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if (node.childs.get(pos[0]) != null) {
                return false;
            } else {
                boolean isDeleted = delete(node.childs.get(pos[0]), key);
                if (node.childs.get(pos[0]).count < (order - 1) / 2) {
                    fix(node, pos[0]);
                }
                return isDeleted;
            }
        }
    }

    private void removeKey(BNode<E> node, int index) {
        for(int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private E getPredecessor(BNode<E> node, int index) {
        BNode<E> current = node.childs.get(index);
        while (current.childs.get(index + 1) != null) {
            current = current.childs.get(index + 1);
        }
        return current.keys.get(current.count - 1);
    }

    private void merge(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);

        // Mover clave del padre al hijo izquierdo
        left.keys.set(left.count, parent.keys.get(index));
        left.count++;

        // Mover claves e hijos del hijo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
        }
        for (int i = 0; i <= right.count; i++) {
            left.childs.set(left.count + i, right.childs.get(i));
        }
        left.count += right.count;

        // Eliminar la clave del padre y el hijo derecho
        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }

        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    private void borrowFromLeft(BNode<E> parent, int index ) {
        BNode<E> left = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);

        // Desplazar claves e hijos del niño a la derecha
        for (int i = current.count; i >= 0; i--) {
            current.keys.set(i + 1, current.keys.get(i));
        }

        // Mover clave del padre al niño
        current.keys.set(0, parent.keys.get(index - 1));
        parent.keys.set(index - 1, left.keys.get(left.count - 1));
        left.keys.set(left.count - 1, null);

        // Mover hijo del hermano al niño
        if (left.childs.get(left.count) != null) {

            for (int i = current.count; i >= 0; i--) {
                current.childs.set(i + 1, current.childs.get(i));
            }
            current.childs.set(0, left.childs.get(left.count));
            left.childs.set(left.count, null);
        }

        current.count++;
        left.count--;
    }

    private void borrowFromRight(BNode<E> parent, int index) {
        BNode<E> right = parent.childs.get(index + 1);
        BNode<E> current = parent.childs.get(index);

        // Insertar clave del padre al final de current
        current.keys.set(current.count, parent.keys.get(index));

        // Reemplazar clave del padre con la primera del hermano derecho
        parent.keys.set(index, right.keys.get(0));

        // Desplazar las claves del hermano derecho una posición a la izquierda
        for (int i = 1; i < right.count; i++) {
            right.keys.set(i - 1, right.keys.get(i));
        }
        right.keys.set(right.count - 1, null);

        // Si tiene hijos, mover el primer hijo del hermano derecho al final de current
        if (right.childs.get(0) != null) {
            current.childs.set(current.count + 1, right.childs.get(0));
            for (int i = 1; i <= right.count; i++) {
                right.childs.set(i - 1, right.childs.get(i));
            }
            right.childs.set(right.count, null);
        }

        current.count++;
        right.count--;
    }

    private void fix(BNode<E> parent, int index) {
        if (index > 0 && parent.childs.get(index - 1).count > (order - 1) / 2) {
            borrowFromLeft(parent, index);
        }

        else if (index < parent.count && parent.childs.get(index + 1).count > (order - 1) / 2) {
            borrowFromRight(parent, index);
        }

        else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }

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

////    ---------------------------Borrow------------------------------------
//
//
//private void redistributeFromLeft(BNode<E> parent, int childIdx) {
//    BNode<E> left = parent.childs.get(childIdx - 1);
//    BNode<E> current = parent.childs.get(childIdx);
//
//    // Desplazar claves e hijos del niño a la derecha
//    for (int i = current.count; i >= 0; i--) {
//        current.keys.set(i, current.keys.get(i - 1));
//    }
//    for (int i = current.count + 1; i > 0; i--) {
//        current.childs.set(i, current.childs.get(i - 1));
//    }
//
//    // Mover clave del padre al niño
//    current.keys.set(0, parent.keys.get(childIdx - 1));
//    current.count++;
//
//    // Mover clave del hermano al padre
//    parent.keys.set(childIdx - 1, left.keys.get(left.count - 1));
//
//    // Mover hijo del hermano al niño
//    if (left.childs.get(left.count) != null) {
//        current.childs.set(0, left.childs.get(left.count));
//    }
//
//    left.count--;
//}
//
//private void redistributeFromRight(BNode<E> parent, int childIdx) {
//    BNode<E> child = parent.childs.get(childIdx);
//    BNode<E> rightSibling = parent.childs.get(childIdx + 1);
//
//    // Mover clave del padre al niño
//    child.keys.set(child.count, parent.keys.get(childIdx));
//    child.count++;
//
//    // Mover clave del hermano al padre
//    parent.keys.set(childIdx, rightSibling.keys.get(0));
//
//    // Mover hijo del hermano al niño
//    if (rightSibling.childs.get(0) != null) {
//        child.childs.set(child.count, rightSibling.childs.get(0));
//    }
//
//    // Desplazar claves e hijos del hermano a la izquierda
//    for (int i = 0; i < rightSibling.count - 1; i++) {
//        rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
//    }
//    for (int i = 0; i < rightSibling.count; i++) {
//        rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
//    }
//
//    rightSibling.count--;
//}
//
////    ---------------------------Merge------------------------------------
//
//private void mergeNodes(BNode<E> parent, int idx) {
//    BNode<E> leftChild = parent.childs.get(idx);
//    BNode<E> rightChild = parent.childs.get(idx + 1);
//
//    // Mover clave del padre al hijo izquierdo
//    leftChild.keys.set(leftChild.count, parent.keys.get(idx));
//    leftChild.count++;
//
//    // Mover claves e hijos del hijo derecho al izquierdo
//    for (int i = 0; i < rightChild.count; i++) {
//        leftChild.keys.set(leftChild.count + i, rightChild.keys.get(i));
//    }
//    for (int i = 0; i <= rightChild.count; i++) {
//        leftChild.childs.set(leftChild.count + i, rightChild.childs.get(i));
//    }
//    leftChild.count += rightChild.count;
//
//    // Eliminar la clave del padre y el hijo derecho
//    for (int i = idx; i < parent.count - 1; i++) {
//        parent.keys.set(i, parent.keys.get(i + 1));
//    }
//    for (int i = idx + 1; i < parent.count; i++) {
//        parent.childs.set(i, parent.childs.get(i + 1));
//    }
//    parent.count--;
//
//    // Si el padre es la raíz y quedó vacío
//    if (parent == root && parent.count == 0) {
//        root = leftChild;
//    }
//}
//
////    ---------------------------To String------------------------------------
