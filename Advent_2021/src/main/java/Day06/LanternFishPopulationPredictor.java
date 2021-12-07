package Day06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanternFishPopulationPredictor {

    public long predictFishPopulationFrom(List<Integer> initialAgePopulation, int numberOfDays) {
        Map<Integer, Long> ageToFishCount = populateInitialMap(initialAgePopulation);

        int currentDay = 1;
        while (currentDay <= numberOfDays) {
            currentDay++;
            ageToFishCount = updateDaysPopulation(ageToFishCount);
        }
        return ageToFishCount.values().stream().reduce(Long::sum).get();
    }

    private Map<Integer, Long> populateInitialMap(List<Integer> initialAgePopulation) {
        Map<Integer, Long> ageToFishCount = new HashMap<>();
        for (Integer age : initialAgePopulation) {
            ageToFishCount.put(age, ageToFishCount.getOrDefault(age, 0L) + 1);
        }
        return ageToFishCount;
    }

    private Map<Integer, Long> updateDaysPopulation(Map<Integer, Long> ageToFishCount) {
        Map<Integer, Long> currentDayPopulation = new HashMap<>();
        for (Integer age : ageToFishCount.keySet()) {
            Long ageFishCount = ageToFishCount.get(age);
            if (age == 0) {
                currentDayPopulation.put(6, currentDayPopulation.getOrDefault(6, 0L) + ageFishCount);
                currentDayPopulation.put(8, ageFishCount);
            } else {
                currentDayPopulation.put(age - 1, currentDayPopulation.getOrDefault(age - 1, 0L) + ageFishCount);
            }
        }
        return currentDayPopulation;
    }
}
