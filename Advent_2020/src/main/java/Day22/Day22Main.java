package Day22;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day22Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        long winnerScore = CombatCardGame.playRegularCombat(inputLines);
        System.out.println("winnerScore = " + winnerScore);

        long recursiveCombatWinnerScore = CombatCardGame.playRecursiveCombat(inputLines);
        System.out.println("recursiveCombatWinnerScore = " + recursiveCombatWinnerScore);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readInputChunksSplitByEmptyLines(Day22Main.class);
    }
}
