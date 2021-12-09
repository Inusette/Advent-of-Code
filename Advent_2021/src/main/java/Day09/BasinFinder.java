package Day09;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasinFinder {

    private final List<List<Integer>> caveMap;

    private final int maxX;

    private final int maxY;

    public BasinFinder(List<List<Integer>> caveMap) {
        this.caveMap = caveMap;
        maxX = caveMap.get(0).size();
        maxY = caveMap.size();
    }

    public int sumUpBasinBottoms() {
        List<Point> basinBottoms = findBasinBottoms();
        return basinBottoms.stream().mapToInt(bottom -> caveMap.get(bottom.y).get(bottom.x) + 1).sum();
    }

    public int multiplyLargestBasins() {
        List<Point> basinBottoms = findBasinBottoms();
        List<Integer> basinSizes = new ArrayList<>();
        for (Point bottom : basinBottoms) {
            Set<Point> basin = new HashSet<>();
            determineBasin(bottom, basin);
            basinSizes.add(basin.size());
        }
        return basinSizes.stream().sorted().skip(basinSizes.size() - 3).reduce((a, b) -> a * b).get();
    }

    private List<Point> findBasinBottoms() {
        List<Point> bottomLocations = new ArrayList<>();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (isBottom(x, y)) {
                    bottomLocations.add(new Point(x, y));
                }
            }
        }
        return bottomLocations;
    }

    private boolean isBottom(int x, int y) {
        int currentPointValue = caveMap.get(y).get(x);
        boolean isBottom = true;
        if (x > 0 && caveMap.get(y).get(x - 1) <= currentPointValue) {
            isBottom = false;
        }
        if (x < maxX - 1 && caveMap.get(y).get(x + 1) <= currentPointValue) {
            isBottom = false;
        }
        if (y > 0 && caveMap.get(y - 1).get(x) <= currentPointValue) {
            isBottom = false;
        }
        if (y < maxY - 1 && caveMap.get(y + 1).get(x) <= currentPointValue) {
            isBottom = false;
        }
        return isBottom;
    }

    private void determineBasin(Point point, Set<Point> basin) {
        int currentPointValue = caveMap.get(point.y).get(point.x);
        if (currentPointValue == 9) {
            return;
        }

        basin.add(point);
        if (point.x > 0) {
            Point leftNeighbour = new Point(point.x - 1, point.y);
            if (!basin.contains(leftNeighbour)) {
                determineBasin(leftNeighbour, basin);
            }
        }
        if (point.x < maxX - 1) {
            Point rightNeighbour = new Point(point.x + 1, point.y);
            if (!basin.contains(rightNeighbour)) {
                determineBasin(rightNeighbour, basin);
            }
        }
        if (point.y > 0) {
            Point aboveNeighbour = new Point(point.x, point.y - 1);
            if (!basin.contains(aboveNeighbour)) {
                determineBasin(aboveNeighbour, basin);
            }
        }
        if (point.y < maxY - 1) {
            Point belowNeighbour = new Point(point.x, point.y + 1);
            if (!basin.contains(belowNeighbour)) {
                determineBasin(belowNeighbour, basin);
            }
        }
    }
}
