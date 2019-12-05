package Day02;

// Part 1:
// before running the program, replace position 1 with the value 12 and replace position 2 with the value 2.
// What value is left at position 0 after the program halts?

// Part 2:
// Find the input noun and verb that cause the program to produce the output 19690720.
// What is 100 * noun + verb? (For example, if noun=12 and verb=2, the answer would be 1202.)

import java.util.ArrayList;
import java.util.List;

public class AlarmSetter {


    private static final String INPUT = "../Advent_2019/src/Day02/input.txt";
    private static final String SPLIT = ",";
    private static final int POS_1 = 12;
    private static final int POS_2 = 2;

    private static final int MAX_POS = 99;
    private static final int OUTPUT = 19690720;
    private static final int PRE_OUTPUT = 19690000;


    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> input = Utils.InputReader.readFileToList(INPUT);

        // convert the input into a list of integers
        List<Integer> inputIntcode = assembleInput(input);

        // Part 1:
        // make an intcode with the given positions
        Intcoder intcoder = makeIntcoder(inputIntcode, POS_1, POS_2);
        System.out.println(intcoder.getPosition0());

        // Part 2:
        // TODO: FIX THIS SH*T :D
        // find the positions for the incode with the given result

        //System.out.println(findPositions(inputIntcode));

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


    private static int findPositions(List<Integer> inputNumbers) {

        int pos1 = POS_1;
        int pos2 = POS_2;

        while (pos1 <= MAX_POS) {

            pos1 ++;

            Intcoder intcoder = makeIntcoder(inputNumbers, pos1, pos2);

            if (intcoder.getPosition0() > PRE_OUTPUT) {
                break;
            }
        }

        while (pos2 <= MAX_POS) {

            pos2 ++;

            Intcoder intcoder = makeIntcoder(inputNumbers, pos1, pos2);

            if (intcoder.getPosition0() == OUTPUT) {
                break;
            }
        }
        return (pos1 * 100 + pos2);
    }


    private static Intcoder makeIntcoder(List<Integer> inputNumbers, int pos1, int pos2) {

        Intcoder intcoder = new Intcoder(inputNumbers, pos1, pos2);

        try {
            intcoder.performIntcode();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return intcoder;
    }
}
