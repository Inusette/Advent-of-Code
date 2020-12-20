package Day18;

import advent.utils.AdventFinderUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;

/**
 * A collab with @slavov-vili <3
 */
public class HomeworkCalculator {

    private static final String PLUS = "+";

    private static final String MULTIPLY = "*";

    private static final String BRACKETS_EXPRESSION = "\\([^\\(\\)]+\\)";  // lol - brackets that don't have any brackets inside

    private static final String SUM_EXPRESSION = "\\d+ \\+ \\d+";

    public static long evaluateAllLines(List<String> inputLines) {
        return inputLines.stream().mapToLong(line -> evaluateLine(line, HomeworkCalculator::evaluateExpressionNoBrackets)).sum();
    }

    public static long evaluateLinesSumFirst(List<String> inputLines) {
        return inputLines.stream().mapToLong(line -> evaluateLine(line, HomeworkCalculator::evaluateExpressionSumFirst)).sum();
    }

    private static long evaluateLine(String line, Function<String, Long> evaluateExpression) {
        while (line.contains("(")) {
            Optional<String> bracketsExpression = AdventFinderUtils.getMatch(line, BRACKETS_EXPRESSION);
            if (bracketsExpression.isPresent()) {
                String expressionInside = removeBrackets(bracketsExpression.get());
                String expressionResult = String.valueOf(evaluateExpression.apply(expressionInside));
                line = line.replace(bracketsExpression.get(), expressionResult);
            }
        }
        return evaluateExpression.apply(line);
    }

    private static long evaluateExpressionSumFirst(String expression) {
        while (expression.contains(PLUS)) {
            Optional<String> sumExpression = AdventFinderUtils.getMatch(expression, SUM_EXPRESSION);
            Matcher sumMatcher = AdventFinderUtils.createPatternMatcher(SUM_EXPRESSION, expression);
            if (sumMatcher.find()) {
                String expressionResult = String.valueOf(evaluateExpressionNoBrackets(sumExpression.get()));
                expression = sumMatcher.replaceFirst(expressionResult);
            }
        }
        return evaluateExpressionNoBrackets(expression);
    }

    private static long evaluateExpressionNoBrackets(String expression) {
        String[] elements = expression.split(" ");
        long result = Long.parseLong(elements[0]);
        if (elements.length == 1) {
            return result;
        }

        String currentOperator = "";
        for (int elementIndex = 1; elementIndex < elements.length; elementIndex++) {
            String currentElement = elements[elementIndex];
            if (currentElement.equals(PLUS) || currentElement.equals(MULTIPLY)) {
                currentOperator = currentElement;
            } else {
                result = applyOperation(result, currentOperator, Long.parseLong(currentElement));
            }
        }
        return result;
    }

    private static long applyOperation(long firstElement, String operator, long secondElement) {
        if (PLUS.equals(operator))
            return firstElement + secondElement;
        else
            return firstElement * secondElement;
    }

    private static String removeBrackets(String withBrackets) {
        return withBrackets.replace("(", "").replace(")", "");
    }
}
