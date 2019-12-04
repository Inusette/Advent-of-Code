package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionChecker {

    public static Boolean containsPattern(String patternString, String toCheck) {

        Boolean contains = false;

        // attempt to find the pattern in the string
        Matcher m = makeMatcher(patternString, toCheck);

        // if the string comprises any of the patterns, break out of the loop
        if (m.find()) {
            contains = true;
        }

        return contains;
    }


    public static Boolean containsAllPatterns(List<String> patternStrings, String toCheck) {

        Boolean contains = true;

        // go over each pattern,
        for (String regex : patternStrings) {

            // attempt to find the pattern in the string
            Matcher m = makeMatcher(regex, toCheck);

            // if the string doesn't have one of the patterns, break out of the loop
            if (!m.find()) {
                contains = false;
                break;
            }
        }
        return contains;
    }


    public static Boolean containsAtLeastOnePattern(List<String> patternStrings, String toCheck) {

        Boolean contains = false;

        // go over each pattern,
        for (String regex : patternStrings) {

            // attempt to find the pattern in the string
            Matcher m = makeMatcher(regex, toCheck);

            // if the string comprises any of the patterns, break out of the loop
            if (m.find()) {
                contains = true;
                break;
            }
        }
        return contains;
    }


    public static List<String> getAllMatches(String regex, String toCheck) {

        List<String> matches = new ArrayList<>();

        Matcher m = makeMatcher(regex, toCheck);

        while (m.find()) {
            matches.add(m.group());
        }

        return matches;
    }


    private static Matcher makeMatcher(String regex, String toCheck) {

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toCheck);
    }

}
