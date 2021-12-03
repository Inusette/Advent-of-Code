package Day03;

import advent.utils.AdventMathUtils;

import java.util.ArrayList;
import java.util.List;

public class BinaryPowerConsumptionDiagnoser {

    public long diagnoseBinaryPowerConsumption(List<String> binaryReportEntries) {
        int reportSize = binaryReportEntries.get(0).length();
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int bitPosition = 0; bitPosition < reportSize; bitPosition++) {
            char mostFrequentAtPos = getMostFrequentElementAtPosition(binaryReportEntries, bitPosition);
            gamma.append(mostFrequentAtPos);
            epsilon.append(mostFrequentAtPos == '1' ? '0' : '1');
        }
        return multiplyRates(gamma.toString(), epsilon.toString());
    }

    public long diagnoseLifeSupportRating(List<String> reportEntries) {
        List<String> oxygenRate = new ArrayList<>(List.copyOf(reportEntries));
        List<String> cO2Rate = new ArrayList<>(List.copyOf(reportEntries));
        int reportSize = reportEntries.get(0).length();

        for (int bitPosition = 0; bitPosition < reportSize; bitPosition++) {
            char mostFrequentOxygenElement = getMostFrequentElementAtPosition(oxygenRate, bitPosition);
            char leastFrequentCo2Element = getMostFrequentElementAtPosition(cO2Rate, bitPosition) == '1' ? '0' : '1';

            int finalBitPosition = bitPosition;
            if (oxygenRate.size() > 1) {
                oxygenRate.removeIf(p -> p.charAt(finalBitPosition) != mostFrequentOxygenElement);
            }
            if (cO2Rate.size() > 1) {
                cO2Rate.removeIf(p -> p.charAt(finalBitPosition) != leastFrequentCo2Element);
            }
            if (cO2Rate.size() == 1 && oxygenRate.size() == 1) {
                break;
            }
        }
        return multiplyRates(oxygenRate.get(0), cO2Rate.get(0));
    }

    private long multiplyRates(String s, String s2) {
        return Integer.parseInt(s, 2) * Integer.parseInt(s2, 2);
    }

    private char getMostFrequentElementAtPosition(List<String> reportEntries, int bitPosition) {
        List<Character> currentPositionList = new ArrayList<>();
        for (String currentReport : reportEntries) {
            currentPositionList.add(currentReport.charAt(bitPosition));
        }
        List<Character> mostFrequentElementsInList = AdventMathUtils.findMostFrequentElementsInList(currentPositionList);
        if (mostFrequentElementsInList.size() == 1) {
            return mostFrequentElementsInList.get(0);
        } else {
            return '1';
        }
    }
}
