package Day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Inna Pirina
 * @since 10.12.2022
 */
public class CircuitReader {

	private final Map<Integer, Integer> cycleSignals;

	public CircuitReader() {
		cycleSignals = new HashMap<>();
	}

	public void readCircuit(List<String> cycles) {
		int currentCycle = 1;
		int currentX = 1;

		for (String cycleOperation : cycles) {
			cycleSignals.put(currentCycle, currentX);
			if (!cycleOperation.equalsIgnoreCase("noop")) {
				String[] operation = cycleOperation.split(" ");
				cycleSignals.put(++currentCycle, currentX);
				currentX += Integer.parseInt(operation[1]);
			}
			currentCycle++;
		}
		cycleSignals.put(currentCycle, currentX);
	}

	public int sumSinalStrengths() {
		return 20 * cycleSignals.get(20) + 60 * cycleSignals.get(60) + 100 * cycleSignals.get(100)
				+ 140 * cycleSignals.get(140) + 180 * cycleSignals.get(180) + 220 * cycleSignals.get(220);
	}

	public void printCycleSignals() {
		int currentX = 0;
		for (int cycle = 1; cycle <= cycleSignals.size(); cycle++) {
			Integer signal = cycleSignals.get(cycle);
			if (currentX == signal - 1 || currentX == signal || currentX == signal + 1) {
				System.out.print("#");
			} else {
				System.out.print(" ");
			}

			if (currentX == 39) {
				System.out.println();
				currentX = 0;
			} else {
				currentX++;
			}
		}
	}
}
