package Day10;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day10Main {

    public static void main(String[] args) {

        List<Integer> inputNumbers = getInput();
        System.out.println("This is the input: \n" + inputNumbers);

        int joltDifferences = AdapterOrganizer.multiplyJoltDifferences(inputNumbers);
        System.out.println("joltDifferences = " + joltDifferences);

    }

    protected static List<Integer> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerLines(Day10Main.class);
    }
}
