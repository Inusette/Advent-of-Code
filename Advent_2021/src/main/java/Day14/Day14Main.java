package Day14;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day14Main {

    public static void main(String[] args) {
        List<String> inputLines = getInput();
        int emptyLineIndex = inputLines.indexOf("");
        String initialString = inputLines.subList(0, emptyLineIndex).get(0);
        System.out.println("dot positions: " + initialString);
        List<String> rules = inputLines.subList(emptyLineIndex + 1, inputLines.size());

        PolymerBuilder polymerBuilder = new PolymerBuilder();
        long differenceAfter = polymerBuilder.calculateCommonElementDifferenceAfter(40, initialString, rules);
        System.out.println(differenceAfter);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day14Main.class);
    }
}
