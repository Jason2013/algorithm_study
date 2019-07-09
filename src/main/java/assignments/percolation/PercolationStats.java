import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int size;
    private final double[] stats;
    final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        size = n;
        stats = new double[trials];
        for (int t = 0; t < trials; t++) {
            Percolation p = new Percolation(n);

            // randomize indices
            int[] idx = new int[n*n];
            for (int i = 0; i < idx.length; i++) {
                idx[i] = i;
            }
            StdRandom.shuffle(idx);

            int curidx = 0;
            while (!p.percolates())
            {
                int val = idx[curidx++];
                int row = (val / n) + 1;
                int col = (val % n) + 1;
                p.open(row, col);
            }

            stats[t] = (double)curidx / (double)(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(stats) - CONFIDENCE_95 * StdStats.stddev(stats) / size;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(stats) + CONFIDENCE_95 * StdStats.stddev(stats) / size;
    }

    // test client (see below)
    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() +", " + ps.confidenceHi() + "]");
    }
}
