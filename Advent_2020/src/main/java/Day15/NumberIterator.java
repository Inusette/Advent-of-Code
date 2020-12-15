package Day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberIterator {

    public static long iterateNumbers(List<Long> givenNumbers, int lastIndex) {
        Map<Long, Long> allSeenNumbers = fillInitiallySeenNumbers(givenNumbers);
        List<Long> allSpokenNumbers = fillInitiallySpokenNumbers(givenNumbers);

        for (int currentIndex = givenNumbers.size(); currentIndex < lastIndex; currentIndex++) {
            long previousIndex = currentIndex - 1;
            Long previousNumber = allSpokenNumbers.get((int) previousIndex);
            if (allSeenNumbers.containsKey(previousNumber)) {
                Long previouslySeenAt = allSeenNumbers.get(previousNumber);
                allSpokenNumbers.add(previousIndex - previouslySeenAt);
            } else {
                allSpokenNumbers.add(0L);
            }
            allSeenNumbers.put(previousNumber, previousIndex);
        }
        return allSpokenNumbers.get(lastIndex - 1); // I started with 0, not with 1
    }

    private static Map<Long, Long> fillInitiallySeenNumbers(List<Long> givenNumbers) {
        Map<Long, Long> seenNumbers = new HashMap<>();
        for (int numIndex = 0; numIndex < givenNumbers.size() - 1; numIndex++) {
            seenNumbers.put(givenNumbers.get(numIndex), (long) numIndex);
        }
        return seenNumbers;
    }

    private static List<Long> fillInitiallySpokenNumbers(List<Long> givenNumbers) {
        return new ArrayList<>(givenNumbers);
    }
}
