package Day13;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day13Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        BusScheduler busScheduler = new BusScheduler(inputLines);
        long busByWaitingTime = busScheduler.calculateEarliestBusByWaitingTime();
        System.out.println("busByWaitingTime = " + busByWaitingTime);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day13Main.class);
    }
}
