package Day05;

import java.util.Arrays;
import java.util.List;

public class BoardingPassProcessor {

    public static int findMaxBoardingID(List<String> inputLines) {
        int[][] planeMap = new int[128][8];
        int maxID = 0;

        for (String directions : inputLines) {
            int row = findPosition(directions.substring(0, 7), 0, 127);
            int column = findPosition(directions.substring(7), 0, 7);
            int ID = calculateID(row, column);

            planeMap[row][column] = ID;

            if (ID > maxID) {
                maxID = ID;
            }
        }
        printPlaneMap(planeMap);
        return maxID;
    }

    private static void printPlaneMap(int[][] planeMap) {
        for (int[] row : planeMap) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int findPosition(String directions, int startPosition, int endPosition) {

        if (startPosition == endPosition) {
            return startPosition;
        }

        char currentStep = directions.charAt(0);
        int middlePosition = calculateMiddle(startPosition, endPosition);
        String nextSteps = directions.substring(1);

        if (currentStep == 'F' || currentStep == 'L') {
            return findPosition(nextSteps, startPosition, middlePosition);
        }
        else {
            return findPosition(nextSteps, middlePosition +1, endPosition);
        }
    }

    private static int calculateMiddle(int startPosition, int endPosition) {
        int halfDistance = Math.abs(endPosition - startPosition) / 2;
        return startPosition + halfDistance;
    }

    private static int calculateID(int rowPosition, int columnPosition) {
        return rowPosition * 8 + columnPosition;
    }
}
