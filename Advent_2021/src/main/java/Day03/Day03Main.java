package Day03;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day03Main {

    public static void main(String[] args) {
        List<String> inputAsStringList = getInput();
        System.out.println("This is the input: \n" + inputAsStringList);
        BinaryPowerConsumptionDiagnoser diagnoser = new BinaryPowerConsumptionDiagnoser();

        long powerConsumptionRating = diagnoser.diagnoseBinaryPowerConsumption(inputAsStringList);
        System.out.println("The power consumption rating is: " + powerConsumptionRating);

        long lifeSupportRating = diagnoser.diagnoseLifeSupportRating(inputAsStringList);
        System.out.println("The life support rating is: " + lifeSupportRating);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoStringLines(Day03Main.class);
    }
}
