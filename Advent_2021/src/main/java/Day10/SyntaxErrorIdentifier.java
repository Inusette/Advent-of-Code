package Day10;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SyntaxErrorIdentifier {

    private final Map<Character, Integer> CORRUPTION_SCORES = Map.of(
            ')', 3, ']', 57, '}', 1197, '>', 25137);

    private final Map<Character, Integer> AUTOCOMPLETION_SCORES = Map.of(
            ')', 1, ']', 2, '}', 3, '>', 4);

    private final BiMap<Character, Character> CORRESPONDING_CHUNK_ELEMENTS = HashBiMap.create(Map.of(
            ')', '(', ']', '[', '}', '{', '>', '<'));

    private final Set<char[]> nonCorruptedLines = new HashSet<>();

    public long calculateCorruptedSyntaxScore(List<String> syntaxLines) {
        long totalScore = 0;
        for (String line : syntaxLines) {
            char[] lineChunks = line.trim().toCharArray();
            long corruptedLineScore = calculateCorruptedLineScore(lineChunks);
            totalScore += corruptedLineScore;
            if (corruptedLineScore == 0) {
                nonCorruptedLines.add(lineChunks);
            }
        }
        return totalScore;
    }

    public long calculateAutocompletionScores() {
        return nonCorruptedLines.stream()
                .map(this::calculateAutocompletionLineScore)
                .sorted()
                .skip(nonCorruptedLines.size() / 2)
                .findFirst().get();
    }

    private long calculateCorruptedLineScore(char[] line) {
        Deque<Character> openChunks = new ArrayDeque<>();
        for (char chunkElement : line) {
            if (CORRESPONDING_CHUNK_ELEMENTS.containsValue(chunkElement)) {
                openChunks.addLast(chunkElement);
            } else {
                if (openChunks.peekLast() == CORRESPONDING_CHUNK_ELEMENTS.get(chunkElement)) {
                    openChunks.pollLast();
                } else {
                    return CORRUPTION_SCORES.get(chunkElement);
                }
            }
        }
        return 0;
    }

    private long calculateAutocompletionLineScore(char[] line) {
        Deque<Character> openChunks = new ArrayDeque<>();
        for (char chunkElement : line) {
            if (CORRESPONDING_CHUNK_ELEMENTS.containsValue(chunkElement)) {
                openChunks.addFirst(chunkElement);
            } else {
                if (openChunks.peekFirst() == CORRESPONDING_CHUNK_ELEMENTS.get(chunkElement)) {
                    openChunks.pollFirst();
                }
            }
        }
        long lineAutocompleteTotal = 0;
        for (Character opening : openChunks) {
            Character closing = CORRESPONDING_CHUNK_ELEMENTS.inverse().get(opening);
            lineAutocompleteTotal = lineAutocompleteTotal * 5 + AUTOCOMPLETION_SCORES.get(closing);
        }

        return lineAutocompleteTotal;
    }
}
