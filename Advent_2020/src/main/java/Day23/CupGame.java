package Day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A collaboration with @slavov-vili <3
 */
public class CupGame {

    private final Map<Integer, Integer> cupPointers;
    private int currentCup;

    public CupGame(String input, int size) {
        cupPointers = fillCupPointers(input, size);
    }

    public String getLabelsStartingWith1(int rounds) {
        playGame(rounds);
        List<String> orderedLabels = new ArrayList<>();
        int current = 1;
        do {
            int next = cupPointers.get(current);
            orderedLabels.add(String.valueOf(next));
            current = next;
        } while (current != 1);
        return String.join("", orderedLabels);
    }

    public String playAndMultiply(int rounds) {
        playGame(rounds);

        long result = 1L;
        int current = cupPointers.get(1);
        result *= current;
        current = cupPointers.get(current);
        result *= current;
        return String.valueOf(result);
    }

    /**
     * Example of a round:
     * cups: (3) 8  9  1  2  5  4  6  7
     * pick up: 8, 9, 1
     * destination: 2
     * result: 3 2 8 9 1 5 4 6 7
     *
     * To play the game using the reference pointer map, all one needs to do, is change the values that the keys
     * point to. After one round, at key 3 you will find 2 (not 8), at key 2 you will find 8 (not 5),
     * and at key 1 you will find 5 (not 2).            oO
     */
    private void playGame(int rounds) {
        for (int i = 0; i < rounds; i++) {
            List<Integer> nextThree = new ArrayList<>();
            int nextCup = currentCup;
            for (int j = 0; j < 3; j++) {
                nextCup = cupPointers.get(nextCup);
                nextThree.add(nextCup);
            }
            cupPointers.put(currentCup, cupPointers.get(nextCup));

            int minLabel = 1;
            int maxLabel = cupPointers.size();
            int destinationLabel = calcDestinationLabel(currentCup, minLabel, maxLabel, nextThree);
            int nextOfDestination = cupPointers.get(destinationLabel);
            cupPointers.put(destinationLabel, nextThree.get(0));
            cupPointers.put(nextThree.get(2), nextOfDestination);

            currentCup = cupPointers.get(currentCup);
        }
    }

    private int calcDestinationLabel(int currentLabel, int minLabel, int maxLabel, List<Integer> nextThree) {
        int destination = currentLabel;
        do {
            destination = calcNextLabel(destination, minLabel, maxLabel);
        } while (nextThree.contains(destination));

        return destination;
    }

    private int calcNextLabel(int currentLabel, int minLabel, int maxLabel) {
        return (currentLabel == minLabel) ? maxLabel : currentLabel - 1;
    }

    /**
     * The idea is to represent the cup labels in a way, where a key of the cupPointers map is a current cup label
     * and the value at that key is the label of the cup that follows it.
     *
     * So, if you have a cup sequence 3 8 9 1, then at key 3 you will put 8, at key 8 you put 9, at 9 you put 1, and
     * at 1 you put 3, because it represents a circle, so it loops back to the beginning
     *
     * And if you need to fill in the rest of the list with values until 10000, then it does the same.
     * E.g. at key 25, there is 26, at key 26, there is 27, and so on
     */
    private Map<Integer, Integer> fillCupPointers(String input, int pointersCount) {
        Map<Integer, Integer> pointers = new HashMap<>();
        List<Integer> initialPointers = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        currentCup = initialPointers.get(0);

        for (int i = 0; i < initialPointers.size() - 1; i++) {
            int currentLabel = initialPointers.get(i);
            int nextLabel = initialPointers.get(i + 1);
            pointers.put(currentLabel, nextLabel);
        }
        int maxLabel = initialPointers.size();
        int lastLabel = initialPointers.get(maxLabel - 1);

        if (pointersCount > maxLabel) {
            pointers.put(lastLabel, maxLabel + 1);
            for (int furtherIndex = maxLabel + 1; furtherIndex < pointersCount; furtherIndex++) {
                pointers.put(furtherIndex, furtherIndex + 1);
            }
            lastLabel = pointersCount;
        }
        pointers.put(lastLabel, initialPointers.get(0));
        return pointers;
    }
}
