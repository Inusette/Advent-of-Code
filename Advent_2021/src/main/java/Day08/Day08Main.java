package Day08;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day08Main {

    public static void main(String[] args) {
        List<String> inputAsStringLines = getInput();

        SevenSegmentParser sevenSegmentParser = new SevenSegmentParser(inputAsStringLines);
        int uniqueValueCount = sevenSegmentParser.countUniqueValuesInOutput();
        System.out.println("The number of times that digits 1, 4, 7, or 8 appear in output is: " + uniqueValueCount);

        long outputValuesSum = sevenSegmentParser.sumOutput();
        System.out.println("Adding up all of the output values results in: " + outputValuesSum);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day08Main.class);
    }
}
