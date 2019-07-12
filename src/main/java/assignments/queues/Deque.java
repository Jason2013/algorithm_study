
import java.util.Iterator;
//import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    // construct an empty deque
  public Deque() {}

    // is the deque empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the deque
    public int size() {
      return 0;
    }

    // add the item to the front
    public void addFirst(T item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}
    }

    // add the item to the back
    public void addLast(T item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}
    }

    // remove and return the item from the front
    public T removeFirst() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
        return null;
    }

    // remove and return the item from the back
    public T removeLast() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<T> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
    	System.out.println("Hello, world!");
    }

}
