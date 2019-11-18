package Day6;

import java.util.Arrays;
import java.util.List;


class LightGrid {

    private int[][] grid;
    private int lightCount;


    LightGrid(int rows, int columns) {

        this.grid = new int[rows][columns];
        this.lightCount = 0;

        // fill the grid with zeroes
        fillGrid();

    }


    private void fillGrid() {

        // go over the 2d array and fill it with zeroes
        for (int[] row : grid) {
            Arrays.fill(row, 0);
        }
    }


    void followInstructions(List<Instruction> instructions) {

        // go over all the instructions
        for (Instruction instruction : instructions) {

            // begin iterating at the start row, end at the end row
            for (int row = instruction.getStartRow(); row <= instruction.getEndRow(); row++) {

                // begin iterating at the start column, end iterating at the end column
                for (int column = instruction.getStartColumn(); column <= instruction.getEndColumn(); column++) {

                    // perform an action to each current light
                    if (instruction.getAction().equalsIgnoreCase(ActionConstants.TOGGLE)) {
                        toggle(row, column);
                    }
                    else if (instruction.getAction().equalsIgnoreCase(ActionConstants.TURN_ON)) {
                        turnOn(row, column);
                    }
                    else if (instruction.getAction().equalsIgnoreCase(ActionConstants.TURN_OFF)) {
                        turnOff(row, column);
                    }
                }
            }
        }

        // update the light count
        calculateLights();
    }


    private void toggle(int row, int column) {

        // Part 1:
        /*
        if (grid[row][column] == 0) {
            grid[row][column] = 1;
        }
        else {
            grid[row][column] = 0;
        }
        */

        // Part 2:
        grid[row][column] += 2;
    }


    private void turnOn(int row, int column) {

        // Part 1:
        /*
        if (grid[row][column] != 1) {
                grid[row][column] = 1;
            }
        */

        // Part 2:
        grid[row][column] += 1;
    }


    private void turnOff(int row, int column) {

        if (grid[row][column] != 0) {
            // Part 1:
            // grid[row][column] = 0;

            // Part 2:
            grid[row][column] -= 1;
        }
    }


    private void calculateLights() {

        // iterate over all the rows
        for (int[] row : grid) {

            // calculate the sum of each row and update the lightCount
            lightCount += Arrays.stream(row).sum();
        }
    }


    int getLightCount() {
        return lightCount;
    }
}
