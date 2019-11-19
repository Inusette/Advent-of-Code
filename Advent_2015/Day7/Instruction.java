package Day7;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Instruction {

    // A bunch of constants >.>
    private static final String GATE = "gate";
    private static final String VALUE = "value";
    private static final String WIRE_A = "valueA";
    private static final String WIRE_B = "wireB";

    private static final String[] gates = {CircuitConstants.NOT, CircuitConstants.AND, CircuitConstants.OR,
    CircuitConstants.L_SHIFT, CircuitConstants.R_SHIFT};


    // Regular Expression constants
    // target = group(2)
    private static final Pattern TARGET_PATTERN = Pattern.compile("(->)\\s(.+)");

    // first - group(1), operator = group(2), second = group(3)
    private static final Pattern TWO_COMPONENTS = Pattern.compile("(.+)\\s(.+)\\s(.+)\\s(->)");

    // operator = group(1), first = group(2)
    private static final Pattern ONE_COMPONENT = Pattern.compile("(.+)\\s(.+)\\s(->)");

    // value = group(1)
    private static final Pattern VALUE_PATTERN = Pattern.compile("(.+)\\s(->)");


    // Class states
    private HashMap<String, String> rule;
    private String target;


    Instruction(String input) throws Exception{

        this.rule = new HashMap<>();
        this.target = findTarget(input);

        // add the conditions or the value of the target to te map
        addLeftPart(input);

    }


    private String findTarget(String input) throws Exception{

        String targetValue;

        // find the regex pattern in the input
        Matcher targetMatcher = TARGET_PATTERN.matcher(input);

        // add to the map
        if (targetMatcher.find()) {
            targetValue = targetMatcher.group(2);
        }
        else {
            throw new Exception("Target value not found");
        }

        return targetValue;
    }


    private void addLeftPart(String input) throws Exception{

        // check if any of the gates are present in the instructions
        Boolean present = false;

        for (String gate: gates) {
            if (input.contains(gate)) {
                present = true;
                break;
            }
        }

        // if there are no gates, then add the value to the map
        if (!present) {
            addValue(input);
        }
        else {
            // iterate over gates again and see which one is present
            for (String gate : gates) {

                if (input.contains(gate)) {

                    // if it is a NOT gate, use one component pattern
                    if (gate.equals(CircuitConstants.NOT)) {
                        addOneComponent(input);
                        break;
                    }
                    // if it is any other gate, use 2 components pattern
                    else {
                        addTwoComponents(input);
                        break;
                    }
                }
            }
        }
    }


    private void addOneComponent(String input) throws Exception{

        Matcher componentMatcher = ONE_COMPONENT.matcher(input);

        if (componentMatcher.find()) {
            rule.put(GATE, componentMatcher.group(1));
            rule.put(VALUE, componentMatcher.group(2));
        }
        else {
            throw new Exception("Component is not found");
        }

    }


    private void addTwoComponents(String input) throws Exception{

        Matcher componentMatcher = TWO_COMPONENTS.matcher(input);

        if (componentMatcher.find()) {
            rule.put(GATE, componentMatcher.group(2));
            rule.put(WIRE_A, componentMatcher.group(1));
            rule.put(WIRE_B, componentMatcher.group(3));
        }
        else {
            throw new Exception("Components are not found");
        }

    }


    private void addValue(String input) throws Exception{

        Matcher valueMatcher = VALUE_PATTERN.matcher(input);

        if (valueMatcher.find()) {
            rule.put(VALUE, valueMatcher.group(1));
        }
        else {
            throw new Exception("Value is not found");
        }

    }


    String getTarget() {
        return target;
    }

    Boolean hasGate() {
        return rule.containsKey(GATE);
    }

    String getGate() {
        return rule.get(GATE);
    }

    String getValue() {
        return rule.get(VALUE);
    }

    Boolean hasMultipleComponents() {
        return rule.containsKey(WIRE_A) && rule.containsKey(WIRE_B);
    }

    String getWireA() {
        return rule.get(WIRE_A);
    }

    String getWireB() {
        return rule.get(WIRE_B);
    }
}
