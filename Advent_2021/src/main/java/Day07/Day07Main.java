package Day07;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day07Main {

    public static void main(String[] args) {
        List<Integer> inputAsIntegerList = getInput();
        System.out.println(inputAsIntegerList);

        CrabPositionIdentifier crabPositionIdentifier = new CrabPositionIdentifier();
        long fuelForNeededForBestPosition = crabPositionIdentifier.getFuelForNeededForBestPosition(inputAsIntegerList, true);
        System.out.println("For distance only, least fuel needed: " + fuelForNeededForBestPosition);

        fuelForNeededForBestPosition = crabPositionIdentifier.getFuelForNeededForBestPosition(inputAsIntegerList, false);
        System.out.println("After adjusting calculations, least fuel needed is: " + fuelForNeededForBestPosition);
    }

    protected static List<Integer> getInput() {
        return AdventFileUtils.readCommaSplitClassInputIntoIntegerList(Day07Main.class);
    }
}
