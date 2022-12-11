package Day10;

import static advent.utils.AdventFileUtils.readInputIntoStringLines;

import java.util.List;

public class Day10Main {

	public static void main(String[] args) {
		List<String> inputAsLines = getInput();
		CircuitReader circuitReader = new CircuitReader();
		circuitReader.readCircuit(inputAsLines);

		int signalStrengths = circuitReader.sumSinalStrengths();
		System.out.println(signalStrengths);
		circuitReader.printCycleSignals();
	}

	protected static List<String> getInput() {
		return readInputIntoStringLines(Day10Main.class);
	}
}
