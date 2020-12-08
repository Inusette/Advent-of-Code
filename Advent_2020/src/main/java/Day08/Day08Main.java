package Day08;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day08Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        AccumulatorComputer computer = new AccumulatorComputer();
        int accumulator = computer.computeUntilFirstLoop(inputLines);
        System.out.println("The value in the accumulator before the infinite loop = " + accumulator);

        int fixedAccumulator = computer.computeUntilLastInstruction(inputLines);
        System.out.println("The value in the accumulator after the fix = " + fixedAccumulator);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day08Main.class);
    }
}
