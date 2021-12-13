package Day13;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day13Main {

    public static void main(String[] args) {
        List<String> inputLines = getInput();
        int foldingInstructionsStartIndex = inputLines.indexOf("");
        List<String> foldingInstructions = inputLines.subList(foldingInstructionsStartIndex + 1, inputLines.size());
        System.out.println("folding instructions: " + foldingInstructions);
        List<String> dotPositions = inputLines.subList(0, foldingInstructionsStartIndex);
        System.out.println("dot positions: " + dotPositions);

        OrigamiFolder origamiFolder = new OrigamiFolder();
        origamiFolder.foldPaper(dotPositions, foldingInstructions);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day13Main.class);
    }
}
