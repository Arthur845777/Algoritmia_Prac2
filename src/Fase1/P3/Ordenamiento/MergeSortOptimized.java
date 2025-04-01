package Fase1.P3.Ordenamiento;

import java.util.Arrays;

public class MergeSortOptimized {
    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        if (arr.length < INSERTION_SORT_THRESHOLD) {
            insertionSort(arr);
            return;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }

        System.out.println("Ordenando array de " + size + " elementos con MergeSort optimizado...");
        long startTime = System.currentTimeMillis();

        mergeSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");

        // Verificar que está ordenado (primeros y últimos elementos)
        System.out.println("Primeros 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, 0, Math.min(5, arr.length))));
        System.out.println("Últimos 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, Math.max(0, arr.length - 5), arr.length)));
    }
}
