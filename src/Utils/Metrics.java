package Utils;

public class Metrics {
    private static long comparisons;
    private static long allocations;
    private static long recursionDepth;
    private static long maxDepth;
    private static long startTime;
    private static long elapsed;

    public static void reset() {
        comparisons = allocations = recursionDepth = maxDepth = elapsed = 0;
    }

    public static void startTimer() { startTime = System.nanoTime(); }
    public static void stopTimer() { elapsed = System.nanoTime() - startTime; }

    public static void countComparison() { comparisons++; }
    public static void countAllocation() { allocations++; }

    public static void enterRecursion() {
        recursionDepth++;
        maxDepth = Math.max(maxDepth, recursionDepth);
    }
    public static void exitRecursion() { recursionDepth--; }

    public static long getComparisons() { return comparisons; }
    public static long getAllocations() { return allocations; }
    public static long getMaxDepth() { return maxDepth; }
    public static long getElapsedNanos() { return elapsed; }

    public static void report() {
        System.out.println("Time (ms): " + elapsed / 1_000_000.0);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Allocations: " + allocations);
        System.out.println("Max recursion depth: " + maxDepth);
    }
}
