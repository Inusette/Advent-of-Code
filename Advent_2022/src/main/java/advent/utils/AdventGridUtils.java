package advent.utils;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AdventGridUtils {

    private AdventGridUtils() {
        // you shall not instantiate me
    }

    public static List<Point> getNeighbours(Point currentPoint, int maxX, int maxY) {
        List<Point> neighbours = new ArrayList<>();
        if (currentPoint.x > 0) {
            // left neighbour
            neighbours.add(new Point(currentPoint.x - 1, currentPoint.y));
        }
        if (currentPoint.x < maxX - 1) {
            // right Neighbour
            neighbours.add(new Point(currentPoint.x + 1, currentPoint.y));
        }
        if (currentPoint.y > 0) {
            // above Neighbour
            neighbours.add(new Point(currentPoint.x, currentPoint.y - 1));
        }
        if (currentPoint.y < maxY - 1) {
            // below Neighbour
            neighbours.add(new Point(currentPoint.x, currentPoint.y + 1));
        }
        return neighbours;
    }
}
