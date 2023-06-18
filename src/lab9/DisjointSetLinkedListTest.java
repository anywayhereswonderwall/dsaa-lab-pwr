import org.junit.Test;

import static org.junit.Assert.*;

public class DisjointSetLinkedListTest {

    @Test
    public void testMakeSet() {
        DisjointSetLinkedList ds = new DisjointSetLinkedList(5);
        assertEquals(0, ds.findSet(0));
        assertEquals(1, ds.findSet(1));
        assertEquals(2, ds.findSet(2));
        assertEquals(3, ds.findSet(3));
        assertEquals(4, ds.findSet(4));
    }

    @Test
    public void testUnion() {
        DisjointSetLinkedList ds = new DisjointSetLinkedList(5);
        assertTrue(ds.union(0, 1));
        assertEquals(1, ds.findSet(0));
        assertEquals(1, ds.findSet(1));
        assertTrue(ds.union(2, 3));
        assertEquals(3, ds.findSet(2));
        assertEquals(3, ds.findSet(3));
        assertTrue(ds.union(0, 3));
        assertEquals(1, ds.findSet(0));
        assertEquals(1, ds.findSet(1));
        assertEquals(1, ds.findSet(2));
        assertEquals(1, ds.findSet(3));
        assertFalse(ds.union(0, 3));
    }

    @Test
    public void testToString() {
        DisjointSetLinkedList ds = new DisjointSetLinkedList(5);
        ds.union(0, 1);
        ds.union(2, 3);
        ds.union(0, 3);
        String expected = "1, 0, 3, 2\n4";
        assertEquals(expected, ds.toString());
    }
}
