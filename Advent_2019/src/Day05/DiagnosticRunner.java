package Day05;


import java.util.ArrayList;
import java.util.List;

public class DiagnosticRunner {

    private static final String INPUT = "../Advent_2019/src/Day05/input.txt";
    private static final String SPLIT = ",";


    public static void main(String[] argds) {

        // read the input into a list of strings
        List<String> input = Utils.InputReader.readFileToList(INPUT);

        // convert the input into a list of integers
        List<Integer> inputIntcode = assembleInput(input);

        IntcodeComputer intcoder = new IntcodeComputer(inputIntcode);

        try {
            intcoder.performIntcode();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static List<Integer> assembleInput(List<String> input) {

        List<Integer> inputNumbers = new ArrayList<>();

        // iterate over lines
        for (String line : input) {

            // split the line and add each number to the list
            for (String position : line.split(SPLIT)) {
                inputNumbers.add(Integer.parseInt(position));
            }
        }
        return inputNumbers;
    }
}
