package Day4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PasswordGenerator {

    private int rangeFrom;
    private int rangeTo;
    private String pattern;


    PasswordGenerator(int rangeFrom, int rangeTo, String pattern){

        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        this.pattern = pattern;
    }


    List<Integer> generateBasicPasswords() {

        List<Integer> passwords = new ArrayList<>();

        for (int password = rangeFrom; password <= rangeTo; password++) {

            // check if the current password contains a required pattern
            if (!hasPattern(password)) {
                continue;
            }

            // check if the numbers in the current password are in ascending order
            if (isAscending(password)) {
                passwords.add(password);
            }
        }
        return passwords;
    }


    List<Integer> generateAdvancedPasswords() {

        List<Integer> passwords = new ArrayList<>();

        for (int password = rangeFrom; password <= rangeTo; password++) {

            // check if the current password contains a required pattern
            if (!hasPattern(password)) {
                continue;
            }

            if (!hasIndependentPattern(password)) {
                continue;
            }

            // check if the numbers in the current password are in ascending order
            if (isAscending(password)) {
                passwords.add(password);
            }
        }
        return passwords;
    }


    private Boolean hasPattern(int password) {
        return Utils.ExpressionChecker.containsPattern(pattern, Integer.toString(password));
    }


    private Boolean isAscending(int password) {

        Boolean isAscending = false;

        int[] sorted =  Integer.toString(password).chars().map(c -> c-'0').toArray();
        Arrays.sort(sorted);

        if (Arrays.equals(Integer.toString(password).chars().map(c -> c-'0').toArray(), sorted)) {
            isAscending = true;
        }
        return isAscending;
    }


    private Boolean hasIndependentPattern(int password) {

        Boolean hasIndependent = false;

        List<String> patternMatches = Utils.ExpressionChecker.getAllMatches(pattern, Integer.toString(password));

        for (String match : patternMatches) {
            if (match.length() == 2) {
                hasIndependent = true;
                break;
            }
        }
        return hasIndependent;
    }
}
