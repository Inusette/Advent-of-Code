package Day6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LightSwitcherMain {


    private static final int MAXLIGHTS = 1000;
    private static final String INPUT = "/Users/inusea/Documents/Java Stuff/AoC2015/src/Day6/input.txt";


    public static void main(String[] args) {

        // read the file into a list of strings
        List<String> inputList = readFileToList(INPUT);

        // instantiate the list of instruction objects
        List<Instruction> instructions = new ArrayList<>();

        String[] actions = {ActionConstants.TOGGLE, ActionConstants.TURN_ON, ActionConstants.TURN_OFF};

        // attempt to assemble the list of instructions
        for (String line : inputList) {

            try {
                Instruction instruction = Instruction.buildDefault(line, actions);
                instructions.add(instruction);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // instantiate the lights grid object
        LightGrid lightGrid = new LightGrid(MAXLIGHTS, MAXLIGHTS);

        // perform the instructions given in the input
        lightGrid.followInstructions(instructions);

        // Part 1:
        // System.out.printf("There are currently %d lights on", lightGrid.getLightCount());

        // Part 2:
        System.out.printf("The current brightness of the total grid is: %d", lightGrid.getLightCount());


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
