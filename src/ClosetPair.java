import java.util.*;

public class ClosetPair {
    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

    private static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    private static double closestUtil(Point[] ptsX, Point[] ptsY, int left, int right) {
        if (right - left <= 3) return bruteForce(ptsX, left, right);
        int mid = (left + right) / 2;
        Point midPoint = ptsX[mid];
        List<Point> leftY = new ArrayList<>(), rightY = new ArrayList<>();
        for (Point p : ptsY) {
            if (p.x <= midPoint.x) leftY.add(p); else rightY.add(p);
        }
        double dl = closestUtil(ptsX, leftY.toArray(new Point[0]), left, mid);
        double dr = closestUtil(ptsX, rightY.toArray(new Point[0]), mid + 1, right);
        double d = Math.min(dl, dr);
        List<Point> strip = new ArrayList<>();
        for (Point p : ptsY) {
            if (Math.abs(p.x - midPoint.x) < d) strip.add(p);
        }
        return Math.min(d, stripClosest(strip, d));
    }

    public static double closestPair(Point[] points) {
        Point[] ptsX = points.clone();
        Arrays.sort(ptsX, Comparator.comparingDouble(p -> p.x));
        Point[] ptsY = points.clone();
        Arrays.sort(ptsY, Comparator.comparingDouble(p -> p.y));
        return closestUtil(ptsX, ptsY, 0, ptsX.length - 1);
    }
}
