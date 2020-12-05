package Day04;

import advent.utils.AdventFinderUtils;
import advent.utils.AdventMathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassportValidator {

    List<String> initiallyValidPassports;

    List<String> strictlyValidPassports;

    public PassportValidator(List<String> passports) {
        initiallyValidPassports = findValidPassports(passports);
        strictlyValidPassports = findStrictlyValidPassports(initiallyValidPassports);
    }

    public int countInitiallyValidPassports() {
        return initiallyValidPassports.size();
    }

    public int countStrictlyValidPassports() {
        return strictlyValidPassports.size();
    }

    private List<String> findValidPassports(List<String> passports) {
        List<String> validPassports = new ArrayList<>();
        List<String> requiredElements = List.of("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid");
        for (String currentPassport : passports) {
            if (AdventFinderUtils.containsAllPatterns(requiredElements, currentPassport)) {
                validPassports.add(currentPassport);
            }
        }
        return validPassports;
    }

    private List<String> findStrictlyValidPassports(List<String> passports) {

        List<String> strictPasswords = new ArrayList<>();
        for (String passport : passports) {

            if (isBRYValid(passport) && isIYRValid(passport) && isEYRValid(passport) && isHGTValid(passport) &&
            isHCLValid(passport) &&  isECLValid(passport) && isPIDValid(passport)) {
                strictPasswords.add(passport);
            }
        }
        return strictPasswords;
    }

    private boolean isBRYValid(String passport) {
        Optional<String> match = AdventFinderUtils.getMatch(passport, "byr:\\d{4}");
        if (match.isEmpty()){
            return false;
        }
        Optional<String> year = AdventFinderUtils.getMatch(match.get(), "\\d{4}");
        return AdventMathUtils.isWithinRange(Integer.parseInt(year.get()), 1920, 2002);
    }

    private boolean isIYRValid(String passport) {
        Optional<String> match = AdventFinderUtils.getMatch(passport, "iyr:\\d{4}");
        if (match.isEmpty()){
            return false;
        }
        Optional<String> year = AdventFinderUtils.getMatch(match.get(), "\\d{4}");
        return AdventMathUtils.isWithinRange(Integer.parseInt(year.get()), 2010, 2020);
    }

    private boolean isEYRValid(String passport) {
        Optional<String> match = AdventFinderUtils.getMatch(passport, "eyr:\\d{4}");
        if (match.isEmpty()){
            return false;
        }
        Optional<String> year = AdventFinderUtils.getMatch(match.get(), "\\d{4}");
        return AdventMathUtils.isWithinRange(Integer.parseInt(year.get()), 2020, 2030);
    }

    private boolean isHGTValid(String passport) {
        Optional<String> match = AdventFinderUtils.getMatch(passport, "hgt:((\\d{3}cm)|(\\d{2}in))");
        if (match.isEmpty()){
            return false;
        }
        if (match.get().endsWith("in")){
            Optional<String> height = AdventFinderUtils.getMatch(match.get(), "\\d{2}");
            return AdventMathUtils.isWithinRange(Integer.parseInt(height.get()), 59, 76);
        }
        else {
            Optional<String> height = AdventFinderUtils.getMatch(match.get(), "\\d{3}");
            return AdventMathUtils.isWithinRange(Integer.parseInt(height.get()), 150, 193);
        }
    }

    private boolean isHCLValid(String passport) {
        return AdventFinderUtils.containsPattern(passport, "hcl:#([a-f]|\\d){6}");
    }

    private boolean isECLValid(String passport) {
        return AdventFinderUtils.containsPattern(passport, "ecl:(amb|blu|brn|gry|grn|hzl|oth)");
    }

    private boolean isPIDValid(String passport) {
        Optional<String> match = AdventFinderUtils.getMatch(passport, "pid:\\d+");
        if (match.isEmpty())
            return false;
        return match.get().matches("pid:\\d{9}");
    }
}
