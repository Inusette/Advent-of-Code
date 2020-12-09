package Day09;

import advent.utils.AdventMathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmasDecoder {

    public static long findFirstUnEncodedNumber(List<Long> cypherNumbers) {

        long firstUnenctyptedElement = 0;
        for (int currentElementIndex = 25; currentElementIndex < cypherNumbers.size(); currentElementIndex++) {
            if (!isEncrypted(cypherNumbers, currentElementIndex)) {
                firstUnenctyptedElement = cypherNumbers.get(currentElementIndex);
                break;
            }
        }
        return firstUnenctyptedElement;
    }

    public static long findWeaknessRange(List<Long> cypherNumbers, long firstUnenctyptedElement) {
        List<Long> contiguousSumSet = getContiguousSumSet(cypherNumbers, firstUnenctyptedElement);
        Collections.sort(contiguousSumSet);
        return contiguousSumSet.get(0) + contiguousSumSet.get(contiguousSumSet.size()-1);
    }

    private static boolean isEncrypted(List<Long> cypher, int elementIndex) {
        for (int upperIndex = elementIndex - 1; upperIndex > elementIndex - 25; upperIndex--) {
            for (int lowerIndex = elementIndex - 2; lowerIndex >= elementIndex - 25; lowerIndex--) {
                if (AdventMathUtils.isSumOfElements(cypher.get(elementIndex), cypher.get(upperIndex), cypher.get(lowerIndex)))
                    return true;
            }
        }
        return false;
    }

    private static List<Long> getContiguousSumSet(List<Long> cypherNumbers, long sum) {

        for (int lowerIndex = 0; lowerIndex < cypherNumbers.size() - 1; lowerIndex++) {
            Long currentRangeStart = cypherNumbers.get(lowerIndex);
            if (currentRangeStart >= sum)
                continue;
            List<Long> currentContSet = new ArrayList<>(Collections.singletonList(currentRangeStart));
            for (int upperIndex = lowerIndex + 1; upperIndex < cypherNumbers.size(); upperIndex++) {
                currentContSet.add(cypherNumbers.get(upperIndex));
                long currentSum = currentContSet.stream().mapToLong(number -> number).sum();
                if (currentSum == sum) {
                    return currentContSet;
                }
                else if (currentSum > sum) {
                    break;
                }
            }
        }
        return new ArrayList<>();
    }
}
