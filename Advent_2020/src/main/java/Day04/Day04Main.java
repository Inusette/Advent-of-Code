package Day04;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day04Main {

    public static void main(String[] args) {

        List<String> passports = getInput();
        passports.forEach(System.out::println);

        PassportValidator passportValidator = new PassportValidator(passports);
        int validPassportsCount = passportValidator.countInitiallyValidPassports();
        System.out.println("Number od valid passports: " + validPassportsCount);

        int strictlyValidPassports = passportValidator.countStrictlyValidPassports();
        System.out.println("strictlyValidPassports: " + strictlyValidPassports);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readInputChunksSplitByEmptyLines(Day04Main.class);
    }
}
