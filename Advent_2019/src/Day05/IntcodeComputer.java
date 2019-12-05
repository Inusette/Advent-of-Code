package Day05;

import java.util.*;

class IntcodeComputer {

    private static final String ADD = "1";
    private static final String MULTIPLY = "2";
    private static final String INPUT = "3";
    private static final String OUTPUT = "4";
    private static final String JUMP_IF_TRUE = "5";
    private static final String JUMP_IF_FALSE = "6";
    private static final String LESS_THAN = "7";
    private static final String EQUALS = "8";
    private static final String HALT = "99";


    private List<Integer> intcode;
    private int currentIndex;


    IntcodeComputer(List<Integer> intcode) {

        this.intcode = intcode;
        this.currentIndex = 0;
    }


    void performIntcode() throws Exception {

        while (currentIndex < intcode.size()) {

            String opcode = getOpcode();

            if (opcode.startsWith(ADD)) {

                addInts(opcode);
            }
            else if (opcode.startsWith(MULTIPLY)) {

                multiplyInts(opcode);
            }
            else if (opcode.startsWith(INPUT)) {

                callInput();
            }
            else if (opcode.startsWith(OUTPUT)) {

                printOutput(opcode);
            }
            else if (opcode.startsWith(JUMP_IF_TRUE)) {

                jumpIfTrue(opcode);
            }
            else if (opcode.startsWith(JUMP_IF_FALSE)) {

                jumpIfFalse(opcode);
            }
            else if (opcode.startsWith(LESS_THAN)) {

                storeLessThan(opcode);
            }
            else if (opcode.startsWith(EQUALS)) {

                storeEquals(opcode);
            }
            else if (opcode.startsWith(HALT)) {
                break;
            }
            else {
                throw new Exception("Something went wrong at index " + currentIndex);
            }
        }
    }


    private String getOpcode() {

        // converts the opcode to a string, reverses it.
        // if the opcode is shorter than 5 digits, pads 0's at the end

        StringBuilder opcodeBuilder = new StringBuilder(Integer.toString(intcode.get(currentIndex)));

        opcodeBuilder.reverse();

        if (opcodeBuilder.length() < 5) {
            for (int i = opcodeBuilder.length(); i < 5; i++) {
                opcodeBuilder.append("0");
            }
        }
        return opcodeBuilder.toString();
    }



    private Boolean isPosition(char mode) {
        return mode == '0';
    }


    private void addInts(String opcode) {
        // gets two parameters, following the opcode.
        // Sums them
        // writes the result to the index provided at the third parameter following the opcode

        int[] parameters = getTwoParameters(opcode);

        intcode.set(intcode.get(currentIndex + 3), (parameters[0] + parameters[1]));

        updateCurrentIndex(4);
    }


    private void multiplyInts(String opcode) {
        // gets two parameters, following the opcode.
        // multiplies them
        // writes the result to the index provided at the third parameter following the opcode

        int[] parameters = getTwoParameters(opcode);

        intcode.set(intcode.get(currentIndex + 3), (parameters[0] * parameters[1]));
        updateCurrentIndex(4);
    }


    private void callInput() {
        // calls for the input from the user
        // writes the input to the index provided at the parameter following the opcode

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the ID of the system: ");
        int parameter = input.nextInt();

        intcode.set(intcode.get(currentIndex + 1), parameter);

        updateCurrentIndex(2);
    }


    private void printOutput(String opcode) {
        // prints out the value (or the value of the index) provided at the parameter following the opcode

        System.out.println(getParameter(currentIndex + 1, isPosition(opcode.charAt(2))));
        updateCurrentIndex(2);
    }


    private void jumpIfTrue(String opcode) {
        // if the parameter following the index is NOT a zero, the current index is assigned to the second parameter

        int[] parameters = getTwoParameters(opcode);

        if (parameters[0] != 0) {
            currentIndex = parameters[1];
        }
        else {
            updateCurrentIndex(3);
        }
    }


    private void jumpIfFalse(String opcode) {
        // if the parameter following the index IS a zero, the current index is assigned to the second parameter

        int[] parameters = getTwoParameters(opcode);

        if (parameters[0] == 0) {
            currentIndex = parameters[1];
        }
        else {
            updateCurrentIndex(3);
        }
    }


    private void storeLessThan(String opcode) {
        // gets two parameters, following the opcode.
        // compares them
        // writes 1 or 0 to the index provided at the third parameter following the opcode

        int[] parameters = getTwoParameters(opcode);

        if (parameters[0] < parameters[1]) {
            intcode.set(intcode.get(currentIndex + 3), 1);
        }
        else {
            intcode.set(intcode.get(currentIndex + 3), 0);
        }

        updateCurrentIndex(4);
    }


    private void storeEquals(String opcode) {
        // gets two parameters, following the opcode.
        // compares them
        // writes 1 or 0 to the index provided at the third parameter following the opcode

        int[] parameters = getTwoParameters(opcode);

        if (parameters[0] == parameters[1]) {
            intcode.set(intcode.get(currentIndex + 3), 1);
        }
        else {
            intcode.set(intcode.get(currentIndex + 3), 0);
        }

        updateCurrentIndex(4);
    }


    private void updateCurrentIndex(int jumpOverTo) {
        // updates the current index to point at the index after the required parameters
        currentIndex += jumpOverTo;
    }


    private int[] getTwoParameters(String opcode) {
        // checks the mode of the first and second parameters that follow the opcode
        // extracts them and returns an array

        int[] parameters = new int[2];
        parameters[0] = getParameter(currentIndex + 1, isPosition(opcode.charAt(2)));
        parameters[1] = getParameter(currentIndex + 2, isPosition(opcode.charAt(3)));

        return parameters;
    }


    private int getParameter(int index, Boolean isPosition) {
        // depending on the mode of the parameter
        // returns either the value at the given index
        // or the value at the index provided at the given index

        int parameter;

        if (isPosition) {
            parameter = intcode.get(intcode.get(index));
        }
        else {
            parameter = intcode.get(index);
        }
        return parameter;
    }
}
