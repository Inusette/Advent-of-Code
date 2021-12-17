package Day17;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day17Main {

    public static void main(String[] args) {
        String targetArea = getInput().get(0);
        System.out.println(targetArea);

        ProbeShooter probeShooter = new ProbeShooter(targetArea);
        System.out.println(probeShooter);
        System.out.println(probeShooter.findTheHighestY());
        System.out.println(probeShooter.findValidVelocitiesCount());
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day17Main.class);
    }
}
