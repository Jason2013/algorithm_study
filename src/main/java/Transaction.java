import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {

    private final String who;
    private final Date when;
    private final Double amount;

    public Transaction(String who, Date when, Double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite!");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] a = transaction.split("\\s+");
        this.who = a[0];
        this.when = new Date(a[1]);
        Double amount = Double.parseDouble(a[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite!");
        this.amount = amount;
    }

    public String who() {
        return this.who;
    }

    public Date when() {
        return this.when;
    }

    public Double amount() {
        return this.amount;
    }

    @Override
    public int compareTo(Transaction other) {
        return Double.compare(this.amount, other.amount);
    }

    public static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return Double.compare(v.amount, w.amount);
        }
    }

    public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");

        StdOut.println("Unsorted:");
        for (int i=0; i<a.length; i++) {
            StdOut.println(a[i].who + " " + a[i].when + " " + a[i].amount);
        }
        StdOut.println();

        Arrays.sort(a, Transaction.WhoOrder);
        StdOut.println("Sorted by who:");
        for (int i=0; i<a.length; i++) {
            StdOut.println(a[i].who + " " + a[i].when + " " + a[i].amount);
        }
        StdOut.println();
    }
}
