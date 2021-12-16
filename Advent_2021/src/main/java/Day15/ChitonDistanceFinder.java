package Day15;

import advent.utils.AdventGridUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ChitonDistanceFinder {

    private final int maxX;

    private final int maxY;

    private final List<List<Integer>> cavernMap;

    Map<Point, Point> pathPoints;

    public ChitonDistanceFinder(List<List<Integer>> cavernMap) {
        this.maxX = cavernMap.size();
        this.maxY = cavernMap.size();
        this.cavernMap = cavernMap;
        pathPoints = new HashMap<>();
    }

    public int calculateLowestRiskPath() {
        findShortestPath();
        List<Point> path = new ArrayList<>();
        Point pathPoint = new Point(maxX - 1, maxY - 1);
        Point startPoint = new Point(0, 0);
        while (!pathPoint.equals(startPoint)) {
            Point cameFrom = pathPoints.get(pathPoint);
            path.add(pathPoint);
            pathPoint = cameFrom;
        }
        return path.stream().mapToInt(pointInPath -> cavernMap.get(pointInPath.y).get(pointInPath.x)).sum();
    }

    private void findShortestPath() {
        Map<Point, Integer> pointDistances = new HashMap<>();
        pointDistances.put(new Point(0, 0), 0);
        PriorityQueue<Point> nextPoints = new PriorityQueue<>(Comparator.comparing(point -> pointDistances.getOrDefault(point, Integer.MAX_VALUE)));
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                nextPoints.add(new Point(x, y));
            }
        }
        while (!nextPoints.isEmpty()) {
            Point currentPoint = nextPoints.poll();
            {
                List<Point> neighbours = AdventGridUtils.getNeighbours(currentPoint, maxX, maxY);
                for (Point neighbour : neighbours) {
                    int distance = pointDistances.getOrDefault(currentPoint, Integer.MAX_VALUE) + cavernMap.get(neighbour.y).get(neighbour.x);
                    if (distance < pointDistances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                        pointDistances.put(neighbour, distance);
                        pathPoints.put(neighbour, currentPoint);
                        nextPoints.remove(neighbour);
                        nextPoints.add(neighbour);
                    }
                }
            }
        }
    }
}
