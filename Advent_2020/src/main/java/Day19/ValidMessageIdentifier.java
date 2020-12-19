package Day19;

import advent.utils.AdventFinderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A collab with @slavov-vili <3
 */
public class ValidMessageIdentifier {

    Map<Integer, String> allPatterns;

    List<String> allMessages;

    private final static String REPLACER_TEMPLATE = "\\b%d\\b";

    public ValidMessageIdentifier(List<String> inputLines) {
        this.allPatterns = decodeMessageRules(inputLines);
        this.allMessages = parseMessagesFromInput(inputLines);
    }

    public int solutionA() {
        return countRuleMatches(allPatterns.get(0));
    }

    public int solutionB() {
        int matchCount = 0;
        String pattern31 = String.format("(%s)", allPatterns.get(31));
        String pattern42 = String.format("(%s)", allPatterns.get(42));

        for (String message : allMessages) {
            Optional<String> endSequence = AdventFinderUtils.findSequenceAtEnd(message, pattern31);
            if (endSequence.isEmpty()) {
                continue;
            }
            int numberOfMatches = AdventFinderUtils.countAllMatches(endSequence.get(), pattern31);
            String pattern = String.format("%s+%s{%d}%s{%d}", pattern42, pattern42, numberOfMatches, pattern31, numberOfMatches);
            if (message.matches(pattern)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public int countRuleMatches(String rulePattern) {
        return (int) allMessages.stream()
                .filter(message -> message.matches(rulePattern))
                .count();
    }

    private Map<Integer, String> decodeMessageRules(List<String> inputLines) {
        Map<Integer, String> rulesWithRules = new HashMap<>();

        for (String line : inputLines) {
            if (line.isEmpty()) {
                break;
            }
            String[] splitLine = line.split(":");
            rulesWithRules.put(Integer.parseInt(splitLine[0]), splitLine[1].trim());
        }
        return replaceRules(rulesWithRules);
    }

    private List<String> parseMessagesFromInput(List<String> inputLines) {
        return inputLines.subList(allPatterns.size() + 1, inputLines.size());
    }

    private Map<Integer, String> replaceRules(Map<Integer, String> ruleMap) {

        Map<Integer, String> rules = new HashMap<>(ruleMap);

        while (rules.values().stream().anyMatch(this::containsNumbers)) {
            for (Integer ruleNumber : rules.keySet()) {
                if (!containsNumbers(rules.get(ruleNumber))) {
                    rules.replace(ruleNumber, rules.get(ruleNumber).replaceAll("\"", ""));
                    for (Integer currentNumber : rules.keySet()) {
                        String pattern = String.format(REPLACER_TEMPLATE, ruleNumber);
                        String replacement = String.format("(%s)", rules.get(ruleNumber));
                        String replacedValue = AdventFinderUtils.replaceAllFoundPatterns(rules.get(currentNumber), pattern, replacement);
                        if (!containsNumbers(replacedValue)) {
                            replacedValue = replacedValue.replaceAll(" ", "");
                        }
                        rules.replace(currentNumber, replacedValue);
                    }
                }
            }
        }
        return rules;
    }

    private boolean containsNumbers(String value) {
        return AdventFinderUtils.containsPattern(value, "\\d");
    }
}
