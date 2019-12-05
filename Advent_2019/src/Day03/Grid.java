package Day03;


import java.awt.*;
import java.util.*;
import java.util.List;

class Grid {

    private Map<Integer, List<Point>> grid;


    Grid(List<List<String>> instructionsForWires) {

        grid = new HashMap<>();

        // assemble the grid using the instructions
        makeGrid(instructionsForWires);
    }


    private void makeGrid (List<List<String>> instructionsForWires) {

        // for each wire (line) follow instructions and add the path to the grid
        for (int i = 0; i < instructionsForWires.size(); i++) {

            grid.put((i + 1), assemblePath(instructionsForWires.get(i)));
        }
    }


    private List<Point> assemblePath(List<String> wirePath) {

        // Instantiate the list with the starting point
        List<Point> wire = new ArrayList<>();
        addPoint(wire, 0, 0);

        // go over all instructions
        for (String instruction : wirePath) {

            // extract the target index fro the instruction
            int steps = Integer.parseInt(instruction.substring(1));

            // depending on the direction, apply the appropriate method
            if (instruction.startsWith("R")) {
                moveRight(wire, steps);
            }

            else if (instruction.startsWith("L")) {
                moveLeft(wire, steps);
            }

            else if (instruction.startsWith("U")) {
                moveUp(wire, steps);
            }

            else if (instruction.startsWith("D")) {
                moveDown(wire, steps);
            }
        }
        return wire;
    }


    private void moveRight(List<Point> wire, int steps) {

        int y = wire.get(wire.size() - 1).y;
        int x = wire.get(wire.size() - 1).x;

        for (int i = 1; i <= steps; i++) {

            // y stays the same, x changes
            addPoint(wire, (x + i), y);
        }
    }


    private void moveLeft(List<Point> wire, int steps) {

        int y = wire.get(wire.size() - 1).y;
        int x = wire.get(wire.size() - 1).x;

        for (int i = 1; i <= steps; i++) {

            // y stays the same, x changes
            addPoint(wire, (x - i), y);
        }
    }


    private void moveUp(List<Point> wire, int steps) {

        int y = wire.get(wire.size() - 1).y;
        int x = wire.get(wire.size() - 1).x;

        for (int i = 1; i <= steps; i++) {

            // x stays the same, y changes
            addPoint(wire, x, (y + i));
        }
    }


    private void moveDown(List<Point> wire, int steps) {

        int y = wire.get(wire.size() - 1).y;
        int x = wire.get(wire.size() - 1).x;

        for (int i = 1; i <= steps; i++) {

            // x stays the same, y changes
            addPoint(wire, x, (y - i));
        }
    }


    private void addPoint(List<Point> wire, int x, int y) {

        wire.add(new Point(x, y));
    }


    private Set<Point> findIntersections() {

        Set<Point> intersections = new HashSet<>();

        // go over each wire path
        for (Integer wire : grid.keySet()) {

            // assign all points of the first wire to intersections
            if (intersections.isEmpty()) {
                intersections.addAll(grid.get(wire));
            }
            // use SETS to find the point objects that are common among wires
            else {
                intersections.retainAll(new HashSet<>(grid.get(wire)));
            }
        }

        // make sure to remove the starting point from intersections
        intersections.remove(new Point(0,0));

        return intersections;
    }


    int findClosestIntersectionBySteps() {
        // closest intersection to the starting point

        Set<Point> intersections = findIntersections();

        // set the first minimum to the total lengths of all wire paths
        int min = getPath(1).size() * getWires().size();

        // go over each intersection
        for (Point intersection : intersections) {

            int totalDistance = 0;

            // find the distance of each wire to this intersection
            for (int wire : grid.keySet()) {

                totalDistance += getPath(wire).indexOf(intersection);
            }

            // if the total distance is smaller than the current minimum, update it
            if (totalDistance < min) {
                min = totalDistance;
            }
        }
        return min;
    }


    int findClosestIntersectionByManhattanDistance() {
        // closest intersection to the starting point
        return PointUtils.findClosestPointByManhattanDistance(new Point(0,0),
                new ArrayList<>(findIntersections()));
    }


    private List<Point> getPath(int wire) {

        if (grid.containsKey(wire)) {
            return grid.get(wire);
        }
        else {
            return new ArrayList<>();
        }
    }


    private Set<Integer> getWires() {
        return grid.keySet();
    }
}