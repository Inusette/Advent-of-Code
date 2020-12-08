package Day08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This solution is inspired by the collaboration with @kraemerstim, Judith, and Alena.
 * Special thanks to @slavov-vili for brainstorming with me <3
 */
public class AccumulatorComputer {

    final String INSTRUCTION_PATTERN = "^(\\S{3}) ([+-]\\d*)$";  //© by @kraemerstim

    final String NOP = "nop";

    final String ACC = "acc";

    final String JMP = "jmp";

    private Integer currentInstructionIndex;

    private int accumulator;

    public AccumulatorComputer() {
        resetComputer();
    }

    public int computeUntilFirstLoop(List<String> inputLines) {

        List<Instruction> instructions = parseInstructions(inputLines);
        resetComputer();
        computeInstructions(instructions);
        return accumulator;
    }

    public int computeUntilLastInstruction(List<String> inputLines) {
        resetComputer();
        List<Instruction> instructions = parseInstructions(inputLines);
        Set<Integer> indicesBeforeTheLoop = computeInstructions(instructions);

        for (Integer currentIndex : indicesBeforeTheLoop) {
            resetComputer();
            List<Instruction> customInstructions = new ArrayList<>(instructions);
            Instruction currentInstruction = customInstructions.get(currentIndex);
            customInstructions.set(currentIndex, changeInstruction(currentInstruction));

            computeInstructions(customInstructions);
            if (currentInstructionIndex == (instructions.size() - 1))
                break;
        }

        return accumulator;
    }

    private Instruction changeInstruction(Instruction instruction) {
        switch (instruction.operation) {
            case NOP:
                return new Instruction(JMP, instruction.offset);
            case JMP:
                return new Instruction(NOP, instruction.offset);
            default:
                return instruction;
        }
    }

    /**
     * Computes the given instructions
     *
     * @return a set of indices of the executed instructions
     */
    private Set<Integer> computeInstructions(List<Instruction> instructions) {
        Set<Integer> indicesOfExecutedInstructions = new HashSet<>();
        while (!indicesOfExecutedInstructions.contains(currentInstructionIndex)
                && currentInstructionIndex != (instructions.size() - 1)) {
            indicesOfExecutedInstructions.add(currentInstructionIndex);
            executeInstruction(instructions.get(currentInstructionIndex));
        }
        return indicesOfExecutedInstructions;
    }

    private void executeInstruction(Instruction instruction) {
        switch (instruction.operation) {
            case NOP:
                currentInstructionIndex++;
                break;
            case JMP:
                currentInstructionIndex = addOffset(currentInstructionIndex, instruction.offset);
                break;
            case ACC:
                accumulator = addOffset(accumulator, instruction.offset);
                currentInstructionIndex++;
                break;
            default:
                throw new IllegalArgumentException("Tim says WTF? there is no " + instruction.operation + " instruction...");
        }
    }

    private List<Instruction> parseInstructions(List<String> inputLines) {

        List<Instruction> operationList = new ArrayList<>();
        Pattern instructionPattern = Pattern.compile(INSTRUCTION_PATTERN);
        for (String instructionString : inputLines) {
            Matcher matcher = instructionPattern.matcher(instructionString);
            if (matcher.matches()) {
                operationList.add(new Instruction(matcher.group(1), Integer.parseInt(matcher.group(2))));
            }
        }
        return operationList;
    }

    private int addOffset(int currentValue, int offset) {
        return currentValue + offset;
    }

    private void resetComputer() {
        accumulator = 0;
        currentInstructionIndex = 0;
    }

    private static class Instruction {
        String operation;
        int offset;

        public Instruction(String operation, int offset) {
            this.operation = operation;
            this.offset = offset;
        }
    }
}
