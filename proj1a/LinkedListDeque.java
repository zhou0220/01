public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** creates an empty linked list deque. */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Create a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.prev.next = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.next.prev = node;
        sentinel.prev = node;
        size += 1;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index -= 1;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        return getRecursivehelper(sentinel.next, index);
    }

    public T getRecursivehelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            curr = curr.next;
            return getRecursivehelper(curr, index - 1);
        }
    }
}
