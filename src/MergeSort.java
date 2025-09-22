

import Utils.Metrics;

public class MergeSort {
    public static void sort(int[] arr) {
        Metrics.startTimer();
        int[] buffer = new int[arr.length];
        Metrics.countAllocation();
        mergeSort(arr, 0, arr.length - 1, buffer);
        Metrics.stopTimer();
    }

    private static void mergeSort(int[] arr, int left, int right, int[] buffer) {
        Metrics.enterRecursion();
        if (right - left <= 15) {
            insertionSort(arr, left, right);
            Metrics.exitRecursion();
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, buffer);
        mergeSort(arr, mid + 1, right, buffer);
        merge(arr, left, mid, right, buffer);
        Metrics.exitRecursion();
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        Metrics.countAllocation();
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            Metrics.countComparison();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                Metrics.countComparison();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}