package Day15;

import advent.utils.AdventFileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day15Main {

    public static void main(String[] args) {
        List<List<Integer>> inputMatrix = getInput();
        System.out.println(inputMatrix);

        ChitonDistanceFinder distanceFinder = new ChitonDistanceFinder(inputMatrix);
        int lowestRiskPath = distanceFinder.calculateLowestRiskPath();
        System.out.println(lowestRiskPath);

        List<List<Integer>> newInputMatrix = increaseInput(inputMatrix);

        ChitonDistanceFinder distanceFinder1 = new ChitonDistanceFinder(newInputMatrix);
        int lowestRiskPath1 = distanceFinder1.calculateLowestRiskPath();
        System.out.println(lowestRiskPath1);

    }

    protected static List<List<Integer>> getInput() {
        return AdventFileUtils.readClassInputIntoIntegerMatrix(Day15Main.class);
    }

    private static List<List<Integer>> increaseInput(List<List<Integer>> inputMatrix) {
        // todo: refactor this ugliness at some point ¯\_(ツ)_/¯
        List<List<Integer>> newMatrix = new ArrayList<>();
        for (List<Integer> row : inputMatrix) {
            List<Integer> newRow = new ArrayList<>(row);
            List<Integer> increaseWith = findIncreaseWith(row);
            newRow.addAll(increaseWith);
            int count = 1;
            while (count < 4) {
                increaseWith = findIncreaseWith(increaseWith);
                newRow.addAll(increaseWith);
                count++;
            }
            newMatrix.add(newRow);
        }

        List<List<Integer>> currentSection = new ArrayList<>(newMatrix);
        int count = 1;
        while (count < 5) {
            List<List<Integer>> temporaryMatrix = new ArrayList<>();
            for (List<Integer> row : currentSection) {
                List<Integer> increaseWith = findIncreaseWith(row);
                temporaryMatrix.add(increaseWith);
                newMatrix.add(increaseWith);
            }
            currentSection = temporaryMatrix;
            count++;
        }
        return newMatrix;
}

    private static List<Integer> findIncreaseWith(List<Integer> row) {
        return row.stream().map(element -> element >= 9 ? 1 : element + 1).collect(Collectors.toList());
    }
}
