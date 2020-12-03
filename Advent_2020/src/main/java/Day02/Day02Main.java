package Day02;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day02Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        int solutionA = PasswordChecker.countValidPasswordsA(inputLines);
        System.out.println("Solution A: " + solutionA);

        int solutionB = PasswordChecker.countValidPasswordsB(inputLines);
        System.out.println("Solution B: " + solutionB);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day02Main.class);
    }
}
