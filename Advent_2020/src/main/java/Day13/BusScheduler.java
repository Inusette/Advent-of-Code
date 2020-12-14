package Day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BusScheduler {

    private final int departTime;

    private final List<Integer> availableBuses;

    public BusScheduler(List<String> inputLines) {
        this.departTime = getDepartTime(inputLines);
        this.availableBuses = parseAvailableBuses(inputLines);
    }

    public long calculateEarliestBusByWaitingTime() {
        List<Bus> earliestBuses = findEarliestBuses();
        Bus earliestBus = earliestBuses.stream().min(Comparator.comparing(Bus::getEarliestTimestamp)).get();
        return earliestBus.busNumber * (earliestBus.earliestTimestamp - departTime);
    }

    private List<Bus> findEarliestBuses() {
        return availableBuses.stream()
                .mapToInt(bus -> bus)
                .filter(bus -> bus > 0)
                .mapToObj(bus -> new Bus(bus, findClosestTimestampOfBus(bus)))
                .collect(Collectors.toList());
    }

    private int getDepartTime(List<String> inputLines) {
        return Integer.parseInt(inputLines.get(0));
    }

    private List<Integer> parseAvailableBuses(List<String> inputLines) {
        List<Integer> allBuses = new ArrayList<>();
        String[] splitInput = inputLines.get(1).split(",");
        for (String bus : splitInput) {
            if (bus.equalsIgnoreCase("x"))
                allBuses.add(0);
            else
                allBuses.add(Integer.parseInt(bus));
        }
        return allBuses;
    }

    private int findClosestTimestampOfBus(int busNumber) {
        int numberOfRoutesBefore = departTime / busNumber;
        return busNumber * (numberOfRoutesBefore + 1);
    }

    private static class Bus {
        int busNumber;
        int earliestTimestamp;

        public Bus (int busNumber, int earliestTimestamp) {
            this.busNumber = busNumber;
            this.earliestTimestamp = earliestTimestamp;
        }

        public int getEarliestTimestamp() {
            return earliestTimestamp;
        }
    }
}
