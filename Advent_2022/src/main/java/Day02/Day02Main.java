package Day02;

import java.util.List;

import advent.utils.AdventFileUtils;

public class Day02Main {

	public static void main(String[] args) {
		RockPaperScissorer rockPaperScissorer = new RockPaperScissorer();
		List<String> stringList = getInput();

		int myTotalScore = rockPaperScissorer.playRockPaperScissors(stringList);
		System.out.println("The score I thought I would get: " + myTotalScore);

		int myPredefinedScore = rockPaperScissorer.playPredefinedRockPaperScissors(stringList);
		System.out.println("The predefined score: " + myPredefinedScore);
	}

	protected static List<String> getInput() {
		return AdventFileUtils.readInputIntoStringLines(Day02Main.class);
	}
}
