package Day12;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day12Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        ShipNavigator shipNavigator = new ShipNavigator(inputLines);
        int distance = shipNavigator.computeDistanceFromDirections();
        System.out.println("distance = " + distance);

        int distanceWithWaypoint = shipNavigator.computeDistanceUsingWaypoint();
        System.out.println("distanceWithWaypoint = " + distanceWithWaypoint);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day12Main.class);
    }
}
