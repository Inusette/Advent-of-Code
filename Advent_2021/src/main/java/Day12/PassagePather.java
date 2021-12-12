package Day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PassagePather {

    private final Map<String, Set<String>> passageConnections;

    public PassagePather() {
        passageConnections = new HashMap<>();
    }

    public int identifyPassageConnections(List<String> passages) {
        mapPassageConnections(passages);
        List<List<String>> passageways = findPassageway(List.of("start"), "start", false, "");
        return passageways.size();
    }

    public int identifyPassageConnectionsWithDoubleSmallCave() {
        Set<List<String>> passageways = new HashSet<>();
        for (String cave : passageConnections.keySet()) {
            if (!isBigCave(cave) && !"end".equals(cave) && !"start".equals(cave)) {
                passageways.addAll(findPassageway(List.of("start"), "start", true, cave));
            }
        }
        return passageways.size();
    }

    private void mapPassageConnections(List<String> passages) {
        passages.stream().map(line -> line.trim().split("-")).forEach(splitLine -> {
            Set<String> connection = passageConnections.getOrDefault(splitLine[0], new HashSet<>());
            connection.add(splitLine[1]);
            passageConnections.put(splitLine[0], connection);

            Set<String> backConnection = passageConnections.getOrDefault(splitLine[1], new HashSet<>());
            backConnection.add(splitLine[0]);
            passageConnections.put(splitLine[1], backConnection);
        });
    }

    private List<List<String>> findPassageway(List<String> visitedCaves, String cave, boolean withSmallCaveTime, String smallCave) {
        Set<String> passages = passageConnections.get(cave);
        List<List<String>> allPaths = new ArrayList<>();
        for (String nextCave : passages) {
            ArrayList<String> path = new ArrayList<>(visitedCaves);
            path.add(nextCave);
            if ("end".equals(nextCave)) {
                allPaths.add(path);
            } else if (isValidPassageway(nextCave, visitedCaves, withSmallCaveTime, smallCave)) {
                allPaths.addAll(findPassageway(path, nextCave, withSmallCaveTime, smallCave));
            }
        }
        return allPaths;
    }

    private boolean isBigCave(String cave) {
        char[] chars = cave.toCharArray();
        for (char ch : chars) {
            if (Character.isLowerCase(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPassageway(String nextCave, List<String> visitedCaves, boolean withSmallCaveTime, String smallCave) {
        if (withSmallCaveTime) {
            return isBigCave(nextCave) || !visitedCaves.contains(nextCave) || (smallCave.equals(nextCave) && Collections.frequency(visitedCaves, smallCave) < 2);
        } else {
            return isBigCave(nextCave) || !visitedCaves.contains(nextCave);
        }
    }
}
