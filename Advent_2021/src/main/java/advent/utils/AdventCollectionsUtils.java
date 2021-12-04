package advent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdventCollectionsUtils {

    public static List<Integer> splitStringIntoIntegerList(String toSplit, String delimiter) {
        return Arrays.stream(toSplit.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
