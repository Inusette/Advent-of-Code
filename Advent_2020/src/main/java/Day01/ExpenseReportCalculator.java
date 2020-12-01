package Day01;

import java.util.List;

public class ExpenseReportCalculator {

    public static int calculateA(List<Integer> inputNumbers) {

        int result = 0;
        for (int firstNumber = 0; firstNumber < inputNumbers.size() - 1; firstNumber++) {
            for (int secondNumber = 1; secondNumber < inputNumbers.size(); secondNumber++) {
                if (inputNumbers.get(firstNumber) + inputNumbers.get(secondNumber) == 2020) {
                    result = inputNumbers.get(firstNumber) * inputNumbers.get(secondNumber);
                    break;
                }
            }
        }
        return result;
    }

    public static int calculateB(List<Integer> inputNumbers) {

        int result = 0;
        for (int firstNumber = 0; firstNumber < inputNumbers.size() - 2; firstNumber++) {
            for (int secondNumber = 1; secondNumber < inputNumbers.size() - 1; secondNumber++) {
                for (int thirdNumber = 2; thirdNumber < inputNumbers.size(); thirdNumber++) {
                    if ((inputNumbers.get(firstNumber) + inputNumbers.get(secondNumber) + inputNumbers.get(thirdNumber)) == 2020) {
                        result = inputNumbers.get(firstNumber) * inputNumbers.get(secondNumber) * inputNumbers.get(thirdNumber);
                        break;
                    }
                }
            }
        }
        return result;
    }
}
