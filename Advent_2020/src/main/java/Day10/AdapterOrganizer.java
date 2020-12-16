package Day10;

import java.util.Collections;
import java.util.List;

public class AdapterOrganizer {

    public static int multiplyJoltDifferences(List<Integer> jolts) {
        jolts.add(0);
        Collections.sort(jolts);
        jolts.add(jolts.get(jolts.size() - 1) + 3);

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

}
