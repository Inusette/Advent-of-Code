package Day11;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day11Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        SeatMapPredictor predictor = new SeatMapPredictor(inputLines);
        int allOccupiedSeats = predictor.findOccupiedSeatsWithNoAdjacentNeighbours();
        System.out.println("allOccupiedSeats = " + allOccupiedSeats);

        int occupiedSeatsWithNoVisibleNeighbours = predictor.findOccupiedSeatsWithNoVisibleNeighbours();
        System.out.println("occupiedSeatsWithNoVisibleNeighbours = " + occupiedSeatsWithNoVisibleNeighbours);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day11Main.class);
    }
}
