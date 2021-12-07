package Day07;

import java.util.List;

public class CrabPositionIdentifier {

    public long getFuelForNeededForBestPosition(List<Integer> crabPositions, boolean calculateDistanceOnly) {
        int furthestPosition = crabPositions.stream().max(Integer::compareTo).get();
        int closestPosition = crabPositions.stream().min(Integer::compareTo).get();

        long bestFuel = Long.MAX_VALUE;
        for (int position = closestPosition; position < furthestPosition; position++) {
            long fuelNeededForPosition = calculateDistanceOnly ? calculateFuelNeededForPosition(position, crabPositions) :
                    calculateFuelCorrectlyPosition(position, crabPositions);
            if (fuelNeededForPosition < bestFuel) {
                bestFuel = fuelNeededForPosition;
            }
        }
        return bestFuel;
    }

    private long calculateFuelNeededForPosition(int position, List<Integer> allPositions) {
        long requiredFuel = 0;
        for (Integer crabPosition : allPositions) {
            requiredFuel += Math.abs(crabPosition - position);
        }
        return requiredFuel;
    }

    private long calculateFuelCorrectlyPosition(int position, List<Integer> allPositions) {
        long requiredFuel = 0;
        for (Integer crabPosition : allPositions) {
            long fuelNeededByCurrentCrab = 0;
            int distance = Math.abs(crabPosition - position);
            for (int stepsToPosition = 1; stepsToPosition <= distance; stepsToPosition++) {
                fuelNeededByCurrentCrab += stepsToPosition;
            }
            requiredFuel += fuelNeededByCurrentCrab;
        }
        return requiredFuel;
    }
}
