package Day08;

import advent.utils.AdventFinderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SevenSegmentParser {

    private final List<SevenSegmentDisplay> allDisplays;

    public SevenSegmentParser(List<String> inputLines) {
        allDisplays = parseAllDisplays(inputLines);
    }

    public int countUniqueValuesInOutput() {
        int uniqueValueCount = 0;
        for (SevenSegmentDisplay display : allDisplays) {
            if (display.outputInDigits.contains(1)) {
                uniqueValueCount += Collections.frequency(display.outputInDigits, 1);
            }
            if (display.outputInDigits.contains(4)) {
                uniqueValueCount += Collections.frequency(display.outputInDigits, 4);
            }
            if (display.outputInDigits.contains(7)) {
                uniqueValueCount += Collections.frequency(display.outputInDigits, 7);
            }
            if (display.outputInDigits.contains(8)) {
                uniqueValueCount += Collections.frequency(display.outputInDigits, 8);
            }
        }
        return uniqueValueCount;
    }

    public long sumOutput() {
        long sum = 0;
        for (SevenSegmentDisplay display : allDisplays) {
            int outputNumber = Integer.parseInt(display.outputInDigits.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining()));
            sum += outputNumber;
        }
        return sum;
    }

    public List<SevenSegmentDisplay> parseAllDisplays(List<String> inputLines) {
        List<SevenSegmentDisplay> allDisplays = parseInputIntoDisplays(inputLines);
        allDisplays.forEach(this::parseDisplay);
        return allDisplays;
    }

    public void parseDisplay(SevenSegmentDisplay d) {
        while (d.patternToDigit.size() < 10) {
            for (String pattern : d.signalPatterns) {
                if (d.patternToDigit.containsValue(pattern)) {
                    continue;
                }
                switch (pattern.length()) {
                    case 2:
                        d.patternToDigit.put(1, pattern);
                        break;
                    case 3:
                        d.patternToDigit.put(7, pattern);
                        break;
                    case 4:
                        d.patternToDigit.put(4, pattern);
                        break;
                    case 7:
                        d.patternToDigit.put(8, pattern);
                        break;
                    case 6:
                        if (d.patternToDigit.containsKey(4) && isWithinSegment(pattern, d.patternToDigit.get(4))) {
                            d.patternToDigit.put(9, pattern);
                        } else if (d.patternToDigit.containsKey(1) && d.patternToDigit.containsKey(9)
                                && isWithinSegment(pattern, d.patternToDigit.get(1))) {
                            d.patternToDigit.put(0, pattern);
                        } else if (d.patternToDigit.containsKey(0) && d.patternToDigit.containsKey(9)) {
                            d.patternToDigit.put(6, pattern);
                        } else {
                            continue;
                        }
                        break;
                    case 5:
                        if (d.patternToDigit.containsKey(1) && isWithinSegment(pattern, d.patternToDigit.get(1))) {
                            d.patternToDigit.put(3, pattern);
                        } else if (d.patternToDigit.containsKey(6) && isWithinSegment(d.patternToDigit.get(6), pattern)) {
                            d.patternToDigit.put(5, pattern);
                        } else if (d.patternToDigit.containsKey(3) && d.patternToDigit.containsKey(5)) {
                            d.patternToDigit.put(2, pattern);
                        } else {
                            continue;
                        }
                    default: // dance the crab dance!
                }
            }
        }
        translateOutput(d);
    }

    public List<SevenSegmentDisplay> parseInputIntoDisplays(List<String> inputLines) {
        List<SevenSegmentDisplay> allDisplays = new ArrayList<>();
        for (String line : inputLines) {
            String[] splitLine = line.trim().split(" \\| ");
            SevenSegmentDisplay sevenSegmentDisplay = new SevenSegmentDisplay();
            sevenSegmentDisplay.signalPatterns = Arrays.stream(splitLine[0].trim().split("\\s")).collect(Collectors.toList());
            sevenSegmentDisplay.output = Arrays.stream(splitLine[1].trim().split("\\s")).collect(Collectors.toList());
            allDisplays.add(sevenSegmentDisplay);
        }
        return allDisplays;
    }

    private boolean isWithinSegment(String outerSegment, String innerSegment) {
        List<String> segments = Arrays.stream(innerSegment.split("")).collect(Collectors.toList());
        return AdventFinderUtils.containsAllPatterns(segments, outerSegment);
    }

    private boolean isSameSegment(String outerSegment, String innerSegment) {
        return outerSegment.length() == innerSegment.length() && isWithinSegment(outerSegment, innerSegment);
    }

    private void translateOutput(SevenSegmentDisplay display) {
        for (String outputPattern : display.output) {
            Integer outputDigit = display.patternToDigit.entrySet().stream()
                    .filter(entry -> isSameSegment(outputPattern, entry.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .get();
            display.outputInDigits.add(outputDigit);
        }
    }

    public static class SevenSegmentDisplay {
        List<String> signalPatterns;
        List<String> output;
        Map<Integer, String> patternToDigit;
        List<Integer> outputInDigits;

        public SevenSegmentDisplay() {
            signalPatterns = new ArrayList<>();
            output = new ArrayList<>();
            patternToDigit = new HashMap<>();
            outputInDigits = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "SevenSegmentDisplay{" + "signalPatterns=" + signalPatterns + ", output=" + output +
                    ", patternToDigit=" + patternToDigit + '}';
        }
    }
}

