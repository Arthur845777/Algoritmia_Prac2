package Fase1.P3.Ordenamiento;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int middle = arr.length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[arr.length - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = arr[i];
        }
        for (int i = middle; i < arr.length; i++) {
            rightArray[i - middle] = arr[i];
        }

        int[] sortedLeftArray = mergeSort(leftArray);
        int[] sortedRightArray = mergeSort(rightArray);

        return merge(sortedLeftArray, sortedRightArray);
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        int[] result = new int[leftArray.length + rightArray.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                result[resultIndex++] = leftArray[leftIndex++];
            } else {
                result[resultIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftArray.length) {
            result[resultIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            result[resultIndex++] = rightArray[rightIndex++];
        }

        return result;
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