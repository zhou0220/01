package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author zhou
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer<Double> x = new ArrayRingBuffer<>(4);
        assertTrue(x.isEmpty());
        x.enqueue(33.1);
        x.enqueue(44.8);
        x.enqueue(62.3);
        x.enqueue(-3.4);
        assertTrue(x.isFull());
        assertEquals(33.1, x.dequeue(), 0.01);
        assertEquals(44.8, x.peek(), 0.01);
    }
}
