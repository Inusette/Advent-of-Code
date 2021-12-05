package Day05;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VentSegmentParser {

    public static List<Point> getAllPoints(List<String> unprocessedVents, boolean includeDiagonalLines) {
        List<Set<Point>> allVentSegments = VentSegmentParser.parseSegments(unprocessedVents, includeDiagonalLines);
        return allVentSegments.stream().flatMap(Set::stream).collect(Collectors.toList());
    }

    public static List<Set<Point>> parseSegments(List<String> unprocessedVents, boolean includeDiagonalLines) {
        List<Set<Point>> pointSegments = new ArrayList<>();
        for (String line : unprocessedVents) {
            String[] splitLine = line.trim().split(" -> ");
            String[] startingPosition = splitLine[0].split(",");
            Point ventStart = new Point(Integer.parseInt(startingPosition[0]), Integer.parseInt(startingPosition[1]));
            String[] endPosition = splitLine[1].split(",");
            Point ventEnd = new Point(Integer.parseInt(endPosition[0]), Integer.parseInt(endPosition[1]));
            Set<Point> pointsInRange = getPointsInRange(ventStart, ventEnd, includeDiagonalLines);
            if (!pointsInRange.isEmpty()) {
                pointSegments.add(pointsInRange);
            }
        }
        return pointSegments;
    }

    private static Set<Point> getPointsInRange(Point startPoint, Point endPoint, boolean includeDiagonalLines) {
        Set<Point> pointsInRange = new HashSet<>();
        if (startPoint.x == endPoint.x) {
            // start from the smallest y and move up
            for (int y = Math.min(startPoint.y, endPoint.y); y <= Math.max(startPoint.y, endPoint.y); y++) {
                pointsInRange.add(new Point(startPoint.x, y));
            }
        } else if (startPoint.y == endPoint.y) {
            // start from the smallest x and move up
            for (int x = Math.min(startPoint.x, endPoint.x); x <= Math.max(startPoint.x, endPoint.x); x++) {
                pointsInRange.add(new Point(x, startPoint.y));
            }
        } else {
            if (includeDiagonalLines) {
                // start from the first given point and either increment or decrement
                int updateXBy = findUpdateBy(startPoint.x, endPoint.x);
                int updateYBy = findUpdateBy(startPoint.y, endPoint.y);
                int currentY = startPoint.y;
                int currentX = startPoint.x;
                pointsInRange.add(new Point(currentX, currentY));
                do {
                    currentX += updateXBy;
                    currentY += updateYBy;
                    pointsInRange.add(new Point(currentX, currentY));
                }
                while (currentX != endPoint.x);
            }
        }
        return pointsInRange;
    }

    private static int findUpdateBy(int start, int end) {
        if (start < end) {
            return 1;
        } else return -1;
    }
}
