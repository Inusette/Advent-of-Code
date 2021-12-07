package Day06;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day06Main {

    public static void main(String[] args) {
        List<Integer> inputAsIntegerList = getInput();

        LanternFishPopulationPredictor populationPredictor = new LanternFishPopulationPredictor();
        long fishPopulationAfter = populationPredictor.predictFishPopulationFrom(inputAsIntegerList, 256);
        System.out.println(fishPopulationAfter);

        fishPopulationAfter = populationPredictor.predictFishPopulationFrom(inputAsIntegerList, 256);
        System.out.println(fishPopulationAfter);
    }

    protected static List<Integer> getInput() {
        return AdventFileUtils.readCommaSplitClassInputIntoIntegerList(Day06Main.class);
    }
}
