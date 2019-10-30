package Day5;

// Advent of code day 5
// Nice String:
// - It does not contain the strings ab, cd, pq, or xy
// - It contains at least one letter that appears twice in a row  - regex:   "(.)\\1"
// - It contains at least three vowels - regex:  ".*[aeiou].*[aeiou].*[aeiou].*"

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StringCheckerMain {

    // instantiate the constants
    private static final String[] BAD = new String[] {"ab", "cd", "pq", "xy"};
    private static final String[] GOOD = new String[] {"(.)\\1", ".*[aeiou].*[aeiou].*[aeiou].*"};
    private static final String INPUT = "/Users/inusea/Documents/Java Stuff/AoC2015/src/Day5/input.txt";


    public static void main(String[] args) {

        // instantiate the count and the list of strings
        int niceStringsCount;

        // read the file into a list of strings
        List<String> inputList = readFileToList(INPUT);

        // make a nice string checker object
        NiceStringChecker niceChecker = new NiceStringChecker(BAD, GOOD);

        // for each string in the input list, check if it's nice or not
        if (!inputList.isEmpty()) {
            niceStringsCount = niceChecker.countNice(inputList);
            System.out.printf("There are number of nice strings is - %d", niceStringsCount);
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
}
