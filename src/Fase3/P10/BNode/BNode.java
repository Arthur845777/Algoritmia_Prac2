package Fase3.P10.BNode;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    public int count;
    public ArrayList<E> keys;
    public ArrayList<BNode<E>> childs;


    public BNode(int n) {
        this.keys = new ArrayList<>();
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;

        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public boolean nodeFull(int maxKeys) {
//        return count == keys.size(); // propuestas
        return count >= maxKeys;
    }

    public boolean nodeEmpty() {
        return count == 0; // propuestas
    }

    public boolean searchNode(E data, int[] pos) {
       pos[0] = 0;

       while (pos[0] < count && ((Comparable<E>)keys).compareTo(keys.get(pos[0])) > 0) {
           pos[0]++;
       }

       return pos[0] < count && ((Comparable<E>)keys).compareTo(keys.get(pos[0])) == 0;

    }
}

//    public boolean searchNode(E data, int[] pos) {
//        int i = 0;
//
//        while (i < count && data.compareTo(keys.get(i)) > 0) {
//            i++;
//        }
//
//        pos[0] = i;
//
//        return (i < count && data.compareTo(keys.get(i)) == 0);
//    }