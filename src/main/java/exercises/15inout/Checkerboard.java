/******************************************************************************
 *  Compilation:  javac Checkerboard.java 
 *  Execution:    java Checkerboard n
 *  Dependencies: StdDraw.java
 *
 *  Plots an n-by-n checkerboard.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdDraw;

public class Checkerboard { 

    public static void main(String[] args) { 
        int n = Integer.parseInt(args[0]);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 != 0) StdDraw.setPenColor(StdDraw.BLACK);
                else                  StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
            }
        }
    }

}
