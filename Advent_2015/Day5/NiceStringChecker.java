package Day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class NiceStringChecker {

    // declare the class fields
    private List<Pattern> badPatterns;
    private List<Pattern> goodPatterns;


    NiceStringChecker(String[] badPatterns, String[] goodPatterns) {

        this.badPatterns = assemblePatterns(badPatterns);
        this.goodPatterns = assemblePatterns(goodPatterns);

    }


    NiceStringChecker(String[] goodPatterns) {
        
        this.goodPatterns = assemblePatterns(goodPatterns);
        this.badPatterns = new ArrayList<>();
    }


    private List<Pattern> assemblePatterns(String[] patternStrings) {

        List<Pattern> patternList = new ArrayList<>();

        // iterate over the strings in the given list and make a pattern object for each
        for (String pat : patternStrings) {

            patternList.add(Pattern.compile(pat));
        }
        return patternList;
    }


    Boolean checkString(String toCheck) {

        // if there are bad patterns, check against them
        if (!badPatterns.isEmpty()) {

            // if any of the bad patterns are present - return false
            if (containsOnePattern(toCheck)) {
                return false;
            }
        }

        // if all good patterns are present - return true
        return containsAllPatterns(toCheck);
    }


    private Boolean containsOnePattern(String toCheck) {

        Boolean contains = false;

        // go over each pattern,
        for (Pattern b : badPatterns) {

            // attempt to find the pattern in the string
            Matcher m = b.matcher(toCheck);

            // if the string comprises any of the patterns, break out of the loop
            if (m.find()) {
                contains = true;
                break;
            }
        }
        return contains;
    }


    private Boolean containsAllPatterns(String toCheck) {

        Boolean contains = true;

        // go over each pattern,
        for (Pattern b : goodPatterns) {

            // attempt to find the pattern in the string
            Matcher m = b.matcher(toCheck);

            // if the string doesn't have one of the patterns, break out of the loop
            if (!m.find()) {
                contains = false;
                break;
            }
        }
        return contains;
    }
}
