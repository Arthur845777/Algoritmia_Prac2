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
            if(i< n - 1) {
                this.childs.add(null);
            }
        }
    }

    public boolean nodeFull() {
        return count == keys.size(); // propuestas
    }

    public boolean nodeEmpty() {
        return count == 0; // propuestas
    }

    public boolean searchNode(E data, int[] pos) {
        int i = 0;

        while (i < count && data.compareTo(keys.get(i)) > 0) {
            i++;
        }

        pos[0] = i;

        return (i < count && data.compareTo(keys.get(i)) == 0);
    }


}
