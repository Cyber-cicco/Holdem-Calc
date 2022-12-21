package fr.cybercicco.deck;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<>();

    public void setHand(Deck deck, String cards){
        hand.clear();
        hand.add(deck.getCardFromDeck(cards.substring(0, 2)));
        hand.add(deck.getCardFromDeck(cards.substring(2)));
    }

    public List<Card> getPlayerHand(){
        return hand;
    }
    
    
    public void setRandomHand(Deck deck){
        hand.clear();
        hand.add(deck.getRandomCard());
        hand.add(deck.getRandomCard());
    }
}
