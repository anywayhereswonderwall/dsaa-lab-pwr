import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BSTTest {

    private BST<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BST<>();
    }

    @Test
    public void testAddAndContains() {
        bst.add(5);
        bst.add(3);
        bst.add(7);

        Assertions.assertTrue(bst.getElement(5) == 5);
        Assertions.assertTrue(bst.getElement(3) == 3);
        Assertions.assertTrue(bst.getElement(7) == 7);
        Assertions.assertTrue(bst.getElement(2) == null);
    }

    @Test
    public void testAddDuplicate() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(3);

        Assertions.assertTrue(bst.getElement(5) == 5);
        Assertions.assertTrue(bst.getElement(3) == 3);
        Assertions.assertTrue(bst.getElement(7) == 7);
        Assertions.assertTrue(bst.getElement(2) == null);
    }

    @Test
    public void testSuccessor() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);
        bst.add(6);
        bst.add(9);

        Assertions.assertTrue(bst.successor(5) == 6);
        Assertions.assertTrue(bst.successor(1) == 3);
        Assertions.assertTrue(bst.successor(4) == 5);
        Assertions.assertTrue(bst.successor(9) == null);
    }

    @Test
    public void testToString() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);
        bst.add(6);
        bst.add(9);

        Assertions.assertEquals("1, 3, 4, 5, 6, 7, 9", bst.toStringInOrder());
        Assertions.assertEquals("5, 3, 1, 4, 7, 6, 9", bst.toStringPreOrder());
        Assertions.assertEquals("1, 4, 3, 6, 9, 7, 5", bst.toStringPostOrder());
    }

    @Test
    public void testRemove() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);
        bst.add(6);
        bst.add(9);

        bst.remove(5);
        bst.remove(3);
        bst.remove(7);

        Assertions.assertNull(bst.getElement(5));
        Assertions.assertNull(bst.getElement(3));
        Assertions.assertNull(bst.getElement(7));
        Assertions.assertTrue(bst.getElement(1) == 1);
        Assertions.assertTrue(bst.getElement(4) == 4);
        Assertions.assertTrue(bst.getElement(6) == 6);
        Assertions.assertTrue(bst.getElement(9) == 9);
    }

    @Test
    public void testEmptyBST() {
        Assertions.assertNull(bst.getElement(5));
        Assertions.assertTrue(bst.successor(5) == null);
        Assertions.assertEquals("", bst.toStringInOrder());
        Assertions.assertEquals("", bst.toStringPreOrder());
        Assertions.assertEquals("", bst.toStringPostOrder());
        Assertions.assertNull(bst.remove(5));
    }
    @Test
    public void testNodesWithTwoChildrenCount() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);
        bst.add(6);
        bst.add(9);

        Assertions.assertEquals(3, bst.countNodesWithTwoChildren());

        bst = new BST<>();

        bst.add(10);
        bst.add(8);
        bst.add(15);
        bst.add(5);
        bst.add(1);
        bst.add(6);
        bst.add(25);
        bst.add(11);
        bst.add(20);
        bst.add(22);

        Assertions.assertEquals(3, bst.countNodesWithTwoChildren());
    }
}
