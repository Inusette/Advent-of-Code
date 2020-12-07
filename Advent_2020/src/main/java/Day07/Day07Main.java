package Day07;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day07Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        System.out.println("This is the input: \n" + inputLines);

        int countShinyGoldBags = BagsRulesParser.countShinyGoldBags(inputLines);
        System.out.println("This many bag colors can eventually contain at least one shiny gold bag: " + countShinyGoldBags);

        long bagsWithinShinyGold = BagsRulesParser.countAllBagsWithinShinyGold(inputLines);
        System.out.println("This many individual bags are required inside your single shiny gold bag: " + bagsWithinShinyGold);

    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day07Main.class);
    }
}
