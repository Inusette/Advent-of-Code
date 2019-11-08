package Day5;

// Advent of code day 5

// Nice Strings

// Part 1:

// - It does not contain the strings ab, cd, pq, or xy
// - It contains at least one letter that appears twice in a row  - regex:   "(.)\\1"
// - It contains at least three vowels - regex:  ".*[aeiou].*[aeiou].*[aeiou].*"


// Part 2:

// - It contains a pair of any two letters that appears at least twice in the string
//   without overlapping - regex:   "(..).*\\1"
// - It contains at least one letter which repeats with exactly one letter between them - regex:   "(.).\\1"


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class StringCheckerMain {

    // instantiate the constants
    private static final String[] BAD = new String[] {"ab", "cd", "pq", "xy"};
    private static final String[] GOOD_FIRST = new String[] {"(.)\\1", ".*[aeiou].*[aeiou].*[aeiou].*"};
    private static final String[] GOOD_SECOND = new String[] {"(..).*\\1", "(.).\\1"};
    private static final String INPUT = "/Users/inusea/Documents/Java Stuff/AoC2015/src/Day5/input.txt";


    public static void main(String[] args) {

        // instantiate the count and the list of strings
        int niceStringsCount1;
        int niceStringsCount2;

        // read the file into a list of strings
        List<String> inputList = readFileToList(INPUT);

        // make a nice string checker object
        NiceStringChecker niceChecker1 = new NiceStringChecker(BAD, GOOD_FIRST);
        NiceStringChecker niceChecker2 = new NiceStringChecker(GOOD_SECOND);


        // for each string in the input list, check if it's nice or not
        if (!inputList.isEmpty()) {
            niceStringsCount1 = countNice(inputList, niceChecker1);
            System.out.printf("The number of nice strings in part 1: %d", niceStringsCount1);

            System.out.println();

            niceStringsCount2 = countNice(inputList, niceChecker2);
            System.out.printf("The number of nice strings in part 2: %d", niceStringsCount2);
        }
        else {
            System.out.println("Something went wrong :( ");
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


    private static int countNice(List<String> inputList, NiceStringChecker niceChecker) {

        // instantiate the count
        int niceCount = 0;

        // go over the strings in the given list
        for (String toCheck : inputList) {

            if (niceChecker.checkString(toCheck)) {
                niceCount++;
            }
        }
        return niceCount;
    }
}
