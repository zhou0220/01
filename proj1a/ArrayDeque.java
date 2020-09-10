public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_SIZE = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;


    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

        for (int i = 0; i < other.size(); i++) {
            addLast(<T> other.get(i));
        }
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[index];
    }
}
