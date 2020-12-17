package Day17;

import java.util.ArrayList;
import java.util.List;


public class FourDimensionalCubesActivator {

    private final char[][][][] pocketDimension;

    int maxXLength;

    int maxYLength;

    int maxZLength;

    int maxWLength;

    final static int MIN_NEIGHBOURS = 2;

    final static int MAX_NEIGHBOURS = 3;

    final static int CYCLE_COUNT = 6;

    final static char ACTIVE = '#';

    final static char INACTIVE = '.';

    public FourDimensionalCubesActivator(List<String> inputLines) {
        this.maxXLength = inputLines.size() + 12;
        this.maxYLength = inputLines.get(0).length() + 12;
        this.maxZLength = 13;
        this.maxWLength = 13;
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
        for (int w = 0; w < maxWLength; w++) {
            for (int z = 0; z < maxZLength; z++) {
                for (int x = 0; x < maxXLength; x++) {
                    for (int y = 0; y < maxYLength; y++) {
                        if (isCubeActive(x, y, z, w))
                            activeCubeCount++;
                    }
                }
            }
        }
        return activeCubeCount;
    }

    private void arrangeGuestsOnSeatMap(List<Position> positions) {
        for (Position position : positions) {
            if (isCubeActive(position.x, position.y, position.z, position.w))
                pocketDimension[position.x][position.y][position.z][position.w] = INACTIVE;
            else
                pocketDimension[position.x][position.y][position.z][position.w] = ACTIVE;

        }
    }

    private char[][][][] processInitialMap(List<String> inputLines) {

        char[][][][] fourDDimension = new char[maxXLength][maxYLength][maxZLength][maxWLength];
        for (int w = 0; w < maxWLength; w++) {
            for (int z = 0; z < maxZLength; z++) {
                for (int x = 0; x < maxXLength; x++) {
                    for (int y = 0; y < maxYLength; y++) {
                        fourDDimension[x][y][z][w] = INACTIVE;
                    }
                }
            }
        }

        for (int xPosition = 0; xPosition < inputLines.size(); xPosition++) {
            for (int yPosition = 0; yPosition < inputLines.get(0).length(); yPosition++) {
                fourDDimension[xPosition + CYCLE_COUNT][yPosition + CYCLE_COUNT][CYCLE_COUNT][CYCLE_COUNT] = inputLines.get(xPosition).charAt(yPosition);
            }
        }
        return fourDDimension;
    }

    private List<Position> findAllSeatsToBeChanged() {
        List<Position> positionsToBeChanged = new ArrayList<>();

        for (int w = 0; w < maxWLength; w++) {
            for (int z = 0; z < maxZLength; z++) {
                for (int x = 0; x < maxXLength; x++) {
                    for (int y = 0; y < maxYLength; y++) {
                        Position currentPosition = new Position(x, y, z, w);
                        int adjacentActiveCubesCount = countAdjacentActiveCubes(currentPosition);
                        if (!isCubeActive(x, y, z, w)) {
                            if (adjacentActiveCubesCount == MAX_NEIGHBOURS)
                                positionsToBeChanged.add(currentPosition);
                        } else if (isCubeActive(x, y, z, w)) {
                            if (adjacentActiveCubesCount != MIN_NEIGHBOURS && adjacentActiveCubesCount != MAX_NEIGHBOURS) {
                                positionsToBeChanged.add(currentPosition);
                            }
                        }
                    }
                }
            }
        }
        return positionsToBeChanged;
    }

    private int countAdjacentActiveCubes(Position position) {
        int adjacentOccupiedCount = 0;
        for (int adjacentW = position.w - 1; adjacentW <= position.w + 1; adjacentW++) {
            if (adjacentW < 0 || adjacentW >= maxWLength) continue;
            for (int adjacentZ = position.z - 1; adjacentZ <= position.z + 1; adjacentZ++) {
                if (adjacentZ < 0 || adjacentZ >= maxZLength) continue;
                for (int adjacentX = position.x - 1; adjacentX <= position.x + 1; adjacentX++) {
                    if (adjacentX < 0 || adjacentX >= maxXLength) continue;
                    for (int adjacentY = position.y - 1; adjacentY <= position.y + 1; adjacentY++) {
                        if (adjacentY < 0 || adjacentY >= maxYLength) continue;
                        if (adjacentX == position.x && adjacentY == position.y && adjacentZ == position.z && adjacentW == position.w)
                            continue;
                        if (isCubeActive(adjacentX, adjacentY, adjacentZ, adjacentW)) {
                            adjacentOccupiedCount++;
                        }
                    }
                }
            }
        }
        return adjacentOccupiedCount;
    }

    private boolean isCubeActive(int x, int y, int z, int w) {
        return pocketDimension[x][y][z][w] == ACTIVE;
    }

    protected static class Position {
        int x;
        int y;
        int z;
        int w;

        public Position(int xPosition, int yPosition, int zPosition, int wPosition) {
            this.x = xPosition;
            this.y = yPosition;
            this.z = zPosition;
            this.w = wPosition;
        }
    }
}
