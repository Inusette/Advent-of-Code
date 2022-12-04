package advent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdventCollectionsUtils {

    private AdventCollectionsUtils() {
        // you shall not instantiate me
    }

    public static List<Integer> splitStringIntoIntegerList(String toSplit, String delimiter) {
        return Arrays.stream(toSplit.split(delimiter))
                .map(Integer::parseInt)
                .toList();
    }

    public static boolean listWithinList(List<Integer> innerList, List<Integer> outerList) {
        return outerList.contains(innerList.get(0)) && outerList.contains(innerList.get(innerList.size() - 1));
    }

    public static boolean listHasIntersection(List<Integer> firstList, List<Integer> secondList) {
        Set<Integer> areaIntersection = firstList.stream()
                .distinct()
                .filter(secondList::contains)
                .collect(Collectors.toSet());
        return !areaIntersection.isEmpty();
    }
}
