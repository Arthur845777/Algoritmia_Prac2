package Bpluss;

public class BTreepluss<E extends Comparable<E>> {

    private BNode<E> root;
    private int order;
    private boolean up;
    private BNode<E> nDes;
    private BNode<E> LastLeaf;

    public BTreepluss(int orden) {
        this.order = orden;
        this.root = null;
        this.up = false;
        this.nDes = null;
        this.LastLeaf = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.order;
    }

    public void insert(E cl) {
        if (root == null) {
            root = new BNode<E>(this.order, true);
            root.keys.set(0, cl);
            root.count = 1;
            LastLeaf = root;
            return;
        }

        up = false;
        E mediana = push(this.root, cl);
        
        if (up) {
            // Solo cuando hay split creamos nueva raíz interna
            BNode<E> pnew = new BNode<E>(this.order, false);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
            // Importante: firstLeaf sigue siendo la primera hoja
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;

        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        }

        if (current.isLeaf) {
            boolean found = current.searchNode(cl, pos);
            if (found) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }

            if (!current.nodeFull(this.order - 1)) {
                insertInLeaf(current, cl, pos[0]);
                up = false;
                return null;
            } else {
                // Split de nodo hoja
                return splitLeaf(current, cl, pos[0]);
            }
        } else {
            // NODO INTERNO: Navegar hacia abajo
            current.searchNode(cl, pos);
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
    
    private void insertInLeaf(BNode<E> leaf, E cl, int pos) {
        for (int i = leaf.count; i > pos; i--) {
            leaf.keys.set(i, leaf.keys.get(i - 1));
        }
        leaf.keys.set(pos, cl);
        leaf.count++;
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
        nDes = new BNode<E>(this.order,false);

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

    private E splitLeaf(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        posMdna = (k <= this.order / 2) ? this.order / 2 : this.order / 2 + 1;
        nDes = new BNode<E>(this.order,true);
    
        // Mover claves y hijos al nuevo nodo
        for (i = posMdna; i < this.order - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
        }

        nDes.count = (this.order - 1) - posMdna;
        current.count = posMdna;

        // Insertar el nuevo elemento en el nodo correspondiente
        if (k <= this.order / 2) {
            insertInLeaf(current, cl, k);
        } else {
            insertInLeaf(nDes, cl, k - posMdna);
        }
    
        // Enlazar nodos hoja (mantener lista doblemente enlazada)
        current.next= nDes;
        nDes.prev = current;
        if (LastLeaf != null) {
            LastLeaf.next = current;
            current.prev = LastLeaf;
        }
        // En B+, la primera clave del nodo derecho se copia hacia arriba
        E mediana = current.keys.get(current.count);
        up = true;
    
        return mediana;
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
        if(root == null){
            return;
        }
        delete(root, cl);
        if (root.count == 0) {
            root = root.childs.get(0);
        }
    }

    public boolean delete(BNode<E> node, E key) {
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

}
