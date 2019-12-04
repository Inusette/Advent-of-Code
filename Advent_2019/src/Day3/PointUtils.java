package Day3;

import java.util.List;
import java.awt.*;


class PointUtils {


    static int findClosestPointByManhattanDistance(Point goal, List<Point> intersections) {

        int min = calculateManhattanDistance(goal, intersections.get(0));

        for (Point point : intersections) {

            int distance = calculateManhattanDistance(goal, point);

            if (distance < min) {
                min = distance;
            }
        }
        return min;
    }


    private static int calculateManhattanDistance(Point a, Point b) {

        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }

}
