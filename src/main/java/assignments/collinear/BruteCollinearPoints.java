import edu.princeton.cs.algs4.Quick;

public class BruteCollinearPoints {

    private Point[] pts;
    private int segmentCount = 0;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point pt : points) {
            if (pt == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        pts = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pts[i] = points[i];
        }

        Quick.sort(pts);

        LineSegment[] segs = new LineSegment[pts.length];
        for (int p = 0; p < pts.length - 3; p++) {
            for (int q = p + 1; q < pts.length - 2; q++) {
                for (int r = q + 1; r < pts.length - 1; r++) {
                    for (int s = r + 1; s < pts.length; s++) {
                        double slope = pts[p].slopeTo(pts[q]);
                        if (pts[p].slopeTo(pts[r]) == slope && pts[p].slopeTo(pts[s]) == slope) {
                            segs[segmentCount++] = new LineSegment(pts[p], pts[s]);
                        }
                    }
                }
            }
        }

        if (segmentCount > 0) {
            segments = new LineSegment[segmentCount];
            for (int i = 0; i < segmentCount; i++) {
                segments[i] = segs[i];
            }
        }
    }

    public int numberOfSegments() { // the number of line segments
        return segmentCount;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            BruteCollinearPoints p1 = new BruteCollinearPoints(null);
            assert false;
            System.out.println(p1.toString());
        } catch (IllegalArgumentException e) {
//            System.out.println("caught1");
        }
        try {
            Point[] pts = new Point[2];
            BruteCollinearPoints p1 = new BruteCollinearPoints(pts);
            assert false;
            System.out.println(p1.toString());
        } catch (IllegalArgumentException e) {
//            System.out.println("caught2");
        }
        try {
            Point[] pts = new Point[2];
            pts[0] = new Point(2, 2);
            pts[1] = new Point(2, 2);
            BruteCollinearPoints p1 = new BruteCollinearPoints(pts);
            assert false;
            System.out.println(p1.toString());
        } catch (IllegalArgumentException e) {
//            System.out.println("caught3");
        }

        Point[] pts1 = new Point[9];

        pts1[0] = new Point(1, 1);
        pts1[1] = new Point(2, 1);
        pts1[2] = new Point(3, 1);
        pts1[3] = new Point(4, 1);
        pts1[4] = new Point(4, 2);
        pts1[5] = new Point(4, 3);
        pts1[6] = new Point(4, 4);
        pts1[7] = new Point(3, 3);
        pts1[8] = new Point(2, 2);

        BruteCollinearPoints bcp1 = new BruteCollinearPoints(pts1);
        assert bcp1.numberOfSegments() == 3;
    }

}
