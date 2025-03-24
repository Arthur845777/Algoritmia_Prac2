package Fase1.P2.Actividad.Bolsa;

import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa < T > implements Iterable < T > {

    private ArrayList <T> lista = new ArrayList <T> ();
    private int max;

    public Bolsa(int tope) {
        super();
        this.max = tope;
    }

    public Bolsa() {
        super();
    }

    public Bolsa add(T objeto) {
        if (lista.size() >= max) {
            lista.add(objeto);
        } else {
            throw new RuntimeException("no caben mas");
        }
        return null;
    }

    public Iterator < T > iterator() {
        return lista.iterator();
    }
}