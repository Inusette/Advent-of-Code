package Day03;

import java.util.List;

/**
 * @author Inna Pirina
 * @since 03.12.2022
 */
public class RucksackPacker {

	private static final int LOWER_CASE_ADJUSTER = 96;

	private static final int UPPER_CASE_ADJUSTER = 38;

	public static int findOddItemsPriorities(List<String> rucksacks) {
		int totalOddItemsPriorities = 0;
		for (String rucksack : rucksacks) {
			String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
			String secondCompartment = rucksack.substring(rucksack.length() / 2);
			for (Character item : firstCompartment.toCharArray()) {
				if (secondCompartment.contains(String.valueOf(item))) {
					totalOddItemsPriorities += calculateItemPriority(item);
					break;
				}
			}
		}
		return totalOddItemsPriorities;
	}

	public static int findBadgePriorities(List<String> rucksacks) {
		int totalBadgePriorities = 0;
		for (int elf = 0; elf < rucksacks.size() - 2; elf += 3) {
			for (Character item : rucksacks.get(elf).toCharArray()) {
				if (rucksacks.get(elf + 1).contains(String.valueOf(item)) &&
						rucksacks.get(elf + 2).contains(String.valueOf(item))) {
					totalBadgePriorities += calculateItemPriority(item);
					break;
				}
			}
		}
		return totalBadgePriorities;
	}

	private static int calculateItemPriority(Character item) {
		return Character.isLowerCase(item)
				? item - LOWER_CASE_ADJUSTER
				: item - UPPER_CASE_ADJUSTER;
	}
}
