import java.util.Arrays;

public class FastCollinearPoints {

    private int segmentCount = 0;
    private LineSegment[] segments;

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

        LineSegment[] segs = new LineSegment[sortedPoints.length];

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
            while (end < slopePoints.length)// ; end++)
            {
                if (slopeVal == origin.slopeTo(slopePoints[end])) {
                } else {
                    if (end - beg >= 3) {
                        //
                        segs[segmentCount++] = new LineSegment(origin, slopePoints[end - 1]);
                    }
                    beg = end;
                    slopeVal = origin.slopeTo(slopePoints[beg]);
                }
                end++;

            }

            if (end - beg >= 3) {
                segs[segmentCount++] = new LineSegment(origin, slopePoints[end - 1]);
            }

        }

        segments = new LineSegment[segmentCount];
        for (int k = 0; k < segments.length; k++) {
            segments[k] = segs[k];
        }

    }

    public int numberOfSegments() // the number of line segments
    {
        return segmentCount;
    }

    public LineSegment[] segments() // the line segments
    {
        return null;
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
    }

}
