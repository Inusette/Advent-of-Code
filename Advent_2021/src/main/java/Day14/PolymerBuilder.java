package Day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymerBuilder {

    private Map<String, Long> syllableCount;

    private final Map<String, Long> letterCount;

    private final Map<String, String> rules;

    public PolymerBuilder() {
        this.rules = new HashMap<>();
        this.syllableCount = new HashMap<>();
        this.letterCount = new HashMap<>();
    }

    public long calculateCommonElementDifferenceAfter(int numberOfSteps, String initialString, List<String> rules) {
        populateRules(rules);
        populateInitialCounts(initialString);

        int step = 1;
        while (step <= numberOfSteps) {
            insertPairs();
            step++;
        }

        long highestFrequency = letterCount.values().stream().max(Long::compareTo).get();
        long lowestFrequency = letterCount.values().stream().min(Long::compareTo).get();
        return highestFrequency - lowestFrequency;
    }

    private void insertPairs() {
        Map<String, Long> grownSyllableCount = new HashMap<>();
        for (String syllable : syllableCount.keySet()) {
            if (rules.containsKey(syllable)) {
                String newLetter = rules.get(syllable);
                String firstSyllable = syllable.charAt(0) + newLetter;
                String secondSyllable = newLetter + syllable.charAt(1);
                grownSyllableCount.put(firstSyllable, grownSyllableCount.getOrDefault(firstSyllable, 0L) + syllableCount.get(syllable));
                grownSyllableCount.put(secondSyllable, grownSyllableCount.getOrDefault(secondSyllable, 0L) + syllableCount.get(syllable));

                letterCount.put(newLetter, letterCount.getOrDefault(newLetter, 0L) + syllableCount.get(syllable));
            } else {
                grownSyllableCount.put(syllable, syllableCount.get(syllable));
            }
        }
        syllableCount = grownSyllableCount;
    }

    private void populateInitialCounts(String initialInputString) {
        char[] chars = initialInputString.toCharArray();
        for (int charIndex = 0; charIndex < initialInputString.length() - 1; charIndex++) {
            String currentSyllable = chars[charIndex] + String.valueOf(chars[charIndex + 1]);
            syllableCount.merge(currentSyllable, 1L, (oldValue, newValue) -> syllableCount.get(currentSyllable) + newValue);
            String letter = String.valueOf(chars[charIndex]);
            letterCount.merge(letter, 1L, (oldValue, newValue) -> letterCount.get(letter) + newValue);
        }
        String lastLetter = String.valueOf(chars[chars.length - 1]);
        letterCount.merge(lastLetter, 1L, (oldValue, newValue) -> letterCount.get(lastLetter) + newValue);
    }

    private void populateRules(List<String> rulesList) {
        for (String line : rulesList) {
            String[] splitLine = line.trim().split(" -> ");
            rules.put(splitLine[0], splitLine[1]);
        }
    }
}
