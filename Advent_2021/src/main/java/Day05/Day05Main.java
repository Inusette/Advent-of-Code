package Day05;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day05Main {

    public static void main(String[] args) {
        List<String> inputAsStringLines = getInput();

        VentsFinder ventFinder = new VentsFinder();
        long overlappingVents = ventFinder.findOverlappingVents(inputAsStringLines);
        System.out.println("The number of points with horizontal and vertical segments overlapping: "
                + overlappingVents);

        long withDiagonalSegments = ventFinder.findOverlappingVentsWithDiagonalSegments(inputAsStringLines);
        System.out.println("The number of points with horizontal, vertical, and diagonal segments overlapping: "
                + withDiagonalSegments);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day05Main.class);
    }
}
