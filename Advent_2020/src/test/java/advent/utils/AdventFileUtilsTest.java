package advent.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class AdventFileUtilsTest {

    @Test
    public void testReadClassInputIntoLines() {

        List<String> fileLines = AdventFileUtils.readClassInputIntoLines(AdventFileUtilsTest.class);
        Assert.assertEquals(2, fileLines.size());
    }

    @Test
    public void testWriteStringIntoClassOutput() {
        try {
            AdventFileUtils.writeStringIntoClassOutput(AdventFileUtilsTest.class, "HoHoHo!");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
