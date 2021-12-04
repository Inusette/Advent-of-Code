package Day04;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SquidBingo {

    public long playSquidBingoUntilFirst(List<List<List<Integer>>> bingoCards, List<Integer> directions) {
        int lastCalledNumber;
        for (int currentDrawnNumber : directions) {
            lastCalledNumber = currentDrawnNumber;
            for (List<List<Integer>> bingoCard : bingoCards) {
                boolean isBingo = playCard(bingoCard, currentDrawnNumber);
                if (isBingo) {
                    System.out.println("BINGO!");
                    return lastCalledNumber * sumUpNonMarkedNumbers(bingoCard);
                }
            }
        }
        return 0;
    }

    public long playSquidBingoUntilLast(List<List<List<Integer>>> bingoCards, List<Integer> drawnNumbers) {
        int lastCalledNumber;
        long lastBingo = 0;
        Set<Integer> cardsWithBingo = new HashSet<>();
        for (int currentDrawnNumber : drawnNumbers) {
            lastCalledNumber = currentDrawnNumber;
            for (int cardIndex = 0; cardIndex < bingoCards.size(); cardIndex++) {
                if (!cardsWithBingo.contains(cardIndex)) {
                    boolean isBingo = playCard(bingoCards.get(cardIndex), currentDrawnNumber);
                    if (isBingo) {
                        cardsWithBingo.add(cardIndex);
                        lastBingo = lastCalledNumber * sumUpNonMarkedNumbers(bingoCards.get(cardIndex));
                    }
                }
            }
        }
        System.out.println("SQUID BINGO!");
        return lastBingo;
    }

    private boolean playCard(List<List<Integer>> bingoCard, int currentNumber) {
        for (int row = 0; row < bingoCard.size(); row++) {
            for (int column = 0; column < bingoCard.get(0).size(); column++) {
                if (bingoCard.get(row).get(column) == currentNumber) {
                    // mark that number
                    bingoCard.get(row).set(column, -1);
                    // check the card
                    if (isBingoCard(bingoCard, row, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isBingoCard(List<List<Integer>> bingoCard, int row, int column) {
        if (rowHasBingo(bingoCard, row)) {
            return true;
        } else {
            return columnHasBingo(bingoCard, column);
        }
    }

    private boolean rowHasBingo(List<List<Integer>> bingoCard, int row) {
        return bingoCard.get(row).stream().allMatch(num -> num < 0);
    }

    private boolean columnHasBingo(List<List<Integer>> bingoCard, int column) {
        return bingoCard.stream().map(row -> row.get(column)).allMatch(num -> num < 0);
    }

    private long sumUpNonMarkedNumbers(List<List<Integer>> bingoCard) {
        return bingoCard.stream().flatMap(row -> row.stream().filter(num -> num >= 0)).reduce(Integer::sum).get();
    }
}
