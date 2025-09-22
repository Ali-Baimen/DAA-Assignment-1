
import Utils.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] arr) {
        Metrics.startTimer();
        quickSort(arr, 0, arr.length - 1);
        Metrics.stopTimer();
    }
    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            Metrics.enterRecursion();
            int pivotIndex = left + rnd.nextInt(right - left + 1);
            int pivotNew = partition(arr, left, right, pivotIndex);
            Metrics.exitRecursion();
            if (pivotNew - left < right - pivotNew) {
                quickSort(arr, left, pivotNew - 1);
                left = pivotNew + 1;
            } else {
                quickSort(arr, pivotNew + 1, right);
                right = pivotNew - 1;
            }
        }
    }
    private static int partition(int[] a, int l, int r, int pivotIndex) {
        Metrics.countAllocation();
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, r);
        int store = l;
        for (int i = l; i < r; i++) {
            Metrics.countComparison();
            if (a[i] < pivotValue) {
                swap(a, store, i);
                store++;
            }
        }
        swap(a, store, r);
        return store;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}