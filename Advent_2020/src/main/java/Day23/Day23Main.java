package Day23;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day23Main {

    public static void main(String[] args) {

        String numberOfCups = "784235916";

        CupGame cupGame = new CupGame(numberOfCups, 9);
        String result = cupGame.getLabelsStartingWith1(100);
        System.out.println("result = " + result);

        cupGame = new CupGame(numberOfCups, 1000000);
        result = cupGame.playAndMultiply(10000000);
        System.out.println("result = " + result);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readInputChunksSplitByEmptyLines(Day23Main.class);
    }
}
