package Day7;

import java.util.HashMap;


class Circuit {

    private static final String DIGIT_PATTERN = "\\d+";

    private HashMap<String, Integer> wires;
    private HashMap<String, Instruction> instructions;


    Circuit(HashMap<String, Instruction> instructions){

        this.wires = new HashMap<>();
        this.instructions = instructions;
    }


    Circuit(HashMap<String, Instruction> instructions, String startWire, int startSignal){

        this.wires = new HashMap<>();
        this.instructions = instructions;

        this.wires.put(startWire, startSignal);
    }


    int findSignal(String wire) {

        // check if the wire is a digit (e.g. after LSHIFT operation)
        if (isDigit(wire)) {
            return Integer.parseInt(wire);
        }

        // if the signal is known assign it to the variable
        else if (wires.containsKey(wire)) {
            return wires.get(wire);
        }

        // find the instructions for the signal
        else {

            // if there is a rule for this wire
            if (instructions.containsKey(wire)) {

                Instruction instruction = instructions.get(wire);

                // if the rule has only one component
                if (instructions.get(wire).hasMultipleComponents()) {

                    // find both components
                    int signalA = findSignal(instruction.getWireA());
                    int signalB = findSignal(instruction.getWireB());

                    // do the operation in the rule and add to the known wires
                    wires.put(wire, doOperation(instruction.getGate(), signalA, signalB));

                    return wires.get(wire);
                }

                // if the rule has 2 components
                else {

                    // handle the rule with just one component
                    int signal = handleSingleComponent(instruction);

                    // add it to the known wires
                    wires.put(wire, signal);

                    return wires.get(wire);
                }
            }
        }

        // if nothing is found, return a negative
        return -1;
    }


    private int handleSingleComponent(Instruction instruction) {

        // if the rule has an operation on another wire's signal (e.g. NOT a)
        if (instruction.hasGate()) {

            // recursively call the find method to find that wire's rule
            int signal = findSignal(instruction.getValue());

            return calculateComplement(signal);

        }
        // if the value is a number
        else if (isDigit(instruction.getValue())) {

            return Integer.parseInt(instruction.getValue());
        }

        // if the rule is another wire's signal
        else {

            // recursively call the find method to find that wire's rule
            return findSignal(instruction.getValue());
        }
    }


    private int doOperation(String gate, int signalA, int signalB) {

        int result = 0;

        // depending on the gate, do the appropriate operation
        switch (gate) {

            case CircuitConstants.AND :
                result = signalA & signalB;
                break;

            case CircuitConstants.OR :
                result = signalA | signalB;
                break;

            case CircuitConstants.L_SHIFT :
                result = signalA << signalB;
                break;

            case CircuitConstants.R_SHIFT :
                result = signalA >> signalB;
                break;
        }

        return result;
    }



    private int calculateComplement(int a) {
        return ~a;
    }


    private static Boolean isDigit(String value) {
        return value.matches(DIGIT_PATTERN);
    }

}
