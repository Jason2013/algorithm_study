import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {

        public Item data;
        public Node<Item> pre;
        public Node<Item> next;

        public Node(Item data) {
            this.data = data;
            this.pre = null;
            this.next = null;
        }
    }

    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int count = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
      return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<Item>(item);
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
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<Item>(item);
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
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret = head.data;
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
        --count;
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret = head.data;
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
        --count;
        return ret;
    }

    private static class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> cur;

        public DequeIterator(Deque<Item> que) {
            cur = que.head;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return cur != null;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if (cur == null) {
                throw new NoSuchElementException();
            }
            Item ret = cur.data;
            cur = cur.next;
            return ret;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(this);
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Deque<String> qs = new Deque<String>();
        qs.addFirst("This");
        qs.addFirst("is");
        qs.addFirst("a");
        qs.addFirst("Test");
        for (String s: qs) {
            System.out.println(s);
        }

    }

}
