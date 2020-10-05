package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> map;

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        map = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        map.put(item, size() - 1);
        swim(size() - 1);
    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return map.containsKey(item);
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return items.size();
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).getItem();
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T tobeRemoved = getSmallest();
        swap(0, size() - 1);
        items.remove(size() - 1);
        map.remove(tobeRemoved);
        sink(0);
        return tobeRemoved;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (item == null || !contains(item)) {
            throw new NoSuchElementException();
        }
        int index = map.get(item);
        double oldPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (oldPriority < priority) {
            sink(index);
        } else {
            swim(index);
        }
    }

    private void swap(int i, int j) {
        PriorityNode temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
        map.put(items.get(i).getItem(), i);
        map.put(items.get(j).getItem(), j);
    }

    private void sink(int i) {
        int smallest = i;
        if (leftChild(i) <= size() - 1 && smaller(leftChild(i), i)) {
            smallest = leftChild(i);
        }
        if (rightChild(i) <= size() - 1 && smaller(rightChild(i), i)) {
            smallest = rightChild(i);
        }
        if (smallest != i) {
            swap(smallest, i);
            sink(smallest);
        }
    }

    private void swim(int i) {
        while (i > 1 && smaller(i, parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private int parent(int i) {
        if (i == 0) {
            return 0;
        } else {
            return (i - 1) / 2;
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private boolean smaller(int i, int j) {
        return items.get(i).getPriority() < items.get(j).getPriority();
    }
}
