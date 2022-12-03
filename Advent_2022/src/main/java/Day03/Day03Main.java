package Day03;

import java.util.List;

import advent.utils.AdventFileUtils;

public class Day03Main {

	public static void main(String[] args) {
		List<String> rucksacklines = getInput();

		int oddItemsPriorities = RucksackPacker.findOddItemsPriorities(rucksacklines);
		System.out.println("The total double items pritorities: " + oddItemsPriorities);

		int badgePriorities = RucksackPacker.findBadgePriorities(rucksacklines);
		System.out.println("The total badge pritorities: " + badgePriorities);
	}

	protected static List<String> getInput() {
		return AdventFileUtils.readInputIntoStringLines(Day03Main.class);
	}
}
