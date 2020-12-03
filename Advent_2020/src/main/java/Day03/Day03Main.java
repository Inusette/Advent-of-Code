package Day03;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day03Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        int solutionA = MapTraverser.findTreesOnPath(inputLines);
        System.out.println("SolutionA: " + solutionA);

        long solutionB = MapTraverser.findProbabilityFromAllSlopes(inputLines);
        System.out.println("SolutionB: " + solutionB);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day03Main.class);
    }
}
