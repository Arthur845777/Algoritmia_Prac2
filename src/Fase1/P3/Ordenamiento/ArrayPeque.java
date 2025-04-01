package Fase1.P3.Ordenamiento;

import java.util.Arrays;

public class ArrayPeque {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
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

//        for(Object item : arr){
//            System.out.println(item);
//        }

        System.out.println("Ordenando array de " + size + " elementos...");
        long startTime = System.currentTimeMillis();

        mergeSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");

        System.out.println("Primeros 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, 0, Math.min(5, arr.length))));
        System.out.println("Últimos 5 elementos: " + Arrays.toString(Arrays.copyOfRange(arr, Math.max(0, arr.length - 5), arr.length)));
    }
}