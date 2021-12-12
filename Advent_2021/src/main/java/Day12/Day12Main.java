package Day12;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day12Main {

    public static void main(String[] args) {
        List<String> inputAsStringLines = getInput();
        PassagePather passagePather = new PassagePather();
        int pathCount = passagePather.identifyPassageConnections(inputAsStringLines);
        System.out.println(pathCount);

        int pathCountWithDouble = passagePather.identifyPassageConnectionsWithDoubleSmallCave();
        System.out.println(pathCountWithDouble);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day12Main.class);
    }
}
