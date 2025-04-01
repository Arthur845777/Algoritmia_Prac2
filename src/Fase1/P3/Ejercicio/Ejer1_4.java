package Fase1.P3.Ejercicio;

import java.util.HashMap;
import java.util.Map;

public class Ejer1_4 {

    public static int BM(int[] v, int n) {
        int m = v[0];                       // O(1)
        for (int i = 1; i < n - 1; i++) {   // O(1) + O(n) + O(n)
            if (v[i] > m) {                 // O(1)
                m = v[i];                   // O(1)
            }
        }
        return m;                           // O(1)
    }

    public static int ConteoL(int[] v, int n) {
        int conteo = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (v[i] == v[j]) {
                    conteo += 1;
                }
            }
        }
        return conteo;
    }

    public static int moda(int[] v) {
        Map<Integer, Integer> frecuencia = new HashMap<>(); // O(1)
        int maxFrecuencia = 0;                              // O(1)
        int moda = v[0];                                    // O(1)

        for (int num : v) {                                 // O(n)
            int f = frecuencia.getOrDefault(num, 0) + 1; // O(1)
            frecuencia.put(num, f);                         // O(1)

            if (f > maxFrecuencia) {                        // O(1)
                maxFrecuencia = f;                          // O(1)
                moda = num;                                 // O(1)
            }
        }
        return moda;
    }

    public static int potenciaRapida(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 0) {
            int mitad = potenciaRapida(x, y / 2);
            return mitad * mitad;
        }
        else {
            return x * potenciaRapida(x, y - 1);
        }
    }

}
