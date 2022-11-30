package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import advent.utils.AdventFileUtils;

public class AdventFileUtilsTest {

    @Test
    public void testReadClassInputIntoLines() {
        List<String> fileLines = AdventFileUtils.readInputIntoStringLines(AdventFileUtilsTest.class);
        assertEquals(2, fileLines.size());
    }

    @Test
    public void testWriteStringIntoClassOutput() {
        try {
            AdventFileUtils.writeStringIntoClassOutput(AdventFileUtilsTest.class, "HoHoHo!");
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testReadClassInputIntoIntegerLines() {
        List<Integer> fileLines = AdventFileUtils.readInputIntoIntegerLines(AdventFileUtilsTest.class);
        assertEquals(2, fileLines.size());
        assertEquals(123, (int) fileLines.get(0));
    }
}
