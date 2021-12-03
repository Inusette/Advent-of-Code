package advent.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdventMathUtils {

    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static <T> Set<T> getIntersectionOf(Set<T> firstSet, Set<T> secondSet) {
        Set<T> intersection = new HashSet<>(firstSet);
        intersection.retainAll(secondSet);
        return intersection;
    }

    public static <T> T calculateMode(List<T> elements) {
        return elements.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(elements, o)))).orElse(null);
    }

    public static <T> List<T> findMostFrequentElementsInList(List<T> elements) {
        return elements.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    public static boolean isSumOfElements(long sum, long element1, long element2) {
        return element1 + element2 == sum;
    }
}
