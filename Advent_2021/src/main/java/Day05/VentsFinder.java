package Day05;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentsFinder {

    public long findOverlappingVents(List<String> ventSegments) {
        return findOverlappingVents(ventSegments, false);
    }

    public long findOverlappingVentsWithDiagonalSegments(List<String> ventSegments) {
        return findOverlappingVents(ventSegments, true);
    }

    private long findOverlappingVents(List<String> ventSegments, boolean includeDiagonalSegments) {
        List<Point> allVentPoints = VentSegmentParser.getAllPoints(ventSegments, includeDiagonalSegments);
        Map<Point, Integer> ventMap = new HashMap<>();
        for (Point currentPoint : allVentPoints) {
            ventMap.put(currentPoint, ventMap.getOrDefault(currentPoint, 0) + 1);
        }
        return ventMap.entrySet().stream().filter(entry -> entry.getValue() >= 2).count();
    }
}
