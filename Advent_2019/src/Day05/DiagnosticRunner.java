package Day05;

// // Full task at https://adventofcode.com/2019/day/5

// using the Intcode class in day 2 as a base for this one!

// Part 1:
// After providing 1 to the only input instruction and passing all the tests,
// what diagnostic code does the program produce?

// Part 2:
// What is the diagnostic code for system ID 5?


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
