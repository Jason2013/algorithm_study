import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

	private class Node<T> {

		public T data;
		public Node<T> pre;
		public Node<T> next;

		public Node(T data) {
			this.data = data;
			this.pre = null;
			this.next = null;
		}
	}

	private Node<T> head = null;
	private Node<T> tail = null;
	private int count = 0;

    // construct an empty deque
	public Deque() {}

    // is the deque empty?
    public boolean isEmpty() {
    	return head == null;
    }

    // return the number of items on the deque
    public int size() {
      return count;
    }

    // add the item to the front
    public void addFirst(T item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}

    	Node<T> newNode = new Node<T>(item);
    	if (head == null) {
    		assert tail == null;
    		head = newNode;
    		tail = head;
    	}
    	else {
    		newNode.next = head;
    		head.pre = newNode;
    		head = newNode;
    	}
    	count++;
    }

    // add the item to the back
    public void addLast(T item) {
    	if (item == null) {
    		throw new IllegalArgumentException();
    	}

    	Node<T> newNode = new Node<T>(item);
    	if (tail == null) {
    		assert head == null;
    		head = newNode;
    		tail = head;
    	}
    	else {
    		newNode.pre = tail;
    		tail.next = newNode;
    		tail = newNode;
    	}
    	count++;
    }

    // remove and return the item from the front
    public T removeFirst() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	T ret = head.data;
    	if (head.next == null) {
    		// last node
    		assert head == tail;
    		assert tail.next == null;
    		head = null;
    		tail = null;
    	}
    	else {
    		
    		head = head.next;
    		head.pre = null;
    	}
        return ret;
    }

    // remove and return the item from the back
    public T removeLast() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	T ret = head.data;
    	if (tail.pre == null) {
    		// last node
    		assert head == tail;
    		assert head.pre == null;
    		head = null;
    		tail = null;
    	}
    	else {
    		tail = tail.pre;
    		tail.next = null;
    	}
        return ret;
    }

    private static class DequeIterator<T> implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
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
