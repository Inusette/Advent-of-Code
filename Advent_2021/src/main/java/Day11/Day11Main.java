package Day11;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day11Main {

    public static void main(String[] args) {
        List<List<Integer>> inputAsMatrix = getInput();
        inputAsMatrix.forEach(System.out::println);

        OctopusDetonator octopusDetonator = new OctopusDetonator();
        long octopusFlashes = octopusDetonator.detonateOctopusFlashes(inputAsMatrix);
        System.out.println(octopusFlashes);

        long allFlashingStep = octopusDetonator.findAllFlashingStep(inputAsMatrix);
        System.out.println(allFlashingStep);
    }

    protected static List<List<Integer>> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerMatrix(Day11Main.class);
    }
}
