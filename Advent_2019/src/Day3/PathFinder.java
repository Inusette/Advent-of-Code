package Day3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathFinder {

    private static final String INPUT = "../Advent_2019/src/Day3/input.txt";
    private static final String SEPARATOR = ",";


    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> input = readFileToList(INPUT);

        // assemble the instructions for the given wires
        List<List<String>> instructionsForWires = assembleInput(input);

        // make a wire grid object
        Grid wireGrid = new Grid(instructionsForWires);

        // Part 1:
        System.out.println(wireGrid.findClosestIntersectionByManhattanDistance());

        // Part 2:
        System.out.println(wireGrid.findClosestIntersectionBySteps());

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
