import java.util.*;
import Utils.Metrics;

public class DeterministicSelectInput {
    public static int select(int[] arr, int start, int end, int k) {
        Metrics.enterRecursion();
        if (start == end) {
            Metrics.exitRecursion();
            return arr[start];
        }

        int pivot = medianOfMedians(arr, start, end);
        int pivotIndex = partition(arr, start, end, pivot);
        int length = pivotIndex - start + 1;

        Metrics.exitRecursion();
        if (k == length) return arr[pivotIndex];
        else if (k < length) return select(arr, start, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, end, k - length);
    }

    static int partition(int[] arr, int start, int end, int pivot) {
        int pivotIndex = start;
        for (int i = start; i <= end; i++) {
            Metrics.countComparison();
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, end);
        int storeIndex = start;
        for (int i = start; i < end; i++) {
            Metrics.countComparison();
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, end);
        return storeIndex;
    }

    static int medianOfMedians(int[] arr, int start, int end) {
        int n = end - start + 1;
        if (n < 5) {
            Arrays.sort(arr, start, end + 1);
            Metrics.countAllocation();
            return arr[start + n / 2];
        }
        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];
        Metrics.countAllocation();
        for (int i = 0; i < numMedians; i++) {
            int substart = start + i * 5;
            int subend = Math.min(substart + 4, end);
            Arrays.sort(arr, substart, subend + 1);
            Metrics.countComparison();
            medians[i] = arr[substart + (subend - substart) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
