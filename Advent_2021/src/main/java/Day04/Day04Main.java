package Day04;

import advent.utils.AdventCollectionsUtils;
import advent.utils.AdventFileUtils;

import java.util.ArrayList;
import java.util.List;

public class Day04Main {

    public static void main(String[] args) {
        List<String> inputAsListOfBlocks = getInput();
        for (String block : inputAsListOfBlocks) {
            System.out.println(block);
        }
        List<Integer> drawnNumbers = AdventCollectionsUtils.splitStringIntoIntegerList(inputAsListOfBlocks.get(0), ",");
        inputAsListOfBlocks.remove(0);
        System.out.println("Drawn Numbers: " + drawnNumbers);

        SquidBingo squidBingo = new SquidBingo();
        long bingoResult = squidBingo.playSquidBingoUntilFirst(formatBingoCards(inputAsListOfBlocks), drawnNumbers);
        System.out.println("Bingo result is: " + bingoResult);

        long lastBingoResult = squidBingo.playSquidBingoUntilLast(formatBingoCards(inputAsListOfBlocks), drawnNumbers);
        System.out.println("Last bingo result is: " + lastBingoResult);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readInputChunksSplitByEmptyLines(Day04Main.class, ",");
    }

    protected static List<List<List<Integer>>> formatBingoCards(List<String> inputAsListOfBlocks) {
        List<List<List<Integer>>> bingoCards = new ArrayList<>();
        for (String block : inputAsListOfBlocks) {
            List<List<Integer>> bingoCard = new ArrayList<>();
            String[] splitBlock = block.trim().split(",");
            for (String line : splitBlock) {
                List<Integer> cardLine = AdventCollectionsUtils.splitStringIntoIntegerList(line.trim(), "\\s+");
                bingoCard.add(cardLine);
            }
            bingoCards.add(bingoCard);
        }
        return bingoCards;
    }
}
