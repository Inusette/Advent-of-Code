package Day18;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day18Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        long lineSum = HomeworkCalculator.evaluateAllLines(inputLines);
        System.out.println("lineSum = " + lineSum);

        long sumFirst = HomeworkCalculator.evaluateLinesSumFirst(inputLines);
        System.out.println("sumFirst = " + sumFirst);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day18Main.class);
    }
}
