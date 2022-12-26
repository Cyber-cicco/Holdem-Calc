package fr.cybercicco.utils;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;

import java.util.HashMap;
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
    static final int charToStrIncr = 9;
    static final int intToCharIncr = 1;

    /**
     *
     * @param card String representation of a card
     * @param deck The Deck of which the card will be pulled from
     * @return Card from the deck
     * Takes the standardised appelation of a card and transforms it into
     * an object with parameters optimised for algorithms
     */
    public static Card getCardFromString(String card, Deck deck){
        int strength = 0;
        switch (card.charAt(0)){
           case 'T' -> strength = 30;
           case 'J' -> strength = 31;
           case 'Q' -> strength = 32;
           case 'K' -> strength = 33;
           case 'A' -> strength = 34;
           default -> strength = Character.getNumericValue(card.charAt(0)) +20;
       }
        return deck.getCardFromDeck(strength, cdsh.get(card.charAt(1)));
    }

    public static String getStringFromCard(Card card){
        return switch (card.strength) {
            case 110 -> "T" + intToChar[card.suite - 1];
            case 111 -> "J" + intToChar[card.suite - 1];
            case 112 -> "Q" + intToChar[card.suite - 1];
            case 113 -> "K" + intToChar[card.suite - 1];
            case 114 -> "A" + intToChar[card.suite - 1];
            default -> "" + (card.strength - 20) + intToChar[card.suite - 1];
        };
    }
}
