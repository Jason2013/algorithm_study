
public class FastCollinearPoints {

    private Point[] pts;
    private int segmentCount = 0;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        //
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
    }

    public           int numberOfSegments()        // the number of line segments
    {
        return 0;
    }

    public LineSegment[] segments()                // the line segments
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
    }

}
