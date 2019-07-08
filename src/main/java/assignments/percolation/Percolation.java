import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF qu = null;
    private int[] site = null;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        qu = new WeightedQuickUnionUF(n * n + 2);

        site = new int[n*n];
        for (int i=0; i<site.length; i++)
        {
            site[i] = 0;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {return true;}

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {return false;}

    // returns the number of open sites
    public int numberOfOpenSites() {return 0;}

    // does the system percolate?
    public boolean percolates() {return false;}

    public static void main(String[] args) {
        // StdOut.println("hello, world");
    }
}
