package Day5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NiceStringChecker {

    // instantiate the constants
    private static final String[] BAD = new String[] {"ab", "cd", "pq", "xy"};
    private static final String DOUBLELETTER = "(.)\\1+";
    private static final String VOWELS = "[aeiou]";
    private static final String INPUT = "/Users/inusea/Documents/Java Stuff/AoC2015/src/Day5/input.txt";


    public static void main(String[] args) {

        // instantiate the count and the list of strings
        int niceStringsCount;
        List<String> inputList = readFileToList(INPUT);

        if (!inputList.isEmpty()) {
            niceStringsCount = countNice(inputList);
            System.out.printf("There are number of nice strings is - %d", niceStringsCount);
        }
        else {
            System.out.println("Something went wrong");
        }

    }


    private static List<String> readFileToList(String fileName) {

        // instantiate an empty list of strings
        List<String> fileStrings = new ArrayList<>();

        // attempt to read the file into the list
        try {
            fileStrings = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return fileStrings;
    }


    private static int countNice(List<String> inputStrings) {

        int niceCount = 0;
        Pattern doubleLetter = Pattern.compile(DOUBLELETTER);
        Pattern vowels = Pattern.compile(VOWELS);

        List<Pattern> badPatterns = new ArrayList<>();

        for (String bad : BAD) {

            badPatterns.add(Pattern.compile(bad));
        }

        for (String toCheck : inputStrings) {

            if (checkString(toCheck, badPatterns, doubleLetter, vowels)) {
                niceCount++;
            }
        }
        return niceCount;
    }


    private static Boolean checkString(String toCheck, List<Pattern> badStrings, Pattern doubleLetter, Pattern vowels) {

        if (containsBad(toCheck, badStrings)) {
            return false;
        }

        if (!containsDoubleLetter(toCheck, doubleLetter)) {
            return false;
        }

        return containsThreeVowels(toCheck, vowels);
    }


    private static Boolean containsBad(String toCheck, List<Pattern> badPatterns) {

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


    private static Boolean containsDoubleLetter(String toCheck, Pattern doubleLetter) {

        Boolean contains = false;

        // check if the string contains a double letter
        Matcher doubleMatcher = doubleLetter.matcher(toCheck);

        // if there is a match, assign true
        if (doubleMatcher.find()) {
            contains = true;
        }
        return contains;
    }


    private static Boolean containsThreeVowels(String toCheck, Pattern vowels) {

        Boolean contains = false;

        // check if the string has any vowels
        Matcher vowelMatcher = vowels.matcher(toCheck);

        // the count of vowels
        int count = 0;

        while (vowelMatcher.find()) {

            // increment the count
            count++;

            // if the string has at least 3 vowels, set it to nice, break out of the while loop
            if (count == 3) {
                contains = true;
                break;
            }
        }
        return contains;
    }

}
