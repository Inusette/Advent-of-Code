package Day6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Instruction {

    private static final Pattern INDEXES = Pattern.compile("(\\d+,\\d+)(.*?)(\\d+,\\d+)");

    private String action;
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;


    private Instruction(String action, int startRow, int startColumn, int endRow, int endColumn) {

        this.action = action;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;

    }


    static Instruction buildDefault(String instruction, String[] actions) throws Exception{

        // find the indexes in the instruction
        Matcher indexesMatcher = findIndexesMatcher(instruction);

        // convert indexes into arrays of ints
        int[] startInx = parseIndeces(indexesMatcher.group(1));
        int[] endInx = parseIndeces(indexesMatcher.group(3));


        // create the Instruction object
        return new Instruction(findAction(instruction, actions), startInx[0], startInx[1], endInx[0], endInx[1]);

    }


    private static Matcher findIndexesMatcher(String input) throws Exception {

        // create the matcher object using the pattern for extracting indexes
        Matcher matcher = INDEXES.matcher(input);

        // if no match is found, throw an exception
        if (!matcher.find()) {
            throw new Exception("No indexes match found! Panic!");
        }
        return matcher;
    }


    private static String findAction(String input, String[] actions) {

        // instantiate the action String
        String foundAction = "";

        // iterate over possible actions
        for (String possibleAction : actions) {

            // if any of them are present in the instruction,
            // assign to action and break out of the loop
            if (input.contains(possibleAction)) {
                foundAction = possibleAction;
                break;
            }
        }
        return foundAction;
    }


    private static int[] parseIndeces(String match) {

        int[] indeces = new int[2];

        String[] indString = match.split(",");
        indeces[0] = Integer.parseInt(indString[0]);
        indeces[1] = Integer.parseInt(indString[1]);

        return indeces;
    }


    String getAction() {
        return action;
    }


    int getStartRow() {
        return startRow;
    }

    int getStartColumn() {
        return startColumn;
    }

    int getEndRow() {
        return endRow;
    }

    int getEndColumn() {
        return endColumn;
    }
}
