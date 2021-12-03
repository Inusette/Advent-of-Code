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
        List<String> CO2Rate = new ArrayList<>(List.copyOf(reportEntries));
        int reportSize = reportEntries.get(0).length();

        for (int bitPosition = 0; bitPosition < reportSize; bitPosition++) {
            char mostFrequentOxygenElement = getMostFrequentElementAtPosition(oxygenRate, bitPosition);
            char leastFrequentCo2Element = getMostFrequentElementAtPosition(CO2Rate, bitPosition) == '1' ? '0' : '1';

            filterReports(oxygenRate, bitPosition, mostFrequentOxygenElement);
            filterReports(CO2Rate, bitPosition, leastFrequentCo2Element);
            if (CO2Rate.size() == 1 && oxygenRate.size() == 1) {
                break;
            }
        }
        return multiplyRates(oxygenRate.get(0), CO2Rate.get(0));
    }

    private void filterReports(List<String> reportsToFilter, int bitPosition, char elementToCheck) {
        if (reportsToFilter.size() > 1) {
            reportsToFilter.removeIf(p -> p.charAt(bitPosition) != elementToCheck);
        }
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
        return mostFrequentElementsInList.size() == 1 ? mostFrequentElementsInList.get(0) : '1';
    }
}
