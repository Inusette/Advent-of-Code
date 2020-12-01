package Day01;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day01Main {

    public static void main(String[] args) {

        List<Integer> inputAsNumberList = getInput();
        System.out.println("This is the input: \n" + inputAsNumberList);

        int solutionA = ExpenseReportCalculator.calculateA(inputAsNumberList);
        System.out.println("Solution A: " + solutionA);

        int solutionB = ExpenseReportCalculator.calculateB(inputAsNumberList);
        System.out.println("Solution B: " + solutionB);
    }

    protected static List<Integer> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerLines(Day01Main.class);
    }
}
