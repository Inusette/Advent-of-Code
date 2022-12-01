package Day01;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import advent.utils.AdventCollectionsUtils;
import advent.utils.AdventFileUtils;

/**
 * Task1: Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
 * <p>
 * Task 2: Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
 */
public class Day01Main {

	public static void main(String[] args) {
		System.out.println("\nHere we go!");

		List<String> inputChunks = getInput();
		List<List<Integer>> snackCalories = getSnackCalories(inputChunks);

		int snackBundleWithTheMostEnergy = SnackEnergyCalculator.identifySnackBundleWithTheMostEnergy(snackCalories);
		System.out.println("The snack bundle with the most energy: " + snackBundleWithTheMostEnergy);

		int topThreeSnackBundles = SnackEnergyCalculator.sumTopThreeSnackBundles(snackCalories);
		System.out.println("The sum of the top three snack bundles with the most energy: " + topThreeSnackBundles);
	}

	protected static List<String> getInput() {
		return AdventFileUtils.readInputChunksSplitByEmptyLines(Day01Main.class, null);
	}

	protected static List<List<Integer>> getSnackCalories(List<String> inputChunks) {
		return inputChunks.stream()
				.map(chunk -> AdventCollectionsUtils.splitStringIntoIntegerList(chunk, " "))
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
