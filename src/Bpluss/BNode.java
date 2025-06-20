package Bpluss;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> implements Comparable<BNode<E>> {
    public int count;
    public ArrayList<E> keys;
    public ArrayList<BNode<E>> childs;
    public boolean isLeaf;
    public BNode<E> next; 
    public BNode<E> prev; 

    public BNode(int n, boolean isLeaf) {
        this.keys = new ArrayList<>();
        this.childs = new ArrayList<BNode<E>>();
        this.count = 0;
        this.isLeaf = isLeaf;
        this.next = null;
        this.prev = null;
        for (int i = 0; i < n   ; i++) {
            this.keys.add(null);
        }
        
        if (!isLeaf) {
            for (int i = 0; i < n; i++) {
                this.childs.add(null);
            }
        }
    }

    public boolean nodeFull(int maxKeys) {
        return count >= maxKeys;
    }

    public boolean nodeEmpty() {
        return count == 0; 
    }


    public boolean searchNode(E data, int[] pos) {
       pos[0] = 0;

       while (pos[0] < count && ((Comparable<E>)data).compareTo(keys.get(pos[0])) > 0) {
           pos[0]++;
       }
       if(isLeaf){
           return pos[0] < count && ((Comparable<E>)data).compareTo(keys.get(pos[0])) == 0;
       }
       return false;

    }

    @Override
    public int compareTo(BNode<E> o) {
        return this.keys.get(0).compareTo(o.keys.get(0));
    }
}

