package Day06;

// Full task at https://adventofcode.com/2019/day/6

// Part 1:
// What is the total number of direct and indirect orbits in your map data?

// Part 2:
// What is the minimum number of orbital transfers required to move
// from the object YOU are orbiting to the object SAN is orbiting?
// (Between the objects they are orbiting - not between YOU and SAN.)


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbitTracker {

    private static final String ORBIT_SIGN = "\\)";
    private static final String INPUT = "../Advent_2019/src/Day06/input.txt";
    private static final String YOU = "YOU";
    private static final String SANTA = "SAN";

    public static void main(String[] args) {

        // read the input into a list of strings
        List<String> input = Utils.InputReader.readFileToList(INPUT);

        // assemble a map of all direct orbits
        Map<String, String> directOrbits = assembleDirectOrbits(input);

        OrbitMap orbitMap = new OrbitMap(directOrbits);

        // Part 1
        System.out.println(orbitMap.countTotalOrbits());

        // Part 2
        try {
            System.out.println(orbitMap.findShortestPathBetween(YOU, SANTA));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static Map<String, String> assembleDirectOrbits(List<String> instructions) {

        // maps the orbitING planet (key) to the orbitED planet (value)
        Map<String, String> directOrbits = new HashMap<>();

        for (String instruction : instructions) {

            String[] planets = instruction.split(ORBIT_SIGN);
            directOrbits.put(planets[1], planets[0]);
        }
        return directOrbits;
    }
}
