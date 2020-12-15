package Day15;

import advent.utils.AdventFileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day15Main {

    public static void main(String[] args) {

        List<String> input = getInput();
        input.forEach(System.out::println);

        List<Long> inputNumbers = Arrays.stream(input.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
        System.out.println("inputNumbers = " + inputNumbers);

        long lastNumber = NumberIterator.iterateNumbers(inputNumbers, 2020);
        System.out.println("lastNumber = " + lastNumber);

        lastNumber = NumberIterator.iterateNumbers(inputNumbers, 30000000);
        System.out.println("lastNumber = " + lastNumber);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day15Main.class);
    }
}
