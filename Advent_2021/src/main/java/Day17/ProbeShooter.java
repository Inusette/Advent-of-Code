package Day17;

import advent.utils.AdventMathUtils;

import java.awt.Point;

public class ProbeShooter {

    private int maxX;

    private int maxY;

    private int minX;

    private int minY;

    public ProbeShooter(String input) {
        assignTargetArea(input);
    }

    public int findTheHighestY(){
        int maxY = Integer.MIN_VALUE;
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= Math.abs(minY); y++) {
                Point curPosition = new Point(0, 0);
                Point curVelocity = new Point(x, y);
                int curMaxY = maxY;

                while(!isPastTarget(curPosition)) {
                    updatePositionByVelocity(curPosition, curVelocity);
                    if (curMaxY < curPosition.y)
                        curMaxY = curPosition.y;
                    updateVelocity(curVelocity);

                    if (isWithinTarget(curPosition))
                        maxY = curMaxY;
                }
            }
        }

        return maxY;
    }

    public int findValidVelocitiesCount(){
        int count = 0;
        for (int x = 0; x <= maxX; x++) {
            for (int y = minY; y <= Math.abs(minY); y++) {
                Point curPosition = new Point(0, 0);
                Point curVelocity = new Point(x, y);

                while(!isPastTarget(curPosition)) {
                    updatePositionByVelocity(curPosition, curVelocity);
                    updateVelocity(curVelocity);

                    if (isWithinTarget(curPosition)) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    private void assignTargetArea(String input) {
        String rightPart = input.trim().split(": ")[1];
        String[] areas = rightPart.replace("x=", "").replace("y=", "").split(", ");
        String[] x = areas[0].split("\\.\\.");
        String[] y = areas[1].split("\\.\\.");
        minX = Integer.parseInt(x[0]);
        maxX = Integer.parseInt(x[1]);
        minY = Integer.parseInt(y[0]);
        maxY = Integer.parseInt(y[1]);
    }

    private boolean isWithinTarget(Point shot) {
        return (AdventMathUtils.isWithinRange(shot.x, minX, maxX) && AdventMathUtils.isWithinRange(shot.y, minY, maxY));
    }

    private boolean isPastTarget(Point shot) {
        return (shot.x > maxX) || (shot.y < minY);
    }

    private void updatePositionByVelocity(Point shot, Point velocity) {
        shot.x += velocity.x;
        shot.y += velocity.y;
    }

    private void updateVelocity(Point velocity) {
        velocity.x += AdventMathUtils.decreaseOrIncreaseByOne(velocity.x, 0);
        velocity.y += -1;
    }

    @Override
    public String toString() {
        return "ProbeShooter{minX=" + minX + ", maxX=" + maxX + ", minY=" + minY + ", maxY=" + maxY + '}';
    }
}
