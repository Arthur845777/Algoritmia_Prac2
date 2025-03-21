package Fase1.P1.Actividad.Teoria;

class Contenedor <T> {
    private T array[];
    private int cont;

    public Contenedor(int n) {
        array = (T[]) new Object[n];
        cont = 0;
    }

    public void add(T ele) {
        if (cont < array.length) {
            array[cont++] = ele;
        }
    }

    public T search(T ele) {
        for (int i = 0; i < cont; i++) {
            if (array[i].equals(ele)) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Contenedor<Integer> c = new Contenedor<Integer>(6);

    }
}