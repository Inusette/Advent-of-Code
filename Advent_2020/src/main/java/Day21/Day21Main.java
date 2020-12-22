package Day21;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day21Main {

    public static void main(String[] args) {

        List<String> inputLines = getInput();
        inputLines.forEach(System.out::println);

        AllergenIdentifier allergenIdentifier = new AllergenIdentifier(inputLines);
        int allNonAllergicIngredientsOccurrences = allergenIdentifier.countAllNonAllergicIngredientsOccurrences();
        System.out.println("allNonAllergicIngredientsOccurrences = " + allNonAllergicIngredientsOccurrences);

        List<String> dangerousIngredientsList = allergenIdentifier.findDangerousIngredientsList();
        String dangerousIngredients = String.join(",", dangerousIngredientsList);
        System.out.println("dangerousIngredients = " + dangerousIngredients);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day21Main.class);
    }
}
