package Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Controlator;
import Fase1.P2.Ejercicio.SimuladorOperacionFinancieraGenerico.Model.OperacionFinanciera;

import java.util.ArrayList;
import java.util.Iterator;

public class Control<T> {
    private ArrayList<T> operacionFinanciera;

    public Control() {
        this.operacionFinanciera = new ArrayList<T>();
    }

    public void add(T obj) {
        operacionFinanciera.add(obj);
    }

    public int size(){
        return operacionFinanciera.size();
    }

    public T get(int index) {
        if (index >= 0 && index < operacionFinanciera.size()) {
            return operacionFinanciera.get(index);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public Iterator< T > iterator() {
        return operacionFinanciera.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-15s %-15s %-10s%n", "Index", "Monto", "Interes", "Plazo"));
        sb.append("------------------------------------------------------------\n");

        Iterator<T> iterator = operacionFinanciera.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T elemento = iterator.next();
            if (elemento instanceof OperacionFinanciera<?>) {
                    OperacionFinanciera<?> op = (OperacionFinanciera<?>) elemento;
                sb.append(String.format("%-10d %-15.2f %-15.2f %-10d%n",
                        index,
                        op.getMonto().doubleValue(),
                        op.getTasaInteres().doubleValue(),
                        op.getPlazo()));
            }
            index++;
        }

        return sb.toString();
    }


}
