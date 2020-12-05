package Day04;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day04Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        List<String> passports = PassportParser.parsePassports(inputLines);
        passports.forEach(System.out::println);

        PassportValidator passportValidator = new PassportValidator(passports);
        int validPassportsCount = passportValidator.countInitiallyValidPassports();
        System.out.println("Number od valid passports: " + validPassportsCount);

        int strictlyValidPassports = passportValidator.countStrictlyValidPassports();
        System.out.println("strictlyValidPassports: " + strictlyValidPassports);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day04Main.class);
    }
}
