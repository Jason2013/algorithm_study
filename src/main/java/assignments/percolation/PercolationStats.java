import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double stats[];

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        stats = new double[trials];
        for (int t=0; t<trials; t++) {
            Percolation p = new Percolation(n);

            // randomize indices
            int idx[] = new int[n*n];
            for (int i=0; i<idx.length; i++) {
                idx[i] = i;
            }
            StdRandom.shuffle(idx);

            int curidx = 0;
            while (!p.percolates())
            {
                int val = idx[curidx++];
                int row = val / n;
                int col = val % n;
                p.open(row, col);
            }

            stats[t] = (double)curidx / (double) (n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (see below)
    // public static void main(String[] args)

    public static void main(String[] args) {

        Percolation p = new Percolation(2);
        int[] ns = new int[10];
        for (int i=0; i<ns.length; i++) {
            ns[i] = i;
        }
        StdRandom.shuffle(ns);
        for (int i=0; i<ns.length; i++) {
            System.out.print(ns[i] + ", ");
        }
        System.out.println("hello, world + " + (7/3) + "," + (7%3));
    }
}
