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


    private List<Pattern> assemblePatterns(String[] patternStrings) {

        List<Pattern> patternList = new ArrayList<>();

        // iterate over the strings in the given list and make a pattern object for each
        for (String pat : patternStrings) {

            patternList.add(Pattern.compile(pat));
        }
        return patternList;
    }


    int countNice(List<String> inputStrings) {

        // instantiate the count
        int niceCount = 0;

        // go over the strings in the given list
        for (String toCheck : inputStrings) {

            if (checkString(toCheck)) {
                niceCount++;
            }
        }
        return niceCount;
    }


    private Boolean checkString(String toCheck) {

        // if any of the bad patterns are present - return false
        if (containsOnePattern(toCheck, badPatterns)) {
            return false;
        }

        // if all good patterns are present - return true
        return containsAllPatterns(toCheck, goodPatterns);
    }


    private Boolean containsOnePattern(String toCheck, List<Pattern> badPatterns) {

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


    private Boolean containsAllPatterns(String toCheck, List<Pattern> badPatterns) {

        Boolean contains = true;

        // go over each pattern,
        for (Pattern b : badPatterns) {

            // attempt to find the pattern in the string
            Matcher m = b.matcher(toCheck);

            // if the string comprises any of the patterns, break out of the loop
            if (!m.find()) {
                contains = false;
                break;
            }
        }
        return contains;
    }

}
