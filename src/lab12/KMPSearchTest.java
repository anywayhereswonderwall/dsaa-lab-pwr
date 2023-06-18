import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class KMPSearchTest {

    @Test
    public void testValidShifts() {
        KMP kmp = new KMP();

        // Test 1: Pattern present multiple times in text
        String pattern = "abc";
        String text = "abcabcabc";
        List<Integer> expectedShifts = Arrays.asList(0, 3, 6);
        LinkedList<Integer> shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 2: Pattern not found in text
        pattern = "xyz";
        text = "abcdef";
        expectedShifts = new LinkedList<>();
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 3: Empty pattern
        pattern = "";
        text = "abcdef";
        expectedShifts = new LinkedList<>();
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 4: Empty text
        pattern = "abc";
        text = "";
        expectedShifts = new LinkedList<>();
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 5: Pattern longer than text
        pattern = "abcdef";
        text = "abc";
        expectedShifts = new LinkedList<>();
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 6: Pattern occurring at the beginning, middle, and end of the text
        pattern = "abc";
        text = "abcdefghiabcdefghiabcdefghi";
        expectedShifts = Arrays.asList(0, 9, 18);
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);

        // Test 7: Pattern with repeating characters
        pattern = "aaa";
        text = "aaaaaaaaaaaa";
        expectedShifts = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        shifts = kmp.validShifts(pattern.replaceAll(" ", ""), text.replaceAll(" ", ""));
        Assertions.assertEquals(expectedShifts, shifts);
    }
}
