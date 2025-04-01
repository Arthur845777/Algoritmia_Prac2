package Fase1.P3.Actividad;

public class Act_Algoritmos_Iter {

    public static void intercambia(int x, int y){
        int aux;    // O(1)
        aux = x;    // O(1)
        x = y;      // O(1)
        y = aux;    // O(1)
    }

    public static int max(int x, int y){
        int result;         // O(1)
        if(x > y){          // O(1)
            result = x;     // O(1)
        } else {            // O(1)
            result = y;     // O(1)
        }
        return result;      // O(1)
    }

    public static int suma(int[] v, int size) {
        int result = 0;                     // O(1)
        for (int i = 0; i < size; i++) {    // O(1) + O(n)
            result += v[i];                 // O(n)
        }
        return result;                      // O(1)
    }

    public static void ordenar(int[] v, int size) {
        int i, j, aux;                          // O(1)
        for (i = 0; i < size - 1; i++) {        // O(1) + O(n) + O(n)
            for (j = 0; j < size - 1; j++) {    // O(1) + O(n) + O(n)
                if (v[j] > v[j + 1]) {          // O(1)
                    aux = v[j];                 // O(1)
                    v[j] = v[j + 1];            // O(1)
                    v[j + 1] = aux;             // O(1)
                }
            }
        }
    }


}
