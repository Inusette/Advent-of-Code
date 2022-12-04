package Day04;

import static advent.utils.AdventFileUtils.readInputIntoStringLines;

import java.util.List;

public class Day04Main {

	public static void main(String[] args) {

		List<String> elfPairLines = getInput();
		CleanupPairIdenitifier cleanupPairIdenitifier = new CleanupPairIdenitifier();
		List<ElfPair> elfPairs = cleanupPairIdenitifier.identifyPairAreas(elfPairLines);

		long pairsWithCompletelyOverlappingAreas = cleanupPairIdenitifier.countPairsWithCompletelyOverlappingAreas(elfPairs);
		System.out.println("Number of Elf pairs, where one range fully contain the other: " + pairsWithCompletelyOverlappingAreas);

		long pairsWithIntersections = cleanupPairIdenitifier.countPairsWithIntersections(elfPairs);
		System.out.println("Number of Elf pairs, where the ranges overlap: " + pairsWithIntersections);
	}

	protected static List<String> getInput() {
		return readInputIntoStringLines(Day04Main.class);
	}
}
