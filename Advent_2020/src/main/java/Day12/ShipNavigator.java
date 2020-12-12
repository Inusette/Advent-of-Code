package Day12;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShipNavigator {

    final String INSTRUCTION_PATTERN = "([A-Z])(\\d+)";

    final List<Direction> rightTurnSequence = List.of(Direction.E, Direction.S, Direction.W, Direction.N);

    final List<Direction> leftTurnSequence = List.of(Direction.E, Direction.N, Direction.W, Direction.S);

    List<Instruction> instructions;

    private final Point shipPosition;

    private final Point waypointPosition;

    Direction currentDirection;

    private enum Direction {
        N, S, E, W, F, R, L
    }

    public ShipNavigator(List<String> inputLines) {
        this.shipPosition = new Point(0, 0);
        this.currentDirection = Direction.E;
        waypointPosition = new Point(10, 1);
        instructions = parseInstructions(inputLines);
    }

    public int computeDistanceFromDirections() {
        instructions.forEach(this::navigateShip);
        return calculateManhattanDistance();
    }

    public int computeDistanceUsingWaypoint() {
        shipPosition.move(0, 0);
        instructions.forEach(this::navigateShipWithWaypoint);
        return calculateManhattanDistance();
    }

    private void navigateShip(Instruction instruction) {

        switch (instruction.direction) {
            case F: {
                navigateShip(new Instruction(currentDirection, instruction.distance));
                break;
            }
            case L:
            case R: {
                currentDirection = turnShip(instruction.direction, instruction.distance);
            }
            default:
                movePointInDifferentDirections(shipPosition, instruction);
        }
    }

    private void navigateShipWithWaypoint(Instruction instruction) {

        switch (instruction.direction) {
            case F: {
                int xDist = instruction.distance * waypointPosition.x;
                int yDist = instruction.distance * waypointPosition.y;
                shipPosition.translate(xDist, yDist);
                break;
            }
            case L:
            case R: {
                turnWaypoint(instruction);
            }
            default:
                movePointInDifferentDirections(waypointPosition, instruction);
        }
    }

    private void movePointInDifferentDirections(Point point, Instruction instruction) {
        switch (instruction.direction) {
            case N: {
                point.translate(0, instruction.distance);
                break;
            }
            case S: {
                point.translate(0, -instruction.distance);
                break;
            }
            case E: {
                point.translate(instruction.distance, 0);
                break;
            }
            case W: {
                point.translate(-instruction.distance, 0);
                break;
            }
        }
    }

    private void turnWaypoint(Instruction instruction) {
        int turnTimes = instruction.distance / 90;
        for (int turnCount = 0; turnCount < turnTimes; turnCount++) {
            if (instruction.direction == Direction.L)
                turnWayPointLeft();
            else
                turnWayPointRight();
        }
    }

    private void turnWayPointRight() {
        waypointPosition.move(waypointPosition.y, -waypointPosition.x);
    }

    private void turnWayPointLeft() {
        waypointPosition.move(-waypointPosition.y, waypointPosition.x);
    }

    private Direction turnShip(Direction direction, int angle) {
        if (direction == Direction.R)
            return findNewDirection(rightTurnSequence, angle);
        else
            return findNewDirection(leftTurnSequence, angle);
    }

    private Direction findNewDirection(List<Direction> turnSequence, int angle) {
        int turnTimes = angle / 90;
        int currentIndex = turnSequence.indexOf(currentDirection);
        int newDirectionIndex = (currentIndex + turnTimes) % 4;
        return turnSequence.get(newDirectionIndex);
    }

    private List<Instruction> parseInstructions(List<String> inputLines) {

        List<Instruction> instructions = new ArrayList<>();
        Pattern instructionPattern = Pattern.compile(INSTRUCTION_PATTERN);
        for (String instructionString : inputLines) {
            Matcher matcher = instructionPattern.matcher(instructionString);
            if (matcher.matches()) {
                Direction givenDirection = Direction.valueOf(matcher.group(1));
                int givenDistance = Integer.parseInt(matcher.group(2));
                instructions.add(new Instruction(givenDirection, givenDistance));
            }
        }
        return instructions;
    }

    private int calculateManhattanDistance() {
        return Math.abs(shipPosition.x) + Math.abs(shipPosition.y);
    }

    private static class Instruction {
        Direction direction;
        int distance;

        public Instruction(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }
    }
}
