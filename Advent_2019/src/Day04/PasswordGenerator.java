package Day04;


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

            // check for the basic conditions of a good password
            if (meetsBasicConditions(password)) {
                passwords.add(password);
            }
        }
        return passwords;
    }


    List<Integer> generateAdvancedPasswords() {

        List<Integer> passwords = new ArrayList<>();

        for (int password = rangeFrom; password <= rangeTo; password++) {

            // check for the basic conditions of a good password
            // check for the required advanced pattern
            if (meetsBasicConditions(password) && hasIndependentPattern(password)) {
                passwords.add(password);
            }
        }
        return passwords;
    }


    private Boolean meetsBasicConditions(int password) {

        // check if the current password contains a required pattern
        // check if the numbers in the current password are in ascending order
        return hasPattern(password) && isAscending(password);
    }


    private Boolean hasPattern(int password) {
        return Utils.ExpressionChecker.containsPattern(pattern, Integer.toString(password));
    }


    private Boolean isAscending(int password) {

        Boolean isAscending = false;

        // convert the number into an array, sort it, then compare to the unsorted
        int[] sorted =  Integer.toString(password).chars().map(c -> c-'0').toArray();
        Arrays.sort(sorted);

        if (Arrays.equals(Integer.toString(password).chars().map(c -> c-'0').toArray(), sorted)) {
            isAscending = true;
        }
        return isAscending;
    }


    private Boolean hasIndependentPattern(int password) {

        Boolean hasIndependent = false;

        // find all the matches for the pattern
        List<String> patternMatches = Utils.ExpressionChecker.getAllMatches(pattern, Integer.toString(password));

        // go over the found matches and check if there is a match of a length 2
        for (String match : patternMatches) {

            if (match.length() == 2) {
                hasIndependent = true;
                break;
            }
        }
        return hasIndependent;
    }
}
