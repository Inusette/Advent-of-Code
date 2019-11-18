package Day14_2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ScoreRecord {

    private List<Integer> scores;
    private int firstElf;
    private int secondElf;


    ScoreRecord() {
        this.firstElf = 0;
        this.secondElf = 1;

        this.scores = new ArrayList<>();
        scores.add(3);
        scores.add(7);
    }


    String calculateScoresAfterTrials(int numberOfTrials, int recipesAfterTrials) {

        // make a string builder object to assemble the result string
        StringBuilder recipeScores = new StringBuilder();

        // repeat until the size of the scores list is not more than
        // the number of trials and the number of recipes after those trials
        while (scores.size() < (numberOfTrials + recipesAfterTrials)) {

            // calculate the new scores and update the scores list
            updateScores();

        }

        // find the index of the recipe after the given number of trials, and get the following scores
        for (int recipeAfter = numberOfTrials; recipeAfter < (numberOfTrials + recipesAfterTrials); recipeAfter++) {

            // append the current score to the stringBuilder object
            recipeScores.append(scores.get(recipeAfter));
        }

        return recipeScores.toString();
    }


    int calculateTrialsBeforeScores(int scoreSequence) {

        // get the score sequence as a list of digits
        List<Integer> sequence = splitScores(scoreSequence);

        // fill in the
        while (scores.size() < (sequence.size() + 2)) {
            updateScores();
        }

        // wile the sequence is NOT present in the last part of the scores list, update the scores list
        // the length of the last part of the scores list is the length of sequence + 2
        // indexOfSublist returns a negative if the sublist is not found
        while (Collections.indexOfSubList(scores.subList((scores.size() - (sequence.size() + 2)),
                (scores.size() -1)), sequence) < 0) {

            updateScores();

        }

        return Collections.indexOfSubList(scores, sequence);
    }


    private void updateScores(){

        // find the new score and add it to the scores list
        List<Integer> scoresToAdd = splitScores(scores.get(firstElf) + scores.get(secondElf));

        // update the scores by adding new ones
        scores.addAll(scoresToAdd);

        // update the positions of elves
        firstElf = (firstElf + 1 + scores.get(firstElf)) % scores.size();
        secondElf = (secondElf + 1 + scores.get(secondElf)) % scores.size();

    }


    private List<Integer> splitScores(int scoresToAdd) {

        // create a list of scores
        List<Integer> scoreList = new ArrayList<>();

        // make sure that if the score is 0, it gets added to the list
        if (scoresToAdd == 0){
            scoreList.add(scoresToAdd);
        }

        // make a separate variable for the argument
        int currentScoreDigits = scoresToAdd;

        // to split a number into digits, use the remainder operator of 10
        // because 10012 / 10 = 1001, remainder 2, hence we get the last digit
        // add the last digit to the list first, update the score to be score / 10
        while (currentScoreDigits > 0) {

            scoreList.add(currentScoreDigits % 10);
            currentScoreDigits /= 10;

        }

        // reverse the order of the scores list
        Collections.reverse(scoreList);

        return scoreList;
    }
}
