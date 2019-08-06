import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LineSegment[] segments;

    private static class PointSlope{
        private final Point pt;
        private double slope;

        public PointSlope(Point pt, double slope) {
            this.pt = pt;
            this.slope = slope;
        }
    }

    private boolean ContainsPointSlope(List<PointSlope> lst, PointSlope ps) {
        for (PointSlope it : lst) {
            if (it.pt.compareTo(ps.pt) == 0 && Double.compare(it.slope, ps.slope) == 0) {
                return true;
            }
        }
        return false;
    }

    public FastCollinearPoints(Point[] points) // finds all line segments containing 4 or more points
    {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point pt : points) {
            if (pt == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0)
                throw new IllegalArgumentException();
        }

        List<LineSegment> segs = new ArrayList<LineSegment>();
        ArrayList<PointSlope> pointSlopes2 = new ArrayList<PointSlope>();

        for (int i = 0; i < sortedPoints.length - 3; i++) {
            Point origin = sortedPoints[i];
            Point[] slopePoints = new Point[sortedPoints.length - 1 - i];
            for (int j = i + 1; j < sortedPoints.length; j++) {
                slopePoints[j - (i + 1)] = sortedPoints[j];
            }
            Arrays.sort(slopePoints, origin.slopeOrder());

            int beg = 0;
            int end = 1;
            double slopeVal = origin.slopeTo(slopePoints[beg]);
            while (end < slopePoints.length)
            {
                double curSlopeVal = origin.slopeTo(slopePoints[end]);
                if (slopeVal == curSlopeVal) {
                } else {
                    if (end - beg >= 3) {

                        PointSlope ps = new PointSlope(slopePoints[end - 1], slopeVal);
                        if (!ContainsPointSlope(pointSlopes2, ps)) {
                            pointSlopes2.add(ps);
                            segs.add(new LineSegment(origin, slopePoints[end - 1]));
                        }
                    }
                    beg = end;
                    slopeVal = origin.slopeTo(slopePoints[beg]);
                }
                end++;

            }

            if (end - beg >= 3) {

                PointSlope ps = new PointSlope(slopePoints[end - 1], slopeVal);
                if (!ContainsPointSlope(pointSlopes2, ps)) {
                    pointSlopes2.add(ps);
                    segs.add(new LineSegment(origin, slopePoints[end - 1]));
                }
            }
        }
        segments = segs.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() // the number of line segments
    {
        return segments.length;
    }

    public LineSegment[] segments() // the line segments
    {
        return segments.clone();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            FastCollinearPoints p1 = new FastCollinearPoints(null);
            assert false;
            System.out.println(p1.toString());
        } catch (IllegalArgumentException e) {
//            System.out.println("caught1");
        }
        try {
            Point[] pts = new Point[2];
            FastCollinearPoints p1 = new FastCollinearPoints(pts);
            assert false;
            System.out.println(p1.toString());
        } catch (IllegalArgumentException e) {
//            System.out.println("caught2");
        }
        try {
            Point[] pts = new Point[2];
            pts[0] = new Point(2, 2);
            pts[1] = new Point(2, 2);
            FastCollinearPoints p1 = new FastCollinearPoints(pts);
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

        FastCollinearPoints bcp1 = new FastCollinearPoints(pts1);
        assert bcp1.numberOfSegments() == 3;

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
