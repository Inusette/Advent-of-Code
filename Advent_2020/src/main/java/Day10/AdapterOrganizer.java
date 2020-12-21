package Day10;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterOrganizer {

    public static int multiplyJoltDifferences(List<Integer> jolts) {
        preProcessJolts(jolts);

        int oneJoltCount = 0;
        int threeJoltsCount = 0;
        for (int currentJolt = 1; currentJolt < jolts.size(); currentJolt++) {
            int currentDifference = jolts.get(currentJolt) - jolts.get(currentJolt - 1);
            if (currentDifference == 1) {
                oneJoltCount++;
            } else if (currentDifference == 3) {
                threeJoltsCount++;
            }
        }
        return oneJoltCount * threeJoltsCount;
    }

    private static void preProcessJolts(List<Integer> jolts) {
        jolts.add(0);
        Collections.sort(jolts);
        jolts.add(jolts.get(jolts.size() - 1) + 3);
    }

    public static long findAllPossibleDifferences(List<Integer> jolts) {
        preProcessJolts(jolts);
        Map<Integer, Long> checkedAdaptors = new HashMap<>();
        return countPossiblePaths(checkedAdaptors, 0, jolts);
    }

    private static long countPossiblePaths(Map<Integer, Long> checkedAdaptors, int currentJolt, List<Integer> adaptors) {
        if (currentJolt == adaptors.size() - 1) {
            return 1;
        }
        int currentAdaptor = adaptors.get(currentJolt);
        if (checkedAdaptors.containsKey(currentAdaptor)) {
            return checkedAdaptors.get(currentAdaptor);
        }
        long totalNumberOfPaths = 0;
        for (int currentDifference = 1; currentDifference <= 3; currentDifference++) {
            int nextAdaptor = currentAdaptor + currentDifference;
            if (adaptors.contains(nextAdaptor)) {
                checkedAdaptors.put(nextAdaptor, countPossiblePaths(checkedAdaptors, adaptors.indexOf(nextAdaptor), adaptors));
                totalNumberOfPaths += checkedAdaptors.get(nextAdaptor);
            }
        }
        return totalNumberOfPaths;
    }
}

