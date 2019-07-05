import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] parent;
    private int count;

    public QuickUnionUF(int n) {
        parent = new int[n];
        count = n;
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        if (connected(p, q)) return;
        parent[p] = q;
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " componnets");
    }
}
