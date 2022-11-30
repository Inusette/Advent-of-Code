package advent.utils;

import java.util.Arrays;
import java.util.List;

public class AdventCollectionsUtils {

    private AdventCollectionsUtils() {
        // you shall not instantiate me
    }

    public static List<Integer> splitStringIntoIntegerList(String toSplit, String delimiter) {
        return Arrays.stream(toSplit.split(delimiter))
                .map(Integer::parseInt)
                .toList();
    }
}
