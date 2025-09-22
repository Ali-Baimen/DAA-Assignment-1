import java.util.*;
import Utils.Metrics;
import Utils.CsvWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        int[] sizes = {100, 1000, 5000, 10000, 20000};

        CsvWriter csv = new CsvWriter("results.csv");
        csv.writeHeader("Algorithm","n","Time(ms)","Comparisons","Allocations","MaxDepth");


        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 100000).toArray();
            Metrics.reset();
            Metrics.startTimer();
            MergeSort.sort(arr);
            Metrics.stopTimer();
            csv.writeRow("MergeSort", n,
                    Metrics.getElapsedNanos() / 1_000_000.0,
                    Metrics.getComparisons(),
                    Metrics.getAllocations(),
                    Metrics.getMaxDepth()
            );
        }


        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 100000).toArray();
            Metrics.reset();
            Metrics.startTimer();
            QuickSort.sort(arr);
            Metrics.stopTimer();
            csv.writeRow("QuickSort", n,
                    Metrics.getElapsedNanos() / 1_000_000.0,
                    Metrics.getComparisons(),
                    Metrics.getAllocations(),
                    Metrics.getMaxDepth()
            );
        }


        for (int n : sizes) {
            int[] arr = rand.ints(n, 0, 100000).toArray();
            int k = n / 2;
            Metrics.reset();
            Metrics.startTimer();
            int result = DeterministicSelectInput.select(arr, 0, arr.length - 1, k);
            Metrics.stopTimer();
            csv.writeRow("Select", n,
                    Metrics.getElapsedNanos() / 1_000_000.0,
                    Metrics.getComparisons(),
                    Metrics.getAllocations(),
                    Metrics.getMaxDepth()
            );
        }


        for (int n : sizes) {
            ClosetPair.Point[] points = new ClosetPair.Point[n];
            for (int i = 0; i < n; i++) {
                double x = rand.nextDouble() * 1000000;
                double y = rand.nextDouble() * 1000000;
                points[i] = new ClosetPair.Point(x, y);
            }
            Metrics.reset();
            Metrics.startTimer();
            double dist = ClosetPair.closestPair(points);
            Metrics.stopTimer();
            csv.writeRow("ClosestPair", n,
                    Metrics.getElapsedNanos() / 1_000_000.0,
                    Metrics.getComparisons(),
                    Metrics.getAllocations(),
                    Metrics.getMaxDepth()
            );
        }

        csv.close();
        System.out.println("Benchmark complete. Results written to results.csv");
    }
}
