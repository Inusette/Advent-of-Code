package Day02;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day02Main {

    public static void main(String[] args) {

        List<String> inputAsStringList = getInput();
        System.out.println("This is the input: \n" + inputAsStringList);

        DestinationPlanner destinationPlanner = new DestinationPlanner();
        long position = destinationPlanner.calculateInitialDestinationPosition(inputAsStringList);
        System.out.println("Destination position is: " + position);

        long positionAfterRecalculation = destinationPlanner.calculateCorrectDestinationPosition(inputAsStringList);
        System.out.println("Correct destination position is: " + positionAfterRecalculation);


    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day02Main.class);
    }
}
