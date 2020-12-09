package Day09;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day09Main {

    public static void main(String[] args) {

        List<Long> inputNumbers = getInput();
        System.out.println("This is the input: \n" + inputNumbers);

        long firstUnEncodedNumber = XmasDecoder.findFirstUnEncodedNumber(inputNumbers);
        System.out.println("firstUnEncodedNumber = " + firstUnEncodedNumber);

        long weaknessRangeSum = XmasDecoder.findWeaknessRange(inputNumbers, firstUnEncodedNumber);
        System.out.println("weaknessRangeSum = " + weaknessRangeSum);
    }

    protected static List<Long> getInput() {
        return AdventFileUtils.readClassInputIntoLongLines(Day09Main.class);
    }
}
