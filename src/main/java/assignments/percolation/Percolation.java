import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final WeightedQuickUnionUF qu;
    private int[] site = null;
    private int openSites = 0;
    private final int vtop;
    private final int vbottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        size = n;

        qu = new WeightedQuickUnionUF(n * n + 2);

        site = new int[n*n];
        for (int i = 0; i < site.length; i++)
        {
            site[i] = 0;
        }

        vtop = n*n;
        vbottom = n*n + 1;

        // connect virtual node
        for (int col = 1; col <= size; col++) {
            qu.union(vtop, index(1, col));
            qu.union(vbottom, index(n, col));
        }
    }

    private void validate(int row, int col) {
        if (!(row >=1 && row <= size && col >= 1 && col <= size)) {
            throw new IllegalArgumentException();
        }
    }

    private int index(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int idx = index(row, col);
        site[idx] = 1;
        openSites++;

        // check up
        if (row > 1 && isOpen(row - 1, col)) {
            int oldIdx = index(row - 1, col);
            qu.union(idx, oldIdx);
        }

        // check down
        if (row < size && isOpen(row + 1, col)) {
            int oldIdx = index(row + 1, col);
            qu.union(idx, oldIdx);
        }

        // check left
        if (col > 1 && isOpen(row, col - 1)) {
            int oldIdx = index(row, col - 1);
            qu.union(idx, oldIdx);
        }

        // check right
        if (col < size && isOpen(row, col + 1)) {
            int oldIdx = index(row, col + 1);
            qu.union(idx, oldIdx);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int idx = index(row, col);
        return site[idx] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (qu.connected(vtop, index(row, col))) {
            return true;
        } else {
            return false;
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {return openSites;}

    // does the system percolate?
    public boolean percolates() {return qu.connected(vtop, vbottom);}

    public static void main(String[] args) {
        // StdOut.println("hello, world");
    }
}
