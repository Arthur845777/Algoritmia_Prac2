package Fase1.P1.Actividad.Teoria;

public class Par <T extends Comparable<T>> {
    private T a;
    private T b;
    public Par (T a, T b) {
        this.a = a; this.b = b;
    }

    public T getA() {
        return a;
    }
    public T getB() {
        return b;
    }

    public T max() {
        return ((this.a.compareTo(this.b) > 0) ? this.a : this.b);
    }

    public static void main (String[] args) {
        Par<Integer> pInt = new Par<Integer>(10,30);
        System.out.println("Mayor = "+ pInt.max().toString());
    }
}
