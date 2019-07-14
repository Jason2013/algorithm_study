import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private int theSize;
    private int theCapacity;
    private Item[] items;
    // construct an empty randomized queue
//    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        theSize = 0;
        theCapacity = INIT_CAPACITY;
        items = (Item[]) new Object[theCapacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return theSize == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return theSize;
    }

    private void resize(int n) {
        assert n >= theSize;
//        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Object[n];
        for (int i = 0; i < theSize; i++) {
            newItems[i] = items[i];
        }
        theCapacity = n;
        items = newItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (theSize == theCapacity) {
            resize(theCapacity * 2);
        }
        items[theSize] = item;
        theSize++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(theSize);
        Item ret = items[idx];
        items[idx] = items[--theSize];
        items[theSize] = null;

        if (theSize < theCapacity / 4) {
            resize(theCapacity /2);
        }
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(theSize)];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final int[] indices;
        private int cur;
        public RandomizedQueueIterator() {
            indices = new int[theSize];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
            cur = 0;
        }
        @Override
        public boolean hasNext() {
            return cur < indices.length;
            // TODO Auto-generated method stub
        }

        @Override
        public Item next() {
            if (cur == indices.length) {
                throw new NoSuchElementException();
            }
            Item ret = items[indices[cur++]];
            return ret;
            // TODO Auto-generated method stub
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(Integer.toString(i));
        }

        for (String s: rq) {
            System.out.println(s);
        }

    }

}
