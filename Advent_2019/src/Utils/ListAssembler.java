package Utils;

import java.util.ArrayList;
import java.util.List;

public class ListAssembler {

    public static String joinListOfCharsIntoString(List<Character> toJoin) {

        StringBuilder resultingString = new StringBuilder();

        // go over each char and append t to the string
        for (Character character : toJoin) {

            resultingString.append(character);
        }

        return resultingString.toString();
    }


    public static List<Integer> splitListOfStringsIntoListOfIntegers(List<String> strings, String deliminator) {

        List<Integer> resultList = new ArrayList<>();

        // iterate over lines
        for (String currentString : strings) {

            // split the line using the deliminator and add each number to the list
            for (String position : currentString.split(deliminator)) {

                resultList.add(Integer.parseInt(position));
            }
        }
        return resultList;
    }


    public static List<Integer> splitListOfStringsIntoListOfIntegers(List<String> strings) {

        List<Integer> resultList = new ArrayList<>();

        // iterate over lines
        for (String line : strings) {

            // split the line and add each number to the list
            for (char character: line.toCharArray()) {

                resultList.add(Character.getNumericValue(character));
            }
        }
        return resultList;
    }


    public static List<Character> splitListOfStringsIntoListOfChars(List<String> strings) {

        List<Character> resultList = new ArrayList<>();

        // iterate over lines
        for (String line : strings) {

            // split the line and add each char to the list
            for (char character: line.toCharArray()) {

                resultList.add(character);
            }
        }
        return resultList;
    }
}
