import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private final int initCapacity = 8;
	private int _size;
	private int _capacity;
	private Item[] items;
    // construct an empty randomized queue
    public RandomizedQueue() {
    	_size = 0;
    	_capacity = initCapacity;
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
    	Item[] newItems = (Item[]) new Object[n];
    	for (int i = 0; i < _size; i++) {
    		newItems[i] = items[i];
    	}
    	_capacity = n;
    	items = newItems;
    }

    // add the item
    public void enqueue(Item item) {
    	if (_size == _capacity) {
    		resize(_capacity * 2);
    	}
    	items[_size] = item;
    	_size++;
    }

    // remove and return a random item
    public Item dequeue() {
    	int idx = StdRandom.uniform(_size);
    	Item ret = items[idx];
    	items[idx] = items[--_size];

    	if (_size < _capacity / 4) {
    		resize(_capacity /2);
    	}
    	return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	return items[StdRandom.uniform(_size)];
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

    	private int[] indices;
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
			return cur < indices.length - 1;
			// TODO Auto-generated method stub
		}

		@Override
		public Item next() {
			Item ret = (Item) items[indices[cur++]];
			return ret;
			// TODO Auto-generated method stub
		}
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    	return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
    	System.out.println("Hello, world!");
    }

}
