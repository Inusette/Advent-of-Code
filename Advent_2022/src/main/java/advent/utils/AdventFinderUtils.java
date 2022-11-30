package advent.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdventFinderUtils {

    private AdventFinderUtils() {
        // you shall not instantiate me
    }

    /**
     * Sorts the list and finds the index of the searched element
     *
     * @return either index or -1 if the element is not found
     */
    public static int findElementInStringList(List<Integer> listToSearch, int key) {
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
    public static <T> int findElementInStringList(List<T extends Comparable<T>> listToSearch, T key) {
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

    /**
     * Checks if all patterns are present in the given string
     */
    public static boolean containsAllPatterns(List<String> patternStrings, String stringToCheck) {

        for (String regex : patternStrings) {
            Matcher matcher = createPatternMatcher(regex, stringToCheck);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks is at least one given pattern is present in the given string
     */
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
     * Finds the matches of the given regex pattern in the given String
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

    /**
     * Counts how many times that pattern occurs within the given string
     */
    public static int countAllMatches(String stringToCheck, String pattern) {
        int matchCount = 0;
        Matcher m = createPatternMatcher(pattern, stringToCheck);
        while (m.find()) {
            matchCount++;
        }
        return matchCount;
    }

    public static Optional<String> getMatch(String stringToCheck, String pattern) {
        Matcher matcher = createPatternMatcher(pattern, stringToCheck);
        if (matcher.find()) {
            return Optional.of(matcher.group());
        }
        return Optional.empty();
    }

    public static String replaceAllFoundPatterns(String toCheck, String pattern, String replacement) {
        Matcher matcher = createPatternMatcher(pattern, toCheck);
        if (matcher.find()) {
            return matcher.replaceAll(replacement);
        }
        return toCheck;
    }

    public static boolean matchesPattern(String toCheck, String pattern) {
        Matcher matcher = createPatternMatcher(pattern, toCheck);
        return matcher.matches();
    }

    public static Optional<String> findSequenceAtEnd(String toCheck, String pattern) {
        pattern = String.format("(%s+)$", pattern);
        Matcher matcher = createPatternMatcher(pattern, toCheck);
        if (matcher.find()) {
            return Optional.of(matcher.group(1));
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

    public static Matcher createPatternMatcher(String regex, String toCheck) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toCheck);
    }
}
