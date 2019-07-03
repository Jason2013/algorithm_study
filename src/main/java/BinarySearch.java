import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class BinarySearch {
    private BinarySearch() {}
    private static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        while (StdIn.hasNext()) {
            idx = BinarySearch.indexOf(whitelist, n);
            if (idx == -1) {
                StdOut.println(key);
            }
        }
    }
}
