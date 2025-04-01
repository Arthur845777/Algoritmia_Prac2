package Fase1.P3.Ordenamiento;

import java.util.Arrays;

public class MergeSortInPlace {
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // copiammos el array
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }

        System.out.println("Ordenando array de " + size + " elementos con MergeSort in-place...");
        long startTime = System.currentTimeMillis();

        mergeSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");

        System.out.println("Primeros 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, 0, Math.min(5, arr.length))));
        System.out.println("Últimos 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, Math.max(0, arr.length - 5), arr.length)));
    }
}
