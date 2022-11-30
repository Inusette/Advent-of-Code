package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import advent.utils.AdventFinderUtils;

public class AdventFinderUtilsTest {

    @Test
    public void testBinarySearchIntegersSuccessful() {
        List<Integer> orderedList = new ArrayList<>(List.of(2, 6, 7, 9, 14, 17, 20, 33, 9, 123, 145, 156, 165, 166, 177, 199));
        int index = AdventFinderUtils.findElementInStringList(orderedList, 9);
        assertEquals(3, index);
    }

    @Test
    public void testBinarySearchIntegersNoMatch() {
        List<Integer> orderedList = new ArrayList<>(List.of(2, 6, 7, 9, 14, 17, 20, 33, 9, 123, 145, 156, 165, 166, 177, 199));
        int index = AdventFinderUtils.findElementInStringList(orderedList, 99);
        assertTrue(index < 0);
    }

    @Test
    public void testBinarySearchIntegersUnordered() {
        List<Integer> unorderedList = new ArrayList<>(List.of(2, 87, 6, 95, 7, 14, 17, 20, 33, 5, 123, 145, 156, 30, 165, 166, 177, 9, 199));
        int index = AdventFinderUtils.findElementInStringList(unorderedList, 9);
        assertEquals(4, index);
    }

    @Test
    public void testBinarySearchStringsSuccessful() {
        List<String> unorderedList = new ArrayList<>(List.of("a", "v", "i", "j", "t", "m", "o", "b", "e"));
        int index = AdventFinderUtils.findElementInStringList(unorderedList, "i");
        assertEquals(3, index);
    }

    @Test
    public void testGetAllMatchesSuccessful() {
        String source = "ho-ho-ho merry f**king Christmas!";
        List<String> allMatches = AdventFinderUtils.getAllMatches(source, "ho");
        assertEquals(3, allMatches.size());

        allMatches = AdventFinderUtils.getAllMatches(source, "h");
        assertEquals(4, allMatches.size());
    }

    @Test
    public void testReplaceAll() {
        String toReplace = "44 66 23 23 | hohhoho";
        String pattern = "\\b23";
        String replacement = "ho";

        String expected = "44 66 ho ho | hohhoho";
        String replaced = AdventFinderUtils.replaceAllFoundPatterns(toReplace, pattern, replacement);
        assertEquals(expected, replaced);
    }
}
