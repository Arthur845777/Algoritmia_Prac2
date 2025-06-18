package Fase3.P10.Arbolito;
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
        return count >= maxKeys;
    }

    public boolean nodeEmpty() {
        return count == 0; 
    }

    public boolean searchNode(E data, int[] pos) {
       pos[0] = 0;

       while (pos[0] < count && keys.get(pos[0]).compareTo(keys.get(pos[0])) > 0) {
           pos[0]++;
       }

       return pos[0] < count && keys.get(pos[0]).compareTo(keys.get(pos[0])) == 0;

    }
}
