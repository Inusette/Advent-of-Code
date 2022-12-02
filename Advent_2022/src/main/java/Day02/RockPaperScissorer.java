package Day02;

import java.util.List;
import java.util.Map;

/**
 * @author Inna Pirina
 * @since 02.12.2022
 */
public class RockPaperScissorer {

	private final Map<String, Integer> elfScores = Map.of("A", 1, "B", 2, "C", 3);
	private final Map<String, Integer> myScores = Map.of("X", 1, "Y", 2, "Z", 3);

	private final Map<String, Integer> winningStrategy = Map.of("A", 2, "B", 3, "C", 1);
	private final Map<String, Integer> losingStrategy = Map.of("A", 3, "B", 1, "C", 2);

	private static final int WIN_SCORE = 6;
	private static final int DRAW_SCORE = 3;

	private static final String WIN = "Z";
	private static final String DRAW = "Y";
	private static final String LOSE = "X";

	public int playRockPaperScissors(List<String> rounds) {
		int myTotalScore = 0;
		for (String round : rounds) {
			String[] deals = round.trim().split(" ");
			int elf = elfScores.get(deals[0]);
			int me = myScores.get(deals[1]);
			myTotalScore += me;

			int difference = Math.abs(elf - me);
			if ((me > elf && difference == 1) || (elf > me && difference == 2)) {
				myTotalScore += WIN_SCORE;
			} else if (me == elf) {
				myTotalScore += DRAW_SCORE;
			}
		}
		return myTotalScore;
	}

	public int playPredefinedRockPaperScissors(List<String> rounds) {
		int myTotalScore = 0;
		for (String round : rounds) {
			String[] deals = round.trim().split(" ");
			String elf = deals[0];
			String me = deals[1];

			switch (me) {
			case WIN -> myTotalScore += WIN_SCORE + winningStrategy.get(elf);
			case DRAW -> myTotalScore += DRAW_SCORE + elfScores.get(elf);
			case LOSE -> myTotalScore += losingStrategy.get(elf);
			default -> System.out.println("OH No!");
			}
		}
		return myTotalScore;
	}
}
