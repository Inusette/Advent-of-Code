package Day7;

// Advent of Code, day 7, 2015
// Full task: https://adventofcode.com/2015/day/7

// Part 1:
// what signal is ultimately provided to wire a?

// Part 2:
// Now, take the signal you got on wire a, override wire b to that signal,
// and reset the other wires (including wire a). What new signal is ultimately provided to wire a?

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CircuitEmulator {

    private static final String INPUT = "/Users/inusea/Documents/Java Stuff/AoC2015/src/Day7/input.txt";
    private static final String targetWire = "a";
    private static final String startWire = "b";


    public static void main(String[] args) {

        // read the input file into a list of Strings
        List<String> inputList = readFileToList(INPUT);

        // Go over each instruction string in the input list and attempt to add the instruction object to the list
        HashMap<String,Instruction> instructionsMap = new HashMap<>();

        for (String instructionString : inputList) {

            try {
                Instruction instruction = new Instruction(instructionString);
                instructionsMap.put(instruction.getTarget(), instruction);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        //
        Circuit circuit = new Circuit(instructionsMap);

        int signalA = circuit.findSignal(targetWire);

        System.out.printf("the signal is ultimately provided to targetWire %s is: %d \n", targetWire, signalA);

        Circuit circuit2 = new Circuit(instructionsMap, startWire, signalA);

        int updatedSignalA = circuit2.findSignal(targetWire);

        System.out.printf("Once the signal %s is overridden by signal %s, the circuit is reassembled and the new " +
                "signal of the wire %s is: %d", startWire, targetWire, targetWire, updatedSignalA);

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
