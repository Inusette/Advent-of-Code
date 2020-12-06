package advent.utils;

import java.util.HashSet;
import java.util.Set;

public class AdventMathUtils {

    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static <T> Set<T> getIntersectionOf(Set<T> firstSet, Set<T> secondSet) {
        Set<T> intersection = new HashSet<>(firstSet);
        intersection.retainAll(secondSet);
        return intersection;
    }
}
