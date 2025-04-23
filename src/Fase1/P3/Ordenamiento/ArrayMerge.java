package Fase1.P3.Ordenamiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArrayMerge {
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> arr) {

        if (arr.size() <= 1) {
            return arr;
        }

        int middle = arr.size() / 2;
        ArrayList<Integer> leftArray = new ArrayList<>(arr.subList(0, middle));
        ArrayList<Integer> rightArray = new ArrayList<>(arr.subList(middle, arr.size()));

        ArrayList<Integer> sortedLeftArray = mergeSort(leftArray);
        ArrayList<Integer> sortedRightArray = mergeSort(rightArray);

        return merge(sortedLeftArray, sortedRightArray);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {

        ArrayList<Integer> result = new ArrayList<>(leftArray.size() + rightArray.size());

        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < leftArray.size() && rightIndex < rightArray.size()) {
            if (leftArray.get(leftIndex) <= rightArray.get(rightIndex)) {
                result.add(leftArray.get(leftIndex++));
            } else {
                result.add(rightArray.get(rightIndex++));
            }
        }

        while (leftIndex < leftArray.size()) {
            result.add(leftArray.get(leftIndex++));
        }

        while (rightIndex < rightArray.size()) {
            result.add(rightArray.get(rightIndex++));
        }

        return result;
    }

    public static void main(String[] args) {
        int size = 100000;
        ArrayList<Integer> arr = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            arr.add((int) (Math.random() * size));
        }

//        for(Integer item : arr){
//            System.out.println(item);
//        }

        System.out.println("Ordenando ArrayList de " + size + " elementos...");
        long startTime = System.currentTimeMillis();

        ArrayList<Integer> sortedArr = mergeSort(arr);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");

        System.out.println("Primeros 5 elementos: " + sortedArr.subList(0, Math.min(5, sortedArr.size())));
        System.out.println("Últimos 5 elementos: " + sortedArr.subList(Math.max(0, sortedArr.size() - 5), sortedArr.size()));
    }
}
