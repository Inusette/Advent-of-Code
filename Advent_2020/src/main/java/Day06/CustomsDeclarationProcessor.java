package Day06;

import advent.utils.AdventMathUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

public class CustomsDeclarationProcessor {

    public static int countAllGroupsUniqueAnswers(List<String> groupAnswers) {
        int groupYesCount = 0;
        for (String currentGroup : groupAnswers) {
            currentGroup = currentGroup.replace(" ", "");
            Set<Character> uniqueAnswers = turnAnswersToSet(currentGroup);
            groupYesCount += uniqueAnswers.size();
        }
        return groupYesCount;
    }

    public static int countAllGroupsIntersections(List<String> groupAnswers) {
        int groupIntersectionsCount = 0;
        for (String currentGroup : groupAnswers) {
            String[] peopleInGroup = currentGroup.trim().split(" ");
            Optional<Set<Character>> intersection = Arrays.stream(peopleInGroup)
                    .map(CustomsDeclarationProcessor::turnAnswersToSet)
                    .reduce(AdventMathUtils::getIntersectionOf);
            if (intersection.isPresent()) {
                groupIntersectionsCount += intersection.get().size();
            }
        }
        return groupIntersectionsCount;
    }

    private static Set<Character> turnAnswersToSet(String answers) {
        return answers.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    }
}
