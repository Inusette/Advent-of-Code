package Day05;

// // Full task at https://adventofcode.com/2019/day/5

// using the Intcode class in day 2 as a base for this one!

// Part 1:
// After providing 1 to the only input instruction and passing all the tests,
// what diagnostic code does the program produce?

// Part 2:
// What is the diagnostic code for system ID 5?


import java.util.List;

public class DiagnosticRunner {

    private static final String INPUT = "../Advent_2019/src/Day05/input.txt";
    private static final String SPLIT = ",";


    public static void main(String[] argds) {

        // read the input into a list of integers
        List<Integer> inputIntcode = Utils.InputReader.readFileIntoListOfIntegers(INPUT, SPLIT);

        IntcodeComputer intcoder = new IntcodeComputer(inputIntcode);

        try {
            intcoder.performIntcode();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
