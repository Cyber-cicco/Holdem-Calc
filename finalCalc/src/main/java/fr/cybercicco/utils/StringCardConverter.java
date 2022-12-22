package fr.cybercicco.utils;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;

/**
 * Class to pull cards from the deck
 */
public class StringCardConverter {
    static final char[] charToStr = {'T', 'J', 'Q', 'K', 'A'};
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
        int suite = 0;
        char firstChar = card.charAt(0);
        char secondChar = card.charAt(1);

        for(int i = 0; i <intToChar.length; i++){
            if(firstChar == charToStr[i]){
                strength = i + charToStrIncr;
            }
            if(secondChar == intToChar[i]){
                suite = i+ intToCharIncr;
            }
        }
        if(strength == 0 && firstChar == charToStr[4]){
            strength = 13;
        } else {
            strength = Character.getNumericValue(firstChar);
        }
        return deck.getCardFromDeck(strength, suite);
    }

    public static String getStringFromCard(Card card){
        return  "" + card.baseStrength + intToChar[card.suite-1];
    }
}
