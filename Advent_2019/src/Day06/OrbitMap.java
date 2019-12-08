package Day06;

import java.util.*;

class OrbitMap {

    private Map<String, List<String>> orbitMap;
    private Map<String, String> directOrbits;


    OrbitMap(Map<String, String> directOrbits) {

        this.directOrbits = directOrbits;

        this.orbitMap = mapPlanets();
    }


    private Map<String, List<String>> mapPlanets() {

        Map<String, List<String>> planetToOrbiters = new HashMap<>();

        // go over all the planets in the direct orbits map, and assemble the list of the indirect orbits
        for (String orbiting : directOrbits.keySet()) {

            planetToOrbiters.put(orbiting, findOrbits(orbiting));
        }

        return planetToOrbiters;
    }


    private List<String> findOrbits(String orbiting) {
        // a recursive method that assembles all the indirect orbits of a planet from direct orbits

        List<String> allOrbits = new ArrayList<>();

        // base case - doesn't orbit anything
        if (!directOrbits.containsKey(orbiting)) {

            // return an empty list
            return allOrbits;
        }
        else {
            // add the current direct orbit to the list
            allOrbits.add(directOrbits.get(orbiting));

            // recursively call this method for the planet at the current direct orbit
            allOrbits.addAll(findOrbits(directOrbits.get(orbiting)));
        }
        return allOrbits;
    }


    int countTotalOrbits() {

        int count = 0;

        // go over all the planets in the map and sum the lengths or their orbit lists
        for (String planet : orbitMap.keySet()) {
            count += orbitMap.get(planet).size();
        }
        return count;
    }


    int findShortestPathBetween(String planetA, String planetB) throws Exception{

        // assign a random big value, like the number of planets in the map
        int min = directOrbits.size();

        // make sure the planets are in the map
        if (!orbitMap.containsKey(planetA) || !orbitMap.containsKey(planetB)) {
            throw new Exception("No such planet in the orbit map");
        }

        // find the planets that are common in both lists of orbiting planets
        Set<String> commonPlanets = getCommonPlanets(planetA, planetB);

        // for each common planet, find its indexes in both lists, sum them
        // if the sum is smaller than the current minimum, update the minimum
        for (String planet : commonPlanets) {
           int distance = orbitMap.get(planetA).indexOf(planet) + orbitMap.get(planetB).indexOf(planet);

           if (distance < min) {
               min = distance;
           }
        }
        return min;
    }


    private Set<String> getCommonPlanets(String planetA, String planetB){

        // turn both lists of orbiting planets into sets
        Set<String> commonPlanets = new HashSet<>(orbitMap.get(planetA));
        Set<String> orbitsOfPlanetB = new HashSet<>(orbitMap.get(planetB));

        // find the intersections
        commonPlanets.retainAll(orbitsOfPlanetB);

        return commonPlanets;
    }
}
