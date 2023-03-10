package fr.cybercicco.deckentities;


import fr.cybercicco.utils.CardException;

import java.util.*;

public class Deck {

    private final List<Card> cards = new ArrayList<>();
    private final List<Card> allCards = new ArrayList<>(52);
    private final Random rand = new Random();

    public List<Card> getAllCards() {
        return allCards;
    }

    /**
     * Instantiating a deck creates all cards that will be used and put it in an arraylist
     * Every instance of a card should only be created here, as every card should be picked from the deck, so that there
     * is no duplicate
     */
    public Deck(){
        for(int h = 1; h <=4; h++){
            for(int i = 2; i <15; i++){
                allCards.add(new Card(i, h));
            }
        }
        clearCards();
    }

    /**
     *
     * @param strength number of the card, from 1 to 13, 13 being Ace, 1 being 2
     * @param suite suite of the card, 1-4 : c,d,s,h
     * @return
     * gets the wanted card from the deck. If it doesn't exist, a random card will be returned instead
     * TODO : implement a way to catch an error if strength or suite is out of the bounds
     */
    public Card getCardFromDeck(int strength, int suite){
        for(int i = 0; i < cards.size(); i++){
            if( cards.get(i).strength == strength && cards.get(i).suite == suite){
                return cards.remove(i);
            }
        }
        throw new CardException("The provided card doesn't exist : " + strength + " " + suite);
    }

    public Card getCardFromDeckPermanently(int strength, int suite){
        for(int i = 0; i < cards.size(); i++){
            if( cards.get(i).strength == strength && cards.get(i).suite == suite){
                allCards.remove(i);
                return cards.remove(i);
            }
        }
        throw new CardException("The provided card doesn't exist : " + strength + " " + suite);
    }

    public Card getRandomCard(){
        return cards.remove(rand.nextInt(cards.size()));
    }

    public void clearCards(){
        cards.clear();
        cards.addAll(allCards);
    }
}
