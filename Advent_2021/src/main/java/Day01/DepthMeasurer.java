package Day01;

import java.util.ArrayList;
import java.util.List;

public class DepthMeasurer {

    public static int measureDepthIncreases(List<Integer> depths) {
        int depthCount = 0;
        for (int currentDepthIndex = 0; currentDepthIndex < depths.size() - 1; currentDepthIndex++) {
            if (depths.get(currentDepthIndex) < depths.get(currentDepthIndex + 1)) {
                depthCount++;
            }
        }
        return depthCount;
    }

    public static int measureDepthSumIncreases(List<Integer> depths) {
        List<Integer> sums = new ArrayList<>();
        for (int currentDepthIndex = 0; currentDepthIndex < depths.size() - 2; currentDepthIndex++) {
            sums.add(depths.get(currentDepthIndex) + depths.get(currentDepthIndex + 1) + depths.get(currentDepthIndex + 2));
        }
        return measureDepthIncreases(sums);
    }
}
