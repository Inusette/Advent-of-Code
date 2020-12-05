package Day05;

import advent.utils.AdventFileUtils;
import java.util.List;

public class Day05Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        int maxID = BoardingPassProcessor.findMaxBoardingID(inputLines);
        System.out.println("Solution A: " + maxID);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day05Main.class);
    }
}
