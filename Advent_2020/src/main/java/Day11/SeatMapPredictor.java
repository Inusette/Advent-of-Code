package Day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeatMapPredictor {

    private Position[][] seatMap;

    private final int seatMapLength;

    private final int seatMapWidth;

    private final List<String> inputLines;

    public SeatMapPredictor(List<String> inputLines) {
        this.inputLines = inputLines;
        this.seatMapLength = inputLines.size();
        this.seatMapWidth = inputLines.get(0).length();
        this.seatMap = new Position[seatMapLength][seatMapWidth];
    }

    public int findOccupiedSeatsWithNoAdjacentNeighbours() {
        seatMap = processInitialMap(inputLines);
        List<Position> seatsToBeChanged = findAllSeatsToBeChanged(4, this::countAdjacentOccupiedSeats);
        while (!seatsToBeChanged.isEmpty()) {
            seatsToBeChanged.forEach(Position::switchOccupied);
            arrangeGuestsOnSeatMap(seatsToBeChanged);
            seatsToBeChanged = findAllSeatsToBeChanged(4, this::countAdjacentOccupiedSeats);
        }
        return countAllOccupiedSeats();
    }

    public int findOccupiedSeatsWithNoVisibleNeighbours() {
        seatMap = processInitialMap(inputLines);
        List<Position> seatsToBeChanged = findAllSeatsToBeChanged(5, this::countVisibleOccupiedSeats);
        while (!seatsToBeChanged.isEmpty()) {
            seatsToBeChanged.forEach(Position::switchOccupied);
            arrangeGuestsOnSeatMap(seatsToBeChanged);
            seatsToBeChanged = findAllSeatsToBeChanged(5, this::countVisibleOccupiedSeats);
        }
        return countAllOccupiedSeats();
    }

    private int countAllOccupiedSeats() {
        List<Character> allSeats = Stream.of(seatMap)
                .flatMap(Stream::of)
                .map(position -> position.seat)
                .filter(seat -> seat.equals('#'))
                .collect(Collectors.toList());
        return allSeats.size();
    }

    private void arrangeGuestsOnSeatMap(List<Position> positions) {
        for (Position currentPosition : positions) {
            seatMap[currentPosition.row][currentPosition.column] = currentPosition;
        }
    }

    private Position[][] processInitialMap(List<String> inputLines) {

        Position[][] seatMap = new Position[seatMapLength][seatMapWidth];
        for (int row = 0; row < seatMapLength; row++) {
            for (int column = 0; column < seatMapWidth; column++) {
                seatMap[row][column] = new Position(row, column, inputLines.get(row).charAt(column));
            }
        }
        return seatMap;
    }

    private List<Position> findAllSeatsToBeChanged(int maxNeighbours, Function<Position, Integer> countNeighbours) {
        List<Position> positionsToBeChanged = new ArrayList<>();
        for (int row = 0; row < seatMapLength; row++) {
            for (int column = 0; column < seatMapWidth; column++) {
                Position currentPosition = seatMap[row][column];
                if (currentPosition.seat == 'L') {
                    if (countNeighbours.apply(currentPosition) == 0)
                        positionsToBeChanged.add(currentPosition);
                } else if (currentPosition.seat == '#') {
                    if (countNeighbours.apply(currentPosition) >= maxNeighbours) {
                        positionsToBeChanged.add(currentPosition);
                    }
                }
            }
        }
        return positionsToBeChanged;
    }

    protected int countAdjacentOccupiedSeats(Position position) {
        int adjacentOccupiedCount = 0;
        for (int adjacentRow = position.row - 1; adjacentRow <= position.row + 1; adjacentRow++) {
            for (int adjacentColumn = position.column - 1; adjacentColumn <= position.column + 1; adjacentColumn++) {
                if (adjacentColumn == position.column && adjacentRow == position.row) {
                    continue;
                }
                if (adjacentRow < 0 || adjacentRow >= seatMapLength) {
                    break;
                }
                if (adjacentColumn < 0 || adjacentColumn >= seatMapWidth) {
                    continue;
                }
                if (isOccupiedSeat(adjacentRow, adjacentColumn)) {
                    adjacentOccupiedCount++;
                }
            }
        }
        return adjacentOccupiedCount;
    }

    protected int countVisibleOccupiedSeats(Position position) {
        int visibleOccupiedSeats = 0;
        for (int nextRow = -1; nextRow <= 1 ; nextRow++) {
            for (int nextColumn = -1; nextColumn <= 1 ; nextColumn++) {
                if (nextRow == 0 && nextColumn == 0) {
                    continue;
                }

                Position positionToCheck = new Position(position.row, position.column, '.');
                while (positionToCheck.seat == '.') {
                    int rowToCheck = positionToCheck.row + nextRow;
                    int columnToCheck = positionToCheck.column + nextColumn;
                    if (rowToCheck < 0 || rowToCheck >= seatMapLength) {
                        break;
                    }
                    if (columnToCheck < 0 || columnToCheck >= seatMapWidth) {
                        break;
                    }
                    positionToCheck = seatMap[rowToCheck][columnToCheck];
                }
                if (positionToCheck.seat == '#') {
                    visibleOccupiedSeats++;
                }
            }
        }
        return visibleOccupiedSeats;
    }

    private boolean isOccupiedSeat(int row, int column) {
        return seatMap[row][column].seat == '#';
    }

    protected class Position {
        int row;
        int column;
        char seat;

        public Position(int row, int column, char seat) {
            this.row = row;
            this.column = column;
            this.seat = seat;
        }

        public void switchOccupied() {
            if (seat == 'L')
                seat = '#';
            else if (seat == '#')
                seat = 'L';
        }
    }
}
