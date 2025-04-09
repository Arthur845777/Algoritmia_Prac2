package Fase1.P4.Ejercicio.Quickselect;
import java.util.ArrayList;

public class Busqueda {

    public void di(ArrayList<Integer> listita, int pedi) {
        // Caso base: si solo hay un elemento, ese es el resultado
        if (listita.size() == 1) {
            System.out.println(listita.get(0));
            return;
        }

        // Determinar el pivote
        int pivoteIndex = listita.size() / 2;
        int pivote = listita.get(pivoteIndex);

        ArrayList<Integer> listitaM = new ArrayList<>(); // Elementos mayores o iguales al pivote
        ArrayList<Integer> listitaN = new ArrayList<>(); // Elementos menores al pivote

        // Dividir la lista en dos partes
        for (int i : listita) {
            if (i < pivote) {
                listitaN.add(i);
            } else if (i > pivote) {
                listitaM.add(i);
            } else if (i == pivote && listita.indexOf(i) != pivoteIndex) {
                // Para manejar duplicados del pivote, no incluimos el pivote actual
                if (listita.indexOf(i) < pivoteIndex) {
                    listitaN.add(i);
                } else {
                    listitaM.add(i);
                }
            }
        }

        if(pedi==1){
            pedi=0;
        }else if(pedi==listita.size()){
            pedi-=1;
        }

        // Ahora decidimos en qué dirección ir
        if (pedi < listitaN.size()) {
            // El elemento está en la parte menor
            di(listitaN, pedi);
        } else if (pedi == listitaN.size()) {
            // El elemento es exactamente el pivote
            System.out.println(pivote);
            return;
        } else {
            // El elemento está en la parte mayor
            // Ajustamos pedi: restamos los elementos de la parte menor y el pivote
            pedi = pedi - listitaN.size() - 1; //
            di(listitaM, pedi);
        }
    }

}