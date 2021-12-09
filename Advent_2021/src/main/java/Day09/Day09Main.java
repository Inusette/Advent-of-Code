package Day09;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day09Main {

    public static void main(String[] args) {
        List<List<Integer>> inputAsMatrix = getInput();

        BasinFinder basinFinder = new BasinFinder(inputAsMatrix);
        System.out.println("The sum of all bottoms is: " + basinFinder.sumUpBasinBottoms());
        System.out.println("Multiplied, the three largest basin sizes are: " + basinFinder.multiplyLargestBasins());
    }

    protected static List<List<Integer>> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerMatrix(Day09Main.class);
    }
}
