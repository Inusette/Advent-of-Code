package advent.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdventFinderUtils {

    /**
     * Sorts the list and finds the index of the searched element
     *
     * @return either index or -1 if the element is not found
     */
    public static int findElementInList(List<Integer> listToSearch, int key) {

        Collections.sort(listToSearch);
        int foundAtIndex = Arrays.binarySearch(listToSearch.toArray(), key);
        validateIndex(foundAtIndex);
        return foundAtIndex;
    }

    /**
     * Sorts the list and finds the index of the searched element
     *
     * @return either index or -1 if the element is not found
     */
    public static int findElementInList(List<String> listToSearch, String key) {

        Collections.sort(listToSearch);
        int foundAtIndex = Arrays.binarySearch(listToSearch.toArray(), key);
        validateIndex(foundAtIndex);
        return foundAtIndex;
    }

    /**
     * Checks whether the given String contains the given regex pattern
     *
     * @return true if yes :)
     */
    public static boolean containsPattern(String stringToCheck, String pattern) {
        Matcher matcher = createPatternMatcher(pattern, stringToCheck);
        return matcher.find();
    }

    public static boolean containsAllPatterns(List<String> patternStrings, String stringToCheck) {

        for (String regex : patternStrings) {
            Matcher matcher = createPatternMatcher(regex, stringToCheck);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsAtLeastOnePattern(List<String> patternStrings, String toCheck) {
        for (String regex : patternStrings) {
            Matcher matcher = createPatternMatcher(regex, toCheck);
            if (matcher.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the matches of the given regex patter in the given String
     *
     * @return An empty list if no matches were found
     */
    public static List<String> getAllMatches(String stringToCheck, String pattern) {

        List<String> matches = new ArrayList<>();
        Matcher m = createPatternMatcher(pattern, stringToCheck);
        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }

    public static Optional<String> getMatch(String stringToCheck, String pattern) {
        Matcher matcher = createPatternMatcher(pattern, stringToCheck);
        if (matcher.find()) {
            return Optional.of(matcher.group());
        }
        return Optional.empty();
    }

    private static void validateIndex(int foundAtIndex) {
        if (foundAtIndex < 0) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index " + foundAtIndex);
        }
    }

    private static Matcher createPatternMatcher(String regex, String toCheck) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toCheck);
    }
}
