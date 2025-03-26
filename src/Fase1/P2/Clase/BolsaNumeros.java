package Fase1.P2.Clase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BolsaNumeros <T extends Number & Comparable<T>> {
    private ArrayList <T> lista = new ArrayList();

    public void add(T objeto) {
        lista.add(objeto);
    }

    public void ordenar(){
        for(int i = 0; i < lista.size()-1; i++){
            for(int j = 0; j < lista.size()-i-1; j++){
                if(lista.get(j).compareTo(lista.get(j + 1)) > 0) {
                    T temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    public void mostrar() {
        System.out.println(lista);
    }
}

class SBolsa<T> {
    private ArrayList <T> elementos = new ArrayList<>();

    public void add(T elemento) {
        elementos.add(elemento);
    }

    public void mostrar() {
        System.out.println(elementos);
    }

}
