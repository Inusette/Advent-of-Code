package Day14;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day14Main {

    public static void main(String[] args) {

        List<List<String>> inputChunks = getInput();
        inputChunks.forEach(System.out::println);

        BitMaskConverter bitMaskConverter = new BitMaskConverter();
        long sumOfConvertedMemoryValues = bitMaskConverter.calculateSumOfConvertedMemoryValues(inputChunks);
        System.out.println("sumOfConvertedMemoryValues = " + sumOfConvertedMemoryValues);

        long sumOfMemoryValuesWithFloating = bitMaskConverter.calculateSumOfMemoryValuesWithFloating(inputChunks);
    }

    protected static List<List<String>> getInput() {
        return AdventFileUtils.readInputChunksSplitByPattern(Day14Main.class, "mask");
    }
}
