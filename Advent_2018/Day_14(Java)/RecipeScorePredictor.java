package Day14_2018;

// Day 14, 2018 Advent of Code
// Full task - https://adventofcode.com/2018/day/14

// Part 1:
// What are the scores of the ten recipes immediately after the number of recipes in your puzzle input?

// Part 2:
// How many recipes appear on the scoreboard to the left of the score sequence in your puzzle input?

public class RecipeScorePredictor {

    private static final int NUMBER_AFTER_TRIALS = 10;
    private static final int TRIALS = 768071;
    private static final int SEQUENCE = 768071;


    public static void main(String[] args) {

        // create a scores object
        ScoreRecord recipeScores = new ScoreRecord();

        // find the scores of the ten recipes immediately after the number of TRIALS
        String finalScores = recipeScores.calculateScoresAfterTrials(TRIALS, NUMBER_AFTER_TRIALS);

        System.out.printf("the scores of the %d recipes immediately after %d of trials is: %s \n",
                NUMBER_AFTER_TRIALS, TRIALS, finalScores);

        // part 2
        ScoreRecord recipeScores2 = new ScoreRecord();

        // find the number of recipes that appear on the scoreboard to the left of the score sequence in the input
        int trialsBeforeSequence = recipeScores2.calculateTrialsBeforeScores(SEQUENCE);

        System.out.printf("the number of trials before the sequence %d is reached is: %d",
                SEQUENCE, trialsBeforeSequence);
    }

}
