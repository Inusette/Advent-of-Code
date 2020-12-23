package Day22;

import java.util.*;
import java.util.stream.Collectors;

public class CombatCardGame {

    private static final String PLAYER1 = "player1";

    private static final String PLAYER2 = "player2";

    public static long playRegularCombat(List<String> players) {
        validatePlayers(players);
        LinkedList<Integer> player1 = preparePlayersDeck(players.get(0));
        LinkedList<Integer> player2 = preparePlayersDeck(players.get(1));

        List<Integer> winner = playSimpleRounds(player1, player2);
        return calculateScore(winner);
    }

    public static long playRecursiveCombat(List<String> players) {
        validatePlayers(players);
        LinkedList<Integer> player1 = preparePlayersDeck(players.get(0));
        LinkedList<Integer> player2 = preparePlayersDeck(players.get(1));
        Map<String, LinkedList<Integer>> winner = playRecursive(new HashMap<>(Map.of(PLAYER1, player1, PLAYER2, player2)));

        if (winner.containsKey(PLAYER1))
            return calculateScore(winner.get(PLAYER1));
        else
            return calculateScore(winner.get(PLAYER2));
    }

    private static void validatePlayers(List<String> players) {
        if (players.size() != 2) {
            throw new IllegalArgumentException("Two players are required for this game :)");
        }
    }

    private static LinkedList<Integer> preparePlayersDeck(String player) {
        String[] splitInput = player.split(":");
        return Arrays.stream(splitInput[1].trim().split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static List<Integer> playSimpleRounds(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        while (!player1.isEmpty() && !player2.isEmpty()) {
            int player1Card = player1.pollFirst();
            int player2Card = player2.pollFirst();

            if (player1Card > player2Card) {
                addCardsToWinnerDeck(player1, player1Card, player2Card);
            } else {
                addCardsToWinnerDeck(player2, player2Card, player1Card);
            }
        }
        if (player1.isEmpty()) return player2;
        else return player1;
    }

    private static long calculateScore(List<Integer> deck) {
        long totalScore = 0;
        int currentCard = 0;
        for (int cardValue = deck.size(); cardValue >= 1; cardValue--) {
            totalScore += deck.get(currentCard) * cardValue;
            currentCard++;
        }
        return totalScore;
    }

    private static Map<String, LinkedList<Integer>> playRecursive(Map<String, LinkedList<Integer>> players) {
        LinkedList<Integer> player1 = players.get(PLAYER1);
        LinkedList<Integer> player2 = players.get(PLAYER2);

        Set<String> seenDeckSequences = new HashSet<>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            // if the sequence happened before, player 1 wins
            String currentDecksSequence = findCurrentSequence(player1, player2);
            if (seenDeckSequences.contains(currentDecksSequence)) {
                players.remove(PLAYER2);
                return players;
            }
            seenDeckSequences.add(currentDecksSequence);

            int player1Card = player1.pollFirst();
            int player2Card = player2.pollFirst();

            if (player1.size() < player1Card || player2.size() < player2Card) {
                // if one of the players does not have enough cards, player with the highest value card wins
                if (player1Card > player2Card) {
                    addCardsToWinnerDeck(player1, player1Card, player2Card);
                } else {
                    addCardsToWinnerDeck(player2, player2Card, player1Card);
                }
            } else {
                // play the sub game
                LinkedList<Integer> subDeck1 = new LinkedList<>(player1.subList(0, player1Card));
                LinkedList<Integer> subDeck2 = new LinkedList<>(player2.subList(0, player2Card));
                Map<String, LinkedList<Integer>> subGamePlayers = new HashMap<>(Map.of(PLAYER1, subDeck1, PLAYER2, subDeck2));
                subGamePlayers = playRecursive(subGamePlayers);
                if (subGamePlayers.containsKey(PLAYER1)) {
                    addCardsToWinnerDeck(player1, player1Card, player2Card);
                } else {
                    addCardsToWinnerDeck(player2, player2Card, player1Card);
                }
            }
        }
        // remove the loser from the map
        if (player1.isEmpty()) players.remove(PLAYER1);
        else players.remove(PLAYER2);

        return players;
    }

    private static String findCurrentSequence(LinkedList<Integer> player1, LinkedList<Integer> player2) {
        String player1Sequence = player1.stream().map(String::valueOf).collect(Collectors.joining());
        String player2Sequence = player2.stream().map(String::valueOf).collect(Collectors.joining());
        return player1Sequence + " " + player2Sequence;
    }

    private static void addCardsToWinnerDeck(LinkedList<Integer> winnerDeck, Integer firstCard, Integer secondCard) {
        winnerDeck.addLast(firstCard);
        winnerDeck.addLast(secondCard);
    }
}
