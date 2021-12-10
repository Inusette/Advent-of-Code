package Day10;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day10Main {

    public static void main(String[] args) {
        List<String> inputAsStringLines = getInput();
        SyntaxErrorIdentifier syntaxErrorIdentifier = new SyntaxErrorIdentifier();
        System.out.println(syntaxErrorIdentifier.calculateCorruptedSyntaxScore(inputAsStringLines));

        long middleAutocompletionScore = syntaxErrorIdentifier.calculateAutocompletionScores();
        System.out.println(middleAutocompletionScore);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day10Main.class);
    }
}
