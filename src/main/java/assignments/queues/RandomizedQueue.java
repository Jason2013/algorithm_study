import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private int _size;
    private int _capacity;
    private Item[] items;
    // construct an empty randomized queue
//    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        _size = 0;
        _capacity = INIT_CAPACITY;
        items = (Item[]) new Object[_capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return _size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return _size;
    }

    private void resize(int n) {
        assert n >= _size;
//        @SuppressWarnings("unchecked")
        Item[] newItems = (Item[]) new Object[n];
        for (int i = 0; i < _size; i++) {
            newItems[i] = items[i];
        }
        _capacity = n;
        items = newItems;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (_size == _capacity) {
            resize(_capacity * 2);
        }
        items[_size] = item;
        _size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(_size);
        Item ret = items[idx];
        items[idx] = items[--_size];
        items[_size] = null;

        if (_size < _capacity / 4) {
            resize(_capacity /2);
        }
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(_size)];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final int[] indices;
        private int cur;
        public RandomizedQueueIterator() {
            indices = new int[_size];
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
