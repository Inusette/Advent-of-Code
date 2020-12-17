package Day17;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day17Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        ThreeDimensionalCubesActivator threeDimensionalCubesActivator = new ThreeDimensionalCubesActivator(inputLines);
        int allActiveCubes = threeDimensionalCubesActivator.countAllActiveCubes();
        System.out.println("allActiveCubes = " + allActiveCubes);

        FourDimensionalCubesActivator fourDimensionalCubesActivator = new FourDimensionalCubesActivator(inputLines);
        int allActiveCubesIn4Dimensions = fourDimensionalCubesActivator.countAllActiveCubes();
        System.out.println("allActiveCubesIn4Dimensions = " + allActiveCubesIn4Dimensions);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day17Main.class);
    }
}
