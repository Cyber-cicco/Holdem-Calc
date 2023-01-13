package fr.cybercicco.utils;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;

import java.util.Map;

/**
 * Class to pull cards from the deck
 */
public class StringCardConverter {
    static final Map<Character, Integer> cdsh = Map.of(
            'c' ,1,
            'd', 2,
            's', 3,
            'h', 4
            );
    static final char[] intToChar = {'c', 'd', 's', 'h'};

    /**
     *
     * @param card String representation of a card
     * @param deck The Deck of which the card will be pulled from
     * @return Card from the deck
     * Takes the standardised appelation of a card and transforms it into
     * an object with parameters optimised for algorithms
     */
    public static Card getCardFromString(String card, Deck deck){
        int strength = getStrength(card);
        return deck.getCardFromDeck(strength, cdsh.get(card.charAt(1)));
    }

    public static Card getCardFromString(String card){
        int strength = getStrength(card);
        return new Card(strength, cdsh.get(card.charAt(1)));
    }

    public static Card getCardFromStringPermanently(String card, Deck deck){
        int strength = getStrength(card);
        return deck.getCardFromDeckPermanently(strength, cdsh.get(card.charAt(1)));
    }

    private static int getStrength(String card) {
        int strength;
        switch (card.charAt(0)){
            case 'T' -> strength = 10;
            case 'J' -> strength = 11;
            case 'Q' -> strength = 12;
            case 'K' -> strength = 13;
            case 'A' -> strength = 14;
            default -> strength = Character.getNumericValue(card.charAt(0));
        }
        return strength;
    }

    public static String getStringFromCard(Card card){
        return switch (card.strength) {
            case 10 -> "T" + intToChar[card.suite - 1];
            case 11 -> "J" + intToChar[card.suite - 1];
            case 12 -> "Q" + intToChar[card.suite - 1];
            case 13 -> "K" + intToChar[card.suite - 1];
            case 14 -> "A" + intToChar[card.suite - 1];
            default -> "" + (card.strength - 20) + intToChar[card.suite - 1];
        };
    }
}
