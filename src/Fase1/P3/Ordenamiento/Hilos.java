package Fase1.P3.Ordenamiento;

import java.util.Arrays;

public class Hilos {
    // Array auxiliar global para evitar creaciones repetidas
    private static int[] auxiliar;

    public static void mergeSort(int[] arr) {
        // Inicializar el array auxiliar una sola vez
        auxiliar = new int[arr.length];
        mergeSortInternal(arr, 0, arr.length - 1);
    }

    private static void mergeSortInternal(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            // Usar un umbral para cambiar a insertion sort en arrays pequeños
            if (derecha - izquierda <= 10) {
                insertionSort(arr, izquierda, derecha);
            } else {
                mergeSortInternal(arr, izquierda, medio);
                mergeSortInternal(arr, medio + 1, derecha);
                merge(arr, izquierda, medio, derecha);
            }
        }
    }

    private static void merge(int[] arr, int izquierda, int medio, int derecha) {
        // Copiar solo la sección necesaria al array auxiliar
        for (int i = izquierda; i <= derecha; i++) {
            auxiliar[i] = arr[i];
        }

        int i = izquierda;
        int j = medio + 1;
        int k = izquierda;

        while (i <= medio && j <= derecha) {
            if (auxiliar[i] <= auxiliar[j]) {
                arr[k++] = auxiliar[i++];
            } else {
                arr[k++] = auxiliar[j++];
            }
        }

        // Solo necesitamos copiar los elementos restantes de la parte izquierda
        // (los de la derecha ya están en su posición)
        while (i <= medio) {
            arr[k++] = auxiliar[i++];
        }
    }

    // Insertion sort para arrays pequeños
    private static void insertionSort(int[] arr, int inicio, int fin) {
        for (int i = inicio + 1; i <= fin; i++) {
            int valorActual = arr[i];
            int j = i - 1;

            while (j >= inicio && arr[j] > valorActual) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = valorActual;
        }
    }

    // Versión multihilo del MergeSort
    public static void parallelMergeSort(int[] arr) {
        // Inicializar el array auxiliar una sola vez
        auxiliar = new int[arr.length];

        // Determinar el número de hilos disponibles
        int numThreads = Runtime.getRuntime().availableProcessors();

        // Crear y ejecutar hilos
        Thread[] threads = new Thread[numThreads];
        int segmentSize = arr.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                int start = threadId * segmentSize;
                int end = (threadId == numThreads - 1) ? arr.length - 1 : (threadId + 1) * segmentSize - 1;
                mergeSortInternal(arr, start, end);
            });
            threads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Combinar los segmentos ordenados
        for (int size = segmentSize; size < arr.length; size *= 2) {
            for (int left = 0; left < arr.length; left += 2 * size) {
                int mid = Math.min(left + size - 1, arr.length - 1);
                int right = Math.min(left + 2 * size - 1, arr.length - 1);
                merge(arr, left, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] arr = new int[size];
        int[] arrParallel = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
            arrParallel[i] = arr[i]; // Copiar para comparación justa
        }

        System.out.println("Ordenando array de " + size + " elementos con MergeSort optimizado...");
        long startTime = System.currentTimeMillis();
        mergeSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución MergeSort optimizado: " + (endTime - startTime) + " ms");

        System.out.println("\nOrdenando array de " + size + " elementos con MergeSort paralelo...");
        startTime = System.currentTimeMillis();
        parallelMergeSort(arrParallel);
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución MergeSort paralelo: " + (endTime - startTime) + " ms");

        // Verificar que ambos resultados sean iguales
        boolean sonIguales = Arrays.equals(arr, arrParallel);
        System.out.println("\n¿Los resultados son iguales? " + sonIguales);

        System.out.println("\nPrimeros 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, 0, Math.min(5, arr.length))));
        System.out.println("Últimos 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, Math.max(0, arr.length - 5), arr.length)));
    }
}