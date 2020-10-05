package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 1);
        heap.add(2, 2);
        heap.add(3, 3);
        heap.add(4, 4);
        heap.add(5, 2);
        heap.add(6, 3);
        assertEquals(heap.size(), 6);
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 1);
        heap.add(2, 2);
        heap.add(3, 3);
        heap.add(4, 4);
        heap.add(5, 2);
        heap.add(6, 3);
        assertTrue(heap.contains(4));
        assertFalse(heap.contains(7));
    }

    @Test
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 1);
        heap.add(2, 2);
        heap.add(3, 3);
        heap.add(4, 4);
        heap.add(5, 2);
        heap.add(6, 3);
        assertTrue(heap.getSmallest() == 1);

    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 1);
        heap.add(2, 2);
        heap.add(3, 3);
        heap.add(4, 4);
        heap.add(5, 2);
        heap.add(6, 3);
        assertTrue(heap.removeSmallest() == 1);
        assertTrue(heap.getSmallest() == 2);
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 1);
        heap.add(2, 2);
        heap.add(3, 3);
        heap.add(4, 4);
        heap.add(5, 2);
        heap.add(6, 3);
        heap.changePriority(5, 5);
        heap.changePriority(6, 0);
        assertTrue(heap.getSmallest() == 6);
    }

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            heap.add(i, 50000 - i);
        }

        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1) / 1000.0 + " seconds.");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            heap.changePriority(i, i + 1);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end3 - start3) / 1000.0 + "seconds.");

        long start2 = System.currentTimeMillis();
        NaiveMinPQ<Integer> heap1 = new NaiveMinPQ<Integer>();
        for (int i = 0; i < 100000; i++) {
            heap1.add(i, 50000 - i);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 + " seconds.");
    }
}
