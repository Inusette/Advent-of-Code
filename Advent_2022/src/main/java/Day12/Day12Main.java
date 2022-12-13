package Day12;

import java.util.ArrayList;
import java.util.List;

import advent.utils.AdventFileUtils;

public class Day12Main {

	public static void main(String[] args) {

		List<ArrayList<Character>> inputAsMatrix = getInput();
		inputAsMatrix.forEach(System.out::println);

		HillClimbPathFinder hillClimbPathFinder = new HillClimbPathFinder(inputAsMatrix);
		int pathWithLeastSteps = hillClimbPathFinder.findPathWithLeastSteps();
		System.out.println("pathWithLeastSteps = " + pathWithLeastSteps);

		int pathWithLeastStepsFromAnyStart = hillClimbPathFinder.findPathWithLeastStepsFromAnyStart();
		System.out.println(pathWithLeastStepsFromAnyStart);
	}

	protected static List<ArrayList<Character>> getInput() {
		List<List<Character>> inputIntoCharacterMatrix = AdventFileUtils.readInputIntoCharacterMatrix(Day12Main.class);
		return inputIntoCharacterMatrix.stream().map(ArrayList::new).toList();
	}
}
