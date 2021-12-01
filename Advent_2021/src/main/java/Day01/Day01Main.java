package Day01;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day01Main {

    public static void main(String[] args) {

        List<Integer> inputAsNumberList = getInput();
        System.out.println("This is the input: \n" + inputAsNumberList);

        int depthIncreases = DepthMeasurer.measureDepthIncreases(inputAsNumberList);
        System.out.println("There number of depth increases is: " + depthIncreases);

        int depthSumsIncreases = DepthMeasurer.measureDepthSumIncreases(inputAsNumberList);
        System.out.println("There number of depth sums increases is: " + depthSumsIncreases);

    }

    protected static List<Integer> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerLines(Day01Main.class);
    }
}
