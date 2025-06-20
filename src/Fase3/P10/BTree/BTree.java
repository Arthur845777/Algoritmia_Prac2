package Fase3.P10.BTree;

//-----------------JOSE----------------------
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//-----------------JOSE----------------------

import Fase3.P10.BNode.BNode;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int order; // num maximo de hijos
    private boolean up;
    private boolean down; // esto tambien lo vamos a necesitar para borrar
    private BNode<E> nDes; // derecha

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
            BNode<E> pnew;
            mediana = push(this.root, cl);
            if (up) {
                pnew = new BNode<E>(this.order); // nodo padre especial -> root
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
            boolean fl;
            fl = current.searchNode(cl, pos);

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

    private E dividedNode(BNode<E> current, E clave, int position) {
        BNode<E> rd = nDes;
        int i, posMdna;

        posMdna = (position <= this.order / 2) ? this.order / 2 : this.order / 2 + 1;
        nDes = new BNode<E>(this.order);

        for (i = posMdna; i < this.order - 1; i++) {  // va de la mitad hacia adelante
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.order - 1) - posMdna;
        current.count = posMdna;

        if (position <= this.order / 2) {
            putNode(current, clave, rd, position); // 1ra mitad
        } else {
            putNode(nDes, clave, rd, position - posMdna); // 2da mitad
        }

        // mediana q sube p
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    // desplaza nomas, ose actualiza las posiciones
    private void putNode(BNode<E> current, E clave, BNode<E> nDes, int k) {
        for (int i = current.count - 1; i >= k; i--) {

            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }

        current.keys.set(k, clave);
        current.childs.set(k + 1, nDes);
        current.count++;
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

        // comprobamos si - la pos es valida / existe un hijo en esa pos
        if (pos[0] < current.childs.size() && current.childs.get(pos[0]) != null) {
            return searchRecursive(current.childs.get(pos[0]), data);
        }
        return false;
    }

    //    ---------------------------Delete------------------------------------
    public void remove(E cl) {
        if (cl == null) {
            return; // sin dato no se puede trabajar
        }
        delete(root, cl);
    }

    public boolean delete(BNode<E> node, E key) {
        if (node == null) {
            return false;
        }

        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            if (node.childs.get(pos[0]) == null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if (node.childs.get(pos[0]) == null) {
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
        for (int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private E getPredecessor(BNode<E> node, int index) {
        BNode<E> current = node.childs.get(index);
        while (current.childs.get(current.count) != null) {
            current = current.childs.get(current.count);
        }
        return current.keys.get(current.count - 1);
    }

    private void fix(BNode<E> parent, int index) {
        if (index > 0 && parent.childs.get(index - 1).count > (order - 1) / 2) {
            borrowFromLeft(parent, index);

        } else if (index < parent.count && parent.childs.get(index + 1).count > (order - 1) / 2) {
            borrowFromRight(parent, index);

        } else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }

    private void borrowFromLeft(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);

        for (int i = current.count; i >= 0; i--) {
            current.keys.set(i + 1, current.keys.get(i));
        }

        current.keys.set(0, parent.keys.get(index - 1));
        parent.keys.set(index - 1, left.keys.get(left.count - 1));
        left.keys.set(left.count - 1, null);

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

        current.keys.set(current.count, parent.keys.get(index));
        parent.keys.set(index, right.keys.get(0));

        for (int i = 1; i < right.count; i++) {
            right.keys.set(i - 1, right.keys.get(i));
        }

        right.keys.set(right.count - 1, null);

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

    private void merge(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);

        // Mover clave del padre al hijo izquierdo
        left.keys.set(left.count, parent.keys.get(index));
        left.count++;

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


    //    ------------------PARTE DE JOSE--------------------------------
    //Ejercicio 3 :v
    // Clase auxiliar que representa la información de cada nodo leída desde el archivo
    class NodoArchivo<E extends Comparable<E>> {
        int nivel;                // Nivel del nodo en el árbol (0 = raíz)
        int id;                   // Identificador único del nodo
        ArrayList<E> claves;      // Lista de claves del nodo

        // Constructor que inicializa los atributos del nodo
        NodoArchivo(int nivel, int id, ArrayList<E> claves) {
            this.nivel = nivel;
            this.id = id;
            this.claves = claves;
        }
    }

    // Clase de excepción personalizada para indicar errores durante la construcción del árbol
    public static class ItemNoFound extends Exception {
        public ItemNoFound(String message) {
            super(message);
        }
    }

    // Método principal para construir el árbol B desde un archivo
    public void building_BTree(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Leer la primera línea: indica el orden del árbol
            String linea = br.readLine();
            if (linea == null) throw new Exception("Archivo vacío");

            // Establecer el orden del árbol (t) leído desde la primera línea
            this.order = Integer.parseInt(linea.trim());

            // Mapa para enlazar cada id con su nodo real (BNode)
            Map<Integer, BNode<E>> mapaNodos = new HashMap<>();

            // Lista temporal para guardar los nodos leídos del archivo antes de enlazarlos
            List<NodoArchivo<E>> nodosArchivo = new ArrayList<>();

            // Leer el resto de líneas del archivo
            while ((linea = br.readLine()) != null) {
                // Separar los valores por coma: nivel, id, claves
                String[] partes = linea.trim().split(",");

                // Parsear el nivel y el id del nodo
                int nivel = Integer.parseInt(partes[0].trim());
                int id = Integer.parseInt(partes[1].trim());

                // Crear lista de claves del nodo
                ArrayList<E> claves = new ArrayList<>();
                for (int i = 2; i < partes.length; i++) {
                    claves.add((E) (Comparable) Integer.parseInt(partes[i].trim()));
                }

                // Agregar el nodo leído a la lista
                nodosArchivo.add(new NodoArchivo<>(nivel, id, claves));
            }

            // Ordenar los nodos por nivel (del más alto al más bajo)
            // nodosArchivo.sort(Comparator.comparingInt(n -> n.nivel));

            // Determinar el nivel más profundo del árbol
            int nivelMax = nodosArchivo.get(nodosArchivo.size() - 1).nivel;

            // Crear nodos reales del árbol B (BNode) sin hijos todavía
            for (NodoArchivo<E> nodoData : nodosArchivo) {
                // Crear nodo B con el orden definido
                BNode<E> nodo = new BNode<>(this.order);

                // Asignar claves al nodo
                for (int i = 0; i < nodoData.claves.size(); i++) {
                    nodo.keys.set(i, nodoData.claves.get(i));
                }

                // Establecer el número de claves
                nodo.count = nodoData.claves.size();

                // Mapear el id del nodo con su objeto BNode
                mapaNodos.put(nodoData.id, nodo);
            }

            // Agrupar los nodos por nivel en un mapa: nivel → lista de nodos
            Map<Integer, List<NodoArchivo<E>>> nodosPorNivel = new HashMap<>();
            for (NodoArchivo<E> nodo : nodosArchivo) {
                nodosPorNivel.computeIfAbsent(nodo.nivel, k -> new ArrayList<>()).add(nodo);
            }

            // Enlazar los nodos padre con sus hijos (de nivel en nivel)
            for (int nivel = 0; nivel < nivelMax; nivel++) {
                // Obtener los nodos del nivel actual (padres)
                List<NodoArchivo<E>> padres = nodosPorNivel.get(nivel);

                // Obtener los nodos del siguiente nivel (hijos)
                List<NodoArchivo<E>> hijos = nodosPorNivel.get(nivel + 1);

                int indexHijo = 0; // Índice para recorrer la lista de hijos

                // Para cada padre, enlazar con la cantidad correcta de hijos (claves + 1)
                for (NodoArchivo<E> padre : padres) {
                    BNode<E> nodoPadre = mapaNodos.get(padre.id);
                    int hijosEsperados = padre.claves.size() + 1;

                    for (int i = 0; i < hijosEsperados; i++) {
                        if (indexHijo >= hijos.size()) {
                            // Error si ya no hay más hijos disponibles
                            throw new ItemNoFound("No se pudo enlazar hijo para el nodo " + padre.id);
                        }

                        // Obtener el nodo hijo y asignarlo al padre en la posición correspondiente
                        BNode<E> nodoHijo = mapaNodos.get(hijos.get(indexHijo).id);
                        nodoPadre.childs.set(i, nodoHijo);
                        indexHijo++;
                    }
                }
            }

            // Buscar el nodo raíz (el único que está en el nivel 0) y asignarlo al árbol
            for (NodoArchivo<E> nodoData : nodosArchivo) {
                if (nodoData.nivel == 0) {
                    this.root = mapaNodos.get(nodoData.id);
                    return;
                }
            }

            // Si no se encontró la raíz, lanzar error
            throw new ItemNoFound("No se encontró la raíz del árbol");

        } catch (IOException e) {
            // Error si hubo problemas al leer el archivo
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
        }
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







//-------------------------------------- Metodo imprimir
//    public String toString() {
//        String s = "";
//        if (isEmpty()) {
//            s += "BTree is empty...";
//        } else {
//            s = writeTree(this.root);
//        }
//        return s;
//    }
//
//    private String writeTree(BNode<E> current) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("%-10s %-20s %-10s %-10s\n", "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
//        sb.append(recorrerYFormatear(current, null));
//        return sb.toString();
//    }
//
//    private String recorrerYFormatear(BNode<E> nodo, BNode<E> padre) {
//        if (nodo == null) return "";
//
//        StringBuilder sb = new StringBuilder();
//
//        // Id del nodo
//        String idNodo = String.valueOf(nodo.idNode);
//
//        // Claves del nodo
//        String claves = "(";
//        for (int i = 0; i < nodo.count; i++) {
//            claves += nodo.keys.get(i);
//            if (i < nodo.count - 1) claves += ", ";
//        }
//        claves += ")";
//
//        // Id del padre
//        String idPadre = (padre == null) ? "--" : "[" + padre.idNode + "]";
//
//        // Ids de los hijos
//        StringBuilder hijos = new StringBuilder();
//        boolean tieneHijos = false;
//        for (int i = 0; i <= nodo.count; i++) {
//            if (nodo.childs.get(i) != null) {
//                if (tieneHijos) hijos.append(", ");
//                hijos.append(nodo.childs.get(i).idNode);
//                tieneHijos = true;
//            }
//        }
//        String idHijos = tieneHijos ? "[" + hijos.toString() + "]" : "--";
//
//        sb.append(String.format("%-10s %-20s %-10s %-10s\n", idNodo, claves, idPadre, idHijos));
//
//        // Recorrer hijos
//        for (int i = 0; i <= nodo.count; i++) {
//            if (nodo.childs.get(i) != null) {
//                sb.append(recorrerYFormatear(nodo.childs.get(i), nodo));
//            }
//        }
//
//        return sb.toString();
//    }