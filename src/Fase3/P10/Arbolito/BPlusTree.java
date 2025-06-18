package Fase3.P10.Arbolito;

import java.util.*;

public class BPlusTree<E extends Comparable<E>> {
    private BPlusNode<E> root;
    private int order;
    private BPlusNode<E> firstLeaf; // Puntero al primer nodo hoja para recorridos secuenciales

    public BPlusTree(int order) {
        this.order = order;
        this.root = null;
        this.firstLeaf = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int getOrder() {
        return this.order;
    }

    // Clase interna para los nodos del B+ Tree
    public static class BPlusNode<E extends Comparable<E>> {
        boolean isLeaf;
        List<E> keys;                    // ArrayList para acceso eficiente por índice
        List<BPlusNode<E>> children;     // ArrayList para los hijos (solo nodos internos)
        BPlusNode<E> next;               // Puntero al siguiente nodo hoja
        BPlusNode<E> prev;               // Puntero al nodo hoja anterior
        int count;

        public BPlusNode(int order, boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new ArrayList<>(Collections.nCopies(order - 1, null));
            this.count = 0;
            this.next = null;
            this.prev = null;
            
            if (!isLeaf) {
                this.children = new ArrayList<>(Collections.nCopies(order, null));
            }
        }

        public boolean isFull() {
            return count >= keys.size();
        }

        public boolean isUnderflow(int minKeys) {
            return count < minKeys;
        }

        // Buscar posición donde insertar/buscar la clave
        public int findPosition(E key) {
            int pos = 0;
            while (pos < count && keys.get(pos).compareTo(key) < 0) {
                pos++;
            }
            return pos;
        }
    }

    public void insert(E key) {
        if (root == null) {
            root = new BPlusNode<>(order, true);
            root.keys.set(0, key);
            root.count = 1;
            firstLeaf = root;
            return;
        }

        BPlusNode<E> newChild = insertHelper(root, key);
        
        // Si hay división en la raíz, crear nueva raíz
        if (newChild != null) {
            BPlusNode<E> newRoot = new BPlusNode<>(order, false);
            newRoot.keys.set(0, newChild.keys.get(0));
            newRoot.children.set(0, root);
            newRoot.children.set(1, newChild);
            newRoot.count = 1;
            root = newRoot;
        }
    }

    private BPlusNode<E> insertHelper(BPlusNode<E> node, E key) {
        if (node.isLeaf) {
            return insertIntoLeaf(node, key);
        } else {
            return insertIntoInternal(node, key);
        }
    }

    private BPlusNode<E> insertIntoLeaf(BPlusNode<E> leaf, E key) {
        int pos = leaf.findPosition(key);
        
        // Verificar si la clave ya existe
        if (pos < leaf.count && leaf.keys.get(pos).equals(key)) {
            System.out.println("Clave duplicada: " + key);
            return null;
        }

        // Insertar en posición correcta
        for (int i = leaf.count; i > pos; i--) {
            leaf.keys.set(i, leaf.keys.get(i - 1));
        }
        leaf.keys.set(pos, key);
        leaf.count++;

        // Si el nodo no está lleno, no hay división
        if (!leaf.isFull()) {
            return null;
        }

        // División del nodo hoja
        return splitLeaf(leaf);
    }

    private BPlusNode<E> splitLeaf(BPlusNode<E> leaf) {
        int mid = (leaf.count + 1) / 2;
        BPlusNode<E> newLeaf = new BPlusNode<>(order, true);

        // Mover la mitad de las claves al nuevo nodo
        for (int i = mid; i < leaf.count; i++) {
            newLeaf.keys.set(i - mid, leaf.keys.get(i));
            leaf.keys.set(i, null);
        }

        newLeaf.count = leaf.count - mid;
        leaf.count = mid;

        // AQUÍ ES DONDE USAMOS LA LÓGICA DE LINKEDLIST:
        // Enlazar los nodos hoja en una lista doblemente enlazada
        newLeaf.next = leaf.next;
        newLeaf.prev = leaf;
        if (leaf.next != null) {
            leaf.next.prev = newLeaf;
        }
        leaf.next = newLeaf;

        return newLeaf;
    }

    private BPlusNode<E> insertIntoInternal(BPlusNode<E> internal, E key) {
        int pos = internal.findPosition(key);
        
        // Ajustar posición para nodos internos
        if (pos < internal.count && key.compareTo(internal.keys.get(pos)) >= 0) {
            pos++;
        }

        BPlusNode<E> newChild = insertHelper(internal.children.get(pos), key);
        
        if (newChild == null) {
            return null; // No hubo división
        }

        // Insertar la clave promocionada
        E promotedKey = newChild.keys.get(0);
        
        // Hacer espacio para la nueva clave
        for (int i = internal.count; i > pos; i--) {
            internal.keys.set(i, internal.keys.get(i - 1));
            internal.children.set(i + 1, internal.children.get(i));
        }
        
        internal.keys.set(pos, promotedKey);
        internal.children.set(pos + 1, newChild);
        internal.count++;

        // Si no está lleno, no hay división
        if (!internal.isFull()) {
            return null;
        }

        // División del nodo interno
        return splitInternal(internal);
    }

    private BPlusNode<E> splitInternal(BPlusNode<E> internal) {
        int mid = internal.count / 2;
        BPlusNode<E> newInternal = new BPlusNode<>(order, false);

        // La clave del medio se promociona, no se copia
        E promotedKey = internal.keys.get(mid);
        
        // Mover claves e hijos al nuevo nodo
        for (int i = mid + 1; i < internal.count; i++) {
            newInternal.keys.set(i - mid - 1, internal.keys.get(i));
            internal.keys.set(i, null);
        }
        
        for (int i = mid + 1; i <= internal.count; i++) {
            newInternal.children.set(i - mid - 1, internal.children.get(i));
            internal.children.set(i, null);
        }

        newInternal.count = internal.count - mid - 1;
        internal.count = mid;
        
        // Limpiar la clave promocionada del nodo original
        internal.keys.set(mid, null);

        return newInternal;
    }

    public boolean search(E key) {
        return searchHelper(root, key);
    }

    private boolean searchHelper(BPlusNode<E> node, E key) {
        if (node == null) {
            return false;
        }

        if (node.isLeaf) {
            int pos = node.findPosition(key);
            return pos < node.count && node.keys.get(pos).equals(key);
        } else {
            int pos = node.findPosition(key);
            if (pos < node.count && key.compareTo(node.keys.get(pos)) >= 0) {
                pos++;
            }
            return searchHelper(node.children.get(pos), key);
        }
    }

    // AQUÍ ES DONDE SE APROVECHA LA LINKEDLIST DE HOJAS:
    // Recorrido secuencial usando los enlaces entre hojas
    public List<E> rangeQuery(E start, E end) {
        List<E> result = new ArrayList<>();
        BPlusNode<E> current = findFirstLeaf(start);
        
        // Recorremos las hojas usando los punteros next (como una LinkedList)
        while (current != null) {
            for (int i = 0; i < current.count; i++) {
                E key = current.keys.get(i);
                if (key.compareTo(start) >= 0 && key.compareTo(end) <= 0) {
                    result.add(key);
                } else if (key.compareTo(end) > 0) {
                    return result;
                }
            }
            current = current.next; // Siguiente hoja en la "LinkedList" de hojas
        }
        
        return result;
    }

    private BPlusNode<E> findFirstLeaf(E key) {
        BPlusNode<E> current = root;
        
        while (current != null && !current.isLeaf) {
            int pos = current.findPosition(key);
            if (pos < current.count && key.compareTo(current.keys.get(pos)) >= 0) {
                pos++;
            }
            current = current.children.get(pos);
        }
        
        return current;
    }

    // OTRA VENTAJA DE LA LINKEDLIST DE HOJAS:
    // Recorrido secuencial de todas las claves usando los enlaces
    public List<E> getAllKeysInOrder() {
        List<E> result = new ArrayList<>();
        BPlusNode<E> current = firstLeaf;
        
        // Recorremos todas las hojas usando los punteros next
        while (current != null) {
            for (int i = 0; i < current.count; i++) {
                result.add(current.keys.get(i));
            }
            current = current.next; // Siguiente hoja
        }
        
        return result;
    }

    // Método que demuestra el uso de la LinkedList de hojas
    public void printAllKeysSequentially() {
        System.out.print("Recorrido secuencial de hojas: ");
        BPlusNode<E> current = firstLeaf;
        
        while (current != null) {
            System.out.print("[");
            for (int i = 0; i < current.count; i++) {
                System.out.print(current.keys.get(i));
                if (i < current.count - 1) System.out.print(", ");
            }
            System.out.print("]");
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    // Método para imprimir el árbol (útil para debugging)
    public void printTree() {
        if (root != null) {
            printTreeHelper(root, 0);
            System.out.println("\n--- Estructura de hojas enlazadas ---");
            printAllKeysSequentially();
        }
    }

    private void printTreeHelper(BPlusNode<E> node, int level) {
        if (node != null) {
            System.out.print("Nivel " + level + " " + (node.isLeaf ? "(Hoja)" : "(Interno)") + ": ");
            for (int i = 0; i < node.count; i++) {
                System.out.print(node.keys.get(i) + " ");
            }
            System.out.println();

            if (!node.isLeaf) {
                for (int i = 0; i <= node.count; i++) {
                    printTreeHelper(node.children.get(i), level + 1);
                }
            }
        }
    }

    // Método que usa tu LinkedList para devolver las hojas como una lista
    public LinkedList<BPlusNode<E>> getLeafNodes() {
        LinkedList<BPlusNode<E>> leafList = new LinkedList<>();
        BPlusNode<E> current = firstLeaf;
        
        while (current != null) {
            leafList.insertLast(current);
            current = current.next;
        }
        
        return leafList;
    }
}