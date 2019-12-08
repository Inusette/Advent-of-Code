package Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputReader {


    public static List<String> readFileToList(String fileName) {

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


    public static List<Integer> readFileIntoListOfIntegers(String fileName) {

        List<String> input = readFileToList(fileName);

        return ListAssembler.splitListOfStringsIntoListOfIntegers(input);
    }


    public static List<Integer> readFileIntoListOfIntegers(String fileName, String deliminator) {

        List<String> input = readFileToList(fileName);

        return ListAssembler.splitListOfStringsIntoListOfIntegers(input, deliminator);
    }


    public static List<Character> readFileIntoListOfChars(String fileName) {

        List<String> input = readFileToList(fileName);

        return ListAssembler.splitListOfStringsIntoListOfChars(input);
    }
}
