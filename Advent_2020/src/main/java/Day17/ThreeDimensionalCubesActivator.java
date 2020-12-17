package Day17;

import java.util.ArrayList;
import java.util.List;


public class ThreeDimensionalCubesActivator {

    private final char[][][] pocketDimension;

    int maxXLength;

    int maxYLength;

    int maxZLength;

    final static int MIN_NEIGHBOURS = 2;

    final static int MAX_NEIGHBOURS = 3;

    final static int CYCLE_COUNT = 6;

    final static char ACTIVE = '#';

    final static char INACTIVE = '.';

    public ThreeDimensionalCubesActivator(List<String> inputLines) {
        this.maxXLength = inputLines.size() + 12;
        this.maxYLength = inputLines.get(0).length() + 12;
        this.maxZLength = 13;
        this.pocketDimension = processInitialMap(inputLines);
    }

    public int countAllActiveCubes() {
        int cycle = 0;
        while (cycle < CYCLE_COUNT) {
            List<Position> seatsToBeChanged = findAllSeatsToBeChanged();
            arrangeGuestsOnSeatMap(seatsToBeChanged);
            cycle++;
        }
        return countAllOccupiedSeats();
    }

    private int countAllOccupiedSeats() {
        int activeCubeCount = 0;
        for (int z = 0; z < maxZLength; z++) {
            for (int x = 0; x < maxXLength; x++) {
                for (int y = 0; y < maxYLength; y++) {
                    if (isCubeActive(x, y, z))
                        activeCubeCount++;
                }
            }
        }
        return activeCubeCount;
    }

    private void arrangeGuestsOnSeatMap(List<Position> positions) {
        for (Position currentPosition : positions) {
            if (isCubeActive(currentPosition.xPosition, currentPosition.yPosition, currentPosition.zPosition))
                pocketDimension[currentPosition.xPosition][currentPosition.yPosition][currentPosition.zPosition] = INACTIVE;
            else
                pocketDimension[currentPosition.xPosition][currentPosition.yPosition][currentPosition.zPosition] = ACTIVE;

        }
    }

    private char[][][] processInitialMap(List<String> inputLines) {

        char[][][] threeDDimension = new char[maxXLength][maxYLength][maxZLength];
        for (int z = 0; z < maxZLength; z++) {
            for (int x = 0; x < maxXLength; x++) {
                for (int y = 0; y < maxYLength; y++) {
                    threeDDimension[x][y][z] = INACTIVE;
                }
            }
        }

        for (int xPosition = 0; xPosition < inputLines.size(); xPosition++) {
            for (int yPosition = 0; yPosition < inputLines.get(0).length(); yPosition++) {
                threeDDimension[xPosition + CYCLE_COUNT][yPosition + CYCLE_COUNT][CYCLE_COUNT] = inputLines.get(xPosition).charAt(yPosition);
            }
        }
        return threeDDimension;
    }

    private List<Position> findAllSeatsToBeChanged() {
        List<Position> positionsToBeChanged = new ArrayList<>();

        for (int z = 0; z < maxZLength; z++) {
            for (int x = 0; x < maxXLength; x++) {
                for (int y = 0; y < maxYLength; y++) {
                    Position currentPosition = new Position(x, y, z);
                    int adjacentActiveCubesCount = countAdjacentActiveCubes(currentPosition);
                    if (!isCubeActive(x, y, z)) {
                        if (adjacentActiveCubesCount == MAX_NEIGHBOURS)
                            positionsToBeChanged.add(currentPosition);
                    } else if (isCubeActive(x, y, z)) {
                        if (adjacentActiveCubesCount != MIN_NEIGHBOURS && adjacentActiveCubesCount != MAX_NEIGHBOURS) {
                            positionsToBeChanged.add(currentPosition);
                        }
                    }
                }
            }
        }
        return positionsToBeChanged;
    }

    private int countAdjacentActiveCubes(Position position) {
        int adjacentOccupiedCount = 0;
        for (int adjacentZ = position.zPosition - 1; adjacentZ <= position.zPosition + 1; adjacentZ++) {
            if (adjacentZ < 0 || adjacentZ >= maxZLength) {
                continue;
            }
            for (int adjacentX = position.xPosition - 1; adjacentX <= position.xPosition + 1; adjacentX++) {
                if (adjacentX < 0 || adjacentX >= maxXLength) {
                    continue;
                }
                for (int adjacentY = position.yPosition - 1; adjacentY <= position.yPosition + 1; adjacentY++) {
                    if (adjacentY < 0 || adjacentY >= maxYLength) {
                        continue;
                    }
                    if (adjacentX == position.xPosition && adjacentY == position.yPosition && adjacentZ == position.zPosition) {
                        continue;
                    }
                    if (isCubeActive(adjacentX, adjacentY, adjacentZ)) {
                        adjacentOccupiedCount++;
                    }
                }
            }
        }
        return adjacentOccupiedCount;
    }

    private boolean isCubeActive(int x, int y, int z) {
        return pocketDimension[x][y][z] == ACTIVE;
    }

    protected static class Position {
        int xPosition;
        int yPosition;
        int zPosition;

        public Position(int xPosition, int yPosition, int zPosition) {
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.zPosition = zPosition;
        }
    }
}
