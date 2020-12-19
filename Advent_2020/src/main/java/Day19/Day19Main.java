package Day19;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day19Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);


        ValidMessageIdentifier validMessageIdentifier = new ValidMessageIdentifier(inputLines);
        int ruleMatches = validMessageIdentifier.solutionA();
        System.out.println("ruleMatches = " + ruleMatches);

        int solutionB = validMessageIdentifier.solutionB();
        System.out.println("solutionB = " + solutionB);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day19Main.class);
    }
}
