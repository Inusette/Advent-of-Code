package Day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitMaskConverter {

    private Map<Long, Long> memory;

    public BitMaskConverter() {
        memory = new HashMap<>();
    }

    public long calculateSumOfConvertedMemoryValues(List<List<String>> inputLines) {
        processMemoryValues(inputLines);
        return sumMemoryValues();
    }

    public long calculateSumOfMemoryValuesWithFloating(List<List<String>> inputLines) {
        memory = new HashMap<>();

        processMemoryWithFloating(inputLines);

        return sumMemoryValues();
    }

    private void processMemoryValues(List<List<String>> maskAndMemoryChunks) {
        for (List<String> currentChunk : maskAndMemoryChunks) {
            String mask = getMask(currentChunk);
            for (int currentIndex = 1; currentIndex < currentChunk.size(); currentIndex++) {
                MemoryEntry currentMemoryEntry = getMemoryEntry(currentChunk, currentIndex);
                memory.put(Long.parseLong(currentMemoryEntry.memoryID), overwriteWithMask(mask, currentMemoryEntry.memoryValue));
            }
        }
    }

    private Long overwriteWithMask(String mask, String memoryValue) {
        long value = Long.parseLong(memoryValue);
        long maskWithOnes = Long.parseLong(mask.replaceAll("X", "1"), 2);
        long maskWithZeroes = Long.parseLong(mask.replaceAll("X", "0"), 2);
        return (value & maskWithOnes) | maskWithZeroes;
    }

    private String getMask(List<String> chunk) {
        return chunk.get(0).replace("mask = ", "").trim();
    }

    private MemoryEntry getMemoryEntry(List<String> currentChunk, int currentIndex) {
        String line = currentChunk.get(currentIndex).replaceAll("mem\\[", "");
        line = line.replaceAll("]", "");
        String[] splitLine = line.split(" = ");

        MemoryEntry memoryEntry = new MemoryEntry();
        memoryEntry.memoryID = splitLine[0].trim();
        memoryEntry.memoryValue = splitLine[1].trim();

        return memoryEntry;
    }

    private long sumMemoryValues() {
        return memory.values().stream().mapToLong(value -> value).sum();
    }

    private void processMemoryWithFloating(List<List<String>> maskAndMemoryChunks) {
        for (List<String> currentChunk : maskAndMemoryChunks) {
            String mask = getMask(currentChunk);
            for (int currentIndex = 1; currentIndex < currentChunk.size(); currentIndex++) {
                MemoryEntry currentMemoryEntry = getMemoryEntry(currentChunk, currentIndex);
            }
        }
    }

    private List<Integer> getIndicesOfAllX(String mask) {
        List<Integer> allIndicesOfX = new ArrayList<>();
        int currentIndex = 0;
        while (true) {
            currentIndex = mask.indexOf('X', currentIndex + 1);
            if (currentIndex >= 0) {
                allIndicesOfX.add(currentIndex);
            } else {
                break;
            }
        }
        return allIndicesOfX;
    }

    private List<String> computePermutations(String permutationPrefix, int permutationSize) {

        if (permutationSize == 0) {
            return List.of(permutationPrefix);
        }
        List<String> permutations = new ArrayList<>();
        for (int digit = 0; digit <= 1 ; digit++) {
            String currentPermutation = permutationPrefix + digit;
            permutations.addAll(computePermutations(currentPermutation, permutationSize -1));
        }
        return permutations;
    }

    private class MemoryEntry {
        String memoryID;
        String memoryValue;
    }
}
