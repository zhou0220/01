import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offBy3 = new OffByN(3);
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void testOffby3() {
        assertTrue(offBy3.equalChars('a', 'd'));
        assertFalse(offBy3.equalChars('a', 'b'));
    }

    @Test
    public void testOffBy5() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('a', 'b'));
    }
}
