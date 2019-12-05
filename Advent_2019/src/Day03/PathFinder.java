package Day03;

// Part 1:
// What is the Manhattan distance from the central port to the closest intersection?

// Part 2:
// What is the fewest combined steps the wires must take to reach an intersection?

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathFinder {

    private static final String INPUT = "../Advent_2019/src/Day03/input.txt";
    private static final String SEPARATOR = ",";


    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> input = Utils.InputReader.readFileToList(INPUT);

        // assemble the instructions for the given wires
        List<List<String>> instructionsForWires = assembleInput(input);

        // make a wire grid object
        Grid wireGrid = new Grid(instructionsForWires);

        // Part 1:
        System.out.println(wireGrid.findClosestIntersectionByManhattanDistance());

        // Part 2:
        System.out.println(wireGrid.findClosestIntersectionBySteps());

    }


    private static List<List<String>> assembleInput(List<String> input) {

        List<List<String>> inputWires = new ArrayList<>();

        // iterate over lines
        for (String line : input) {

            // split the line by a separator and add the resulted list to the input list
            inputWires.add(Arrays.asList(line.split(SEPARATOR)));

        }
        return inputWires;
    }
}
