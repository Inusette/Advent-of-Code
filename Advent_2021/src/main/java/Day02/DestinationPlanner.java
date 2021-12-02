package Day02;

import java.util.List;

public class DestinationPlanner {

    private final String FORWARD = "forward";
    private final String DOWN = "down";
    private final String UP = "up";

    public long calculateInitialDestinationPosition(List<String> directionsList) {
        DepthPosition depthPosition = new DepthPosition();
        for (String current : directionsList) {
            String[] direction = current.trim().split(" ");
            switch (direction[0]) {
                case FORWARD:
                    depthPosition.forward += Integer.parseInt(direction[1]);
                    break;
                case DOWN:
                    depthPosition.depth += Integer.parseInt(direction[1]);
                    break;
                case UP:
                    depthPosition.depth -= Integer.parseInt(direction[1]);
                    break;
            }
        }
        return depthPosition.forward * depthPosition.depth;
    }

    public long calculateCorrectDestinationPosition(List<String> directionsList) {
        DepthPosition depthPosition = new DepthPosition();
        int aim = 0;
        for (String current : directionsList) {
            String[] direction = current.trim().split(" ");
            switch (direction[0]) {
                case FORWARD:
                    depthPosition.forward += Integer.parseInt(direction[1]);
                    depthPosition.depth += aim * Integer.parseInt(direction[1]);
                    break;
                case DOWN:
                    aim += Integer.parseInt(direction[1]);
                    break;
                case UP:
                    aim -= Integer.parseInt(direction[1]);
                    break;
            }
        }
        return depthPosition.forward * depthPosition.depth;
    }

    static class DepthPosition {
        int depth;
        int forward;

        DepthPosition() {
            this.depth = 0;
            this.forward = 0;
        }
    }
}
