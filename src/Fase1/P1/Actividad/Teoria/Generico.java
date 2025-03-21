package Fase1.P1.Actividad.Teoria;

public class Generico {
    static <T extends Comparable<T>> boolean linearSearch(T [] vec, T x) {
        for(T e : vec) {
            if( e.equals(x) ) { return(true); } }
        return(false);
    }

    static boolean linearSearch(Object[] vec, Object x) {
        for(Object e : vec) {
            if( e.equals(x) ) { return(true); } }
        return(false);
    }

    public static void main (String [] args) {
        String[] v = {"Perez","Sanchez","Rodriguez"};
        Integer[] w = {12,34,56};
        System.out.println(linearSearch(v,"Sanchez"));
        System.out.println(linearSearch(w,34));
        System.out.println(linearSearch(w,"Sanchez"));
    }

}

class Caja <T> {
    private T dato;

    public Caja(){
        super();
    }

    public T dame() {
        return this.dato;
    }

    public void pon( T x ){
        this.dato = x;
    }

    public static void main( String args[]) {
        Caja <Integer> c = new Caja<Integer>();
        c.pon(46);
        Integer n = c.dame();
        System.out.println(n);
    }

}
