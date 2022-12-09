package Day08;

import java.util.List;

import advent.utils.AdventFileUtils;

public class Day08Main {

	public static void main(String[] args) {
		List<List<Integer>> forestMatrix = getInput();

		TreeVisibilityIdentifier treeVisibilityIdentifier = new TreeVisibilityIdentifier();
		treeVisibilityIdentifier.exploreForest(forestMatrix);
		int numberOfVisibleTrees = treeVisibilityIdentifier.findNumberOfVisibleTrees();
		System.out.println(numberOfVisibleTrees);

		int highestPossibleScenicScore = treeVisibilityIdentifier.findHighestPossibleScenicScore();
		System.out.println(highestPossibleScenicScore);
	}

	protected static List<List<Integer>> getInput() {
		return AdventFileUtils.readInputIntoIntegerMatrix(Day08Main.class);
	}
}
