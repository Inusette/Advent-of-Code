package Day06;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day06Main {

    public static void main(String[] args) {

        List<String> allGroups = getInput();
        allGroups.forEach(System.out::println);

        int sumOfGroupCounts = CustomsDeclarationProcessor.countAllGroupsUniqueAnswers(allGroups);
        System.out.println("The sum of group counts: " + sumOfGroupCounts);

        int sumOfIntersectionAnswers = CustomsDeclarationProcessor.countAllGroupsIntersections(allGroups);
        System.out.println("The sum of questions to which everyone answered yes: " + sumOfIntersectionAnswers);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readInputChunksSplitByEmptyLines(Day06Main.class);
    }
}
