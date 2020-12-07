package Day07;

import advent.utils.AdventFinderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BagsRulesParser {

    public static int countShinyGoldBags(List<String> inputLines) {

        Map<String, Set<String>> bagsRules = getBagsWithinBags(inputLines);
        Set<String> bagsContainingShinyGold = findOuterBagsOfColor(bagsRules, "shiny gold", new HashSet<>());
        return bagsContainingShinyGold.size();
    }

    public static long countAllBagsWithinShinyGold(List<String> inputLines) {
        Map<String, Set<String>> allBags = getBagsAndTheirContents(inputLines);
        return countAllBagsWithinColor(allBags, "shiny gold") - 1;
    }

    private static Set<String> findOuterBagsOfColor(Map<String, Set<String>> bagsWithinBags, String color, Set<String> bagsContainingColor) {

        if (!bagsWithinBags.containsKey(color)) {
            return bagsContainingColor;
        }

        for (String outerBag : bagsWithinBags.get(color)) {
            bagsContainingColor.add(outerBag);
            bagsContainingColor.addAll(findOuterBagsOfColor(bagsWithinBags, outerBag, bagsContainingColor));
        }
        return bagsContainingColor;
    }

    private static long countAllBagsWithinColor(Map<String, Set<String>> allBags, String color) {

        if (allBags.get(color).isEmpty()) {
            return 1;
        }

        int colorCount = 1;
        for (String innerBag : allBags.get(color)) {
            String innerColor = clearOfNumbers(innerBag);
            Optional<String> numberOfColor = AdventFinderUtils.getMatch(innerBag, "\\d");
            if (numberOfColor.isEmpty()) {
                throw new IllegalArgumentException("Bag has no quantity!" + innerBag);
            }
            colorCount += Integer.parseInt(numberOfColor.get()) * countAllBagsWithinColor(allBags, innerColor);
        }
        return colorCount;
    }

    private static Map<String, Set<String>> getBagsWithinBags(List<String> inputLines) {

        Map<String, Set<String>> bagsWithinBags = new HashMap<>();

        for (String line : inputLines) {
            BagRule currentRule = parseRule(line);

            for (String bagWithin : currentRule.bagsInside) {
                bagWithin = clearOfNumbers(bagWithin);
                if (bagsWithinBags.containsKey(bagWithin)) {
                    bagsWithinBags.get(bagWithin).add(currentRule.bagColor);
                }
                else {
                    bagsWithinBags.put(bagWithin, new HashSet<>(Collections.singletonList(currentRule.bagColor)));
                }
            }
        }
        return bagsWithinBags;
    }

    private static Map<String, Set<String>> getBagsAndTheirContents(List<String> inputLines) {

        Map<String, Set<String>> allBags = new HashMap<>();
        for (String line : inputLines) {
            BagRule currentRule = parseRule(line);
            allBags.put(currentRule.bagColor, new HashSet<>(currentRule.bagsInside));
        }
        return allBags;
    }

    private static BagRule parseRule(String line) {

        String[] splitLine = line.split(" bags contain ");
        BagRule bagRule = new BagRule();
        bagRule.bagColor = splitLine[0];

        List<String> bagsInsideBags;

        String bags = clearOfBagsAndDots(splitLine[1]);
        if (bags.contains(",")) {
            String[] insideBags = bags.split(",");
            bagsInsideBags = Arrays.stream(insideBags).map(String::trim).collect(Collectors.toList());
        }
        else if (bags.contains("no other")) {
            bagsInsideBags = new ArrayList<>();
        }
        else {
            bagsInsideBags = Collections.singletonList(bags);
        }
        bagRule.bagsInside = bagsInsideBags;

        return bagRule;
    }

    private static String clearOfNumbers(String bagColor) {
        String color = "";
        if (AdventFinderUtils.containsPattern(bagColor, "\\d"))
            color = bagColor.replaceAll("\\d", "");
        return color.trim();
    }

    private static String clearOfBagsAndDots(String bagContent) {
        String bags = bagContent.replaceAll("bags", "");
        bags = bags.replaceAll("bag", "").trim();
        return bags.replace(".", "");
    }

    private static class BagRule {
        String bagColor;
        List<String> bagsInside;
    }
}
