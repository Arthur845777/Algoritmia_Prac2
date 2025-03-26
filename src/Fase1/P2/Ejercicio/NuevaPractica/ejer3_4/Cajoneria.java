package Fase1.P2.Ejercicio.NuevaPractica.ejer3_4;

import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T>  implements Iterable<Caja<?>> {
    private ArrayList <Caja<?>> lista = new ArrayList <> ();
    private int max;

    public Cajoneria() {
        super();
    }

    public void add(Caja objeto) {
        lista.add(objeto);
    }

    public void mostrarCajas() {
        System.out.println("\n");
        for (Caja<?> caja : lista) {
            System.out.println(caja);
        }
    }

    public String search(Caja elemento) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<?> caja = lista.get(i);
            if (caja.getElemento() != null && caja.getElemento().equals(elemento)) {
                return "Elemento encontrado en la posición " + i + ", en la caja de color " + caja.getColor();
            }
        }
        return "Elemento no encontrado";
    }

    public String delete(Caja elemento) {
        for (Caja<?> i : lista) {
            if (i != null && i.equals(elemento)) {
                Object eliminado = i.getElemento();
                i.vaciar();
                return "Ha sido eliminado";
            }
        }
        return "No existe el elemento";
    }

    public Iterator<Caja<?>> iterator() {
        return lista.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-15s %-15s%n",
                "Posicion", "Color Caja", "Objeto"));
        sb.append("------------------------------------------------------------\n");

        Iterator<Caja<?>> iterator = lista.iterator();
        int index = 1;

        while (iterator.hasNext()) {
            Caja<?> caja = iterator.next();
            sb.append(String.format("%-10d %-15s %-15s%n",
                    index,
                    caja.getColor(),
                    caja.getElemento()));
            index++;
        }

        return sb.toString();
    }
}