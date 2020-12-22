package Day21;

import advent.utils.AdventFinderUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class AllergenIdentifier {

    private final Map<String, Set<String>> allergenToIngredients;

    private final Map<String, Integer> ingredientsCount;

    private static final String ALLERGENS_PATTERN = "\\(contains .+\\)";

    public AllergenIdentifier(List<String> inputLines) {
        allergenToIngredients = new HashMap<>();
        ingredientsCount = new HashMap<>();
        identifyAllergens(inputLines);
    }

    public int countAllNonAllergicIngredientsOccurrences() {
        int totalOccurrences = 0;
        Set<String> ingredientsWithAllergens = new HashSet<>();
        allergenToIngredients.values().forEach(ingredientsWithAllergens::addAll);

        for (String ingredient : ingredientsCount.keySet()) {
            if (!ingredientsWithAllergens.contains(ingredient)) {
                totalOccurrences += ingredientsCount.get(ingredient);
            }
        }
        return totalOccurrences;
    }

    public List<String> findDangerousIngredientsList() {
        List<String> checkedAllergens = identifyAllergenForEachIngredient();
        Collections.sort(checkedAllergens);
        return checkedAllergens.stream()
                .map(allergen -> allergenToIngredients.get(allergen).iterator().next())
                .collect(Collectors.toList());
    }

    private List<String> identifyAllergenForEachIngredient() {
        List<String> checkedAllergens = new ArrayList<>();
        while (checkedAllergens.size() < allergenToIngredients.size()) {
            for (String allergen : allergenToIngredients.keySet()) {
                Set<String> currentIngredients = allergenToIngredients.get(allergen);
                if (currentIngredients.size() == 1) {
                    if (!checkedAllergens.contains(allergen)) {
                        String currentIngredient = currentIngredients.iterator().next();
                        allergenToIngredients.values()
                                .stream()
                                .filter(ingredients -> ingredients.size() > 1 && ingredients.contains(currentIngredient))
                                .forEach(ingredients -> ingredients.remove(currentIngredient));
                        checkedAllergens.add(allergen);
                    }
                }
            }
        }
        return checkedAllergens;
    }

    private void identifyAllergens(List<String> inputLines) {

        for (String line : inputLines) {
            Matcher allergenMatcher = AdventFinderUtils.createPatternMatcher(ALLERGENS_PATTERN, line);
            String[] allergens;
            if (allergenMatcher.find()) {
                String currentAllergens = allergenMatcher.group();
                allergens = currentAllergens.replace("(contains ", "").replace(")", "").split(",");
                line = allergenMatcher.replaceFirst("");
            } else {
                throw new IllegalArgumentException("No allergens found, vegans rejoice");
            }

            String[] ingredients = line.trim().split(" ");
            processAllergens(allergens, ingredients);
            processIngredients(ingredients);
        }
    }

    private void processAllergens(String[] allergens, String[] ingredients) {
        for (String allergen : allergens) {
            allergen = allergen.trim();
            if (allergenToIngredients.containsKey(allergen)) {
                allergenToIngredients.get(allergen).retainAll(List.of(ingredients));
            } else {
                allergenToIngredients.put(allergen, new HashSet<>(List.of(ingredients)));
            }
        }
    }

    private void processIngredients(String[] ingredients) {
        for (String ingredient : ingredients) {
            if (ingredientsCount.containsKey(ingredient)) {
                ingredientsCount.put(ingredient, ingredientsCount.get(ingredient) + 1);
            } else {
                ingredientsCount.put(ingredient, 1);
            }
        }
    }
}
