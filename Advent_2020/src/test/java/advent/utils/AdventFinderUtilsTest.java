package advent.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdventFinderUtilsTest {

    @Test
    public void testBinarySearchIntegersSuccessful() {
        List<Integer> orderedList = new ArrayList<>(List.of(2, 6, 7, 9, 14, 17, 20, 33, 9, 123, 145, 156, 165, 166, 177, 199));
        int index = AdventFinderUtils.findElementInList(orderedList, 9);
        Assert.assertEquals(3, index);
    }

    @Test
    public void testBinarySearchIntegersNoMatch() {
        List<Integer> orderedList = new ArrayList<>(List.of(2, 6, 7, 9, 14, 17, 20, 33, 9, 123, 145, 156, 165, 166, 177, 199));
        int index = AdventFinderUtils.findElementInList(orderedList, 99);
        Assert.assertTrue(index < 0);
    }

    @Test
    public void testBinarySearchIntegersUnordered() {
        List<Integer> unorderedList = new ArrayList<>(List.of(2, 87, 6, 95, 7, 14, 17, 20, 33, 5, 123, 145, 156, 30, 165, 166, 177, 9, 199));
        int index = AdventFinderUtils.findElementInList(unorderedList, 9);
        Assert.assertEquals(4, index);
    }

    @Test
    public void testBinarySearchStringsSuccessful() {
        List<String> unorderedList = new ArrayList<>(List.of("a", "v", "i", "j", "t", "m", "o", "b", "e"));
        int index = AdventFinderUtils.findElementInList(unorderedList, "i");
        Assert.assertEquals(3, index);
    }
}
