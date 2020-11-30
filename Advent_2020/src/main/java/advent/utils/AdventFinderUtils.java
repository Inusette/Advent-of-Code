package advent.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
