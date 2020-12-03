package Day03;

import java.util.List;

public class MapTraverser {

    public static int findTreesOnPath(List<String> inputLines) {
        return findTreesOnPath(3, 1, inputLines);
    }

    public static long findProbabilityFromAllSlopes(List<String> inputLines) {
        long result = 1;
        result *= findTreesOnPath(1, 1, inputLines);
        result *= findTreesOnPath(3, 1, inputLines);
        result *= findTreesOnPath(5, 1, inputLines);
        result *= findTreesOnPath(7, 1, inputLines);
        result *= findTreesOnPath(1, 2, inputLines);
        return result;
    }

    private static int findTreesOnPath(int stepsRight, int stepsDown, List<String> treeMap) {
        int treeCount = 0;
        int currentColumn = stepsRight;
        int patternSize = treeMap.get(0).length();
        for (int currentRow = stepsDown; currentRow < treeMap.size(); currentRow += stepsDown) {
            if (treeMap.get(currentRow).charAt(currentColumn) == '#') {
                treeCount++;
            }
            currentColumn = (currentColumn + stepsRight) % patternSize;
        }
        return treeCount;
    }
}
