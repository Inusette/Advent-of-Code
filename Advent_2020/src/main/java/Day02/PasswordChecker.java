package Day02;

import advent.utils.AdventFinderUtils;

import java.util.List;

public class PasswordChecker {

    public static int countValidPasswordsA(List<String> inputLines) {

        int validPasswordCount = 0;
        for (String line : inputLines) {
            Password password = parseLine(line);
            List<String> allMatches = AdventFinderUtils.getAllMatches(password.password, String.valueOf(password.element));

            if (allMatches.size() >= password.firstNumber && allMatches.size() <= password.secondNumber) {
                validPasswordCount++;
            }
        }
        return validPasswordCount;
    }

    public static int countValidPasswordsB(List<String> inputLines) {

        int validPasswordCount = 0;
        for (String line : inputLines) {
            Password password = parseLine(line);

            if (password.password.charAt(password.firstNumber - 1) == password.element
                    ^ password.password.charAt(password.secondNumber - 1) == password.element) {
                validPasswordCount++;
            }
        }
        return validPasswordCount;
    }

    private static Password parseLine(String line) {

        Password password = new Password();
        String[] splitLine = line.split(":");
        String[] splitRule = splitLine[0].split(" ");
        String[] splitNumbers = splitRule[0].split("-");

        password.password = splitLine[1].trim();
        password.element = splitRule[1].trim().charAt(0);
        password.firstNumber = Integer.parseInt(splitNumbers[0].trim());
        password.secondNumber = Integer.parseInt(splitNumbers[1].trim());

        return password;
    }

    private static class Password {
        String password;
        char element;
        int firstNumber;
        int secondNumber;
    }
}
