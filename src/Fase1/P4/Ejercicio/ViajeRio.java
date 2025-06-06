package Fase1.P4.Ejercicio;

public class ViajeRio {

    public static int[][] costesMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                calcularCosteMinimo(i, j, T, C);
            }
        }

        return C;
    }

    private static int calcularCosteMinimo(int i, int j, int[][] T, int[][] C) {
        if (C[i][j] != -1) {
            return C[i][j];
        }

        if (i == j) {
            C[i][j] = 0;
            return 0;
        }

        C[i][j] = T[i][j];

        // posibles paradas intermedias
        for (int k = i + 1; k < j; k++) {
            //  costo de k a k
            int costeHastaK = calcularCosteMinimo(i, k, T, C);
            int costeDesdeK = calcularCosteMinimo(k, j, T, C);

            if (costeHastaK + costeDesdeK < C[i][j]) {
                C[i][j] = costeHastaK + costeDesdeK;
            }
        }

        return C[i][j];
    }

    public static void main(String[] args) {
        // n embarcaderos
        int n = 5;
        int[][] T = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                T[i][j] = 0;
            }
        }

        // Definimos las tarifas directas conocidas
        T[0][1] = 2;
        T[0][2] = 9;
        T[0][3] = 10;
        T[0][4] = 15;
        T[1][2] = 4;
        T[1][3] = 7;
        T[1][4] = 6;
        T[2][3] = 1;
        T[2][4] = 2;
        T[3][4] = 3;

        // Calculamos los costes mínimos
        int[][] C = costesMinimos(T);

        // Imprimimos la matriz de costos mínimos
        System.out.println("Matriz de costos mínimos C[i,j]:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!(C[i][j] >= 100)) {
                    System.out.print(C[i][j] + "\t");
                }
            }
            System.out.println();
        }

    }
}