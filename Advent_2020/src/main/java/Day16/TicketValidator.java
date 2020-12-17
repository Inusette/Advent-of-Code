package Day16;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TicketValidator {

    final static String TICKET_RULE_PATTERN = "(.*): (\\d+-\\d+) or (\\d+-\\d+)";

    private final Map<String, TicketRule> ticketRules;

    private final List<List<Integer>> tickets;

    private final Map<String, Integer> rulePositions;

    private final List<Integer> yourTicket;


    public TicketValidator(List<String> inputLines) {
        this.ticketRules = parseTicketRules(inputLines);
        this.tickets = parseTickets(inputLines);
        this.rulePositions = new HashMap<>();
        this.yourTicket = parseYourTicket(inputLines);
    }

    public int calculateTicketErrorRate() {
        int errorRate = 0;
        for (List<Integer> ticket : tickets) {
            for (Integer field : ticket) {
                if (findValidRules(field).size() == 0) {
                    errorRate += field;
                }
            }
        }
        return errorRate;
    }

    public long multiplyDepartureFieldsOfYourTicket() {
        List<List<Integer>> validTickets = getValidTickets();
        Map<Integer, Set<String>> positionsToPossibleRules = findPossibleRulesForPositions(validTickets);
        findPositionsOfRules(positionsToPossibleRules);

        long result = 1;
        for (String rule : rulePositions.keySet()) {
            if (rule.startsWith("departure")) {
                result *= yourTicket.get(rulePositions.get(rule));
            }
        }
        return result;
    }

    private void findPositionsOfRules(Map<Integer, Set<String>> positionsToPossibleRules) {
        while (rulePositions.size() < ticketRules.size()) {
            for (Integer position : positionsToPossibleRules.keySet()) {
                if (positionsToPossibleRules.get(position).size() == 1) {
                    String singleRule = (String) positionsToPossibleRules.get(position).toArray()[0];
                    for (Set<String> possibleRules : positionsToPossibleRules.values()) {
                        possibleRules.remove(singleRule);
                    }
                    rulePositions.put(singleRule, position);
                }
            }
        }
    }

    private Map<Integer, Set<String>> findPossibleRulesForPositions(List<List<Integer>> validTickets) {
        Map<Integer, Set<String>> positionsToPossibleRules = new HashMap<>();
        for (int fieldPosition = 0; fieldPosition < validTickets.get(0).size(); fieldPosition++) {
            Set<String> possibleRules = new HashSet<>(ticketRules.keySet());
            for (List<Integer> ticket : validTickets) {
                possibleRules.retainAll(findValidRules(ticket.get(fieldPosition)));
            }
            positionsToPossibleRules.put(fieldPosition, possibleRules);
        }
        return positionsToPossibleRules;
    }

    private List<List<Integer>> getValidTickets() {
        List<List<Integer>> validTickets = new ArrayList<>();
        ticketLoop:
        for (List<Integer> ticket : tickets) {
            for (Integer field : ticket) {
                if (findValidRules(field).size() == 0) {
                    continue ticketLoop;
                }
            }
            validTickets.add(ticket);
        }
        return validTickets;
    }

    private Set<String> findValidRules(int field) {
        Set<String> validRules = new HashSet<>();
        for (TicketRule rule : ticketRules.values()) {
            if (field >= rule.firstMin && field <= rule.firstMax) {
                validRules.add(rule.ruleName);
            } else if (field >= rule.secondMin && field <= rule.secondMax) {
                validRules.add(rule.ruleName);
            }
        }
        return validRules;
    }

    protected List<List<Integer>> parseTickets(List<String> inputLines) {

        List<List<Integer>> allTickets = new ArrayList<>();
        int indexOfTicketStart = inputLines.indexOf("nearby tickets:");
        for (int currentLine = indexOfTicketStart + 1; currentLine < inputLines.size(); currentLine++) {
            List<Integer> currentTicket = processTicket(inputLines.get(currentLine));
            allTickets.add(currentTicket);
        }
        return allTickets;
    }

    private List<Integer> parseYourTicket(List<String> inputLines) {
        int indexOfTicketStart = inputLines.indexOf("your ticket:");
        return processTicket(inputLines.get(indexOfTicketStart + 1));
    }

    private List<Integer> processTicket(String line) {
        String[] split = line.split(",");
        return Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
    }

    protected Map<String, TicketRule> parseTicketRules(List<String> inputLines) {

        Map<String, TicketRule> ticketRules = new HashMap<>();
        Pattern ticketRulePattern = Pattern.compile(TICKET_RULE_PATTERN);
        for (String currentLine : inputLines) {
            if (currentLine.startsWith("your ticket")) {
                break;
            }
            Matcher matcher = ticketRulePattern.matcher(currentLine);
            if (matcher.find()) {
                TicketRule ticketRule = parseRule(matcher);
                ticketRules.put(ticketRule.ruleName, ticketRule);
            }
        }
        return ticketRules;
    }

    private TicketRule parseRule(Matcher matcher) {
        TicketRule ticketRule = new TicketRule();
        ticketRule.ruleName = matcher.group(1);
        int[] firstRange = getMinAndMax(matcher.group(2));
        ticketRule.firstMin = firstRange[0];
        ticketRule.firstMax = firstRange[1];
        int[] secondRange = getMinAndMax(matcher.group(3));
        ticketRule.secondMin = secondRange[0];
        ticketRule.secondMax = secondRange[1];
        return ticketRule;
    }

    private int[] getMinAndMax(String pattern) {
        String[] split = pattern.split("-");
        int firstNumber = Integer.parseInt(split[0]);
        int secondNumber = Integer.parseInt(split[1]);
        if (firstNumber < secondNumber)
            return new int[]{firstNumber, secondNumber};
        else
            return new int[]{secondNumber, firstNumber};
    }

    protected static class TicketRule {
        String ruleName;
        int firstMin;
        int firstMax;
        int secondMin;
        int secondMax;
    }
}
