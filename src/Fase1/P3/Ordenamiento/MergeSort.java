package Fase1.P3.Ordenamiento;

public class MergeSort {
    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int middle = arr.length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[arr.length - middle];

        // Copiar elementos a los arrays izquierdo y derecho
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

        // Combinar los dos arrays ordenados
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                result[resultIndex++] = leftArray[leftIndex++];
            } else {
                result[resultIndex++] = rightArray[rightIndex++];
            }
        }

        // Copiar elementos restantes del array izquierdo, si hay alguno
        while (leftIndex < leftArray.length) {
            result[resultIndex++] = leftArray[leftIndex++];
        }

        // Copiar elementos restantes del array derecho, si hay alguno
        while (rightIndex < rightArray.length) {
            result[resultIndex++] = rightArray[rightIndex++];
        }

        return result;
    }
}