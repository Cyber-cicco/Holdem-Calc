package fr.cybercicco.deckentities;

import fr.cybercicco.factory.StringCardConverter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Card> hand = new ArrayList<>();

    public void setHand(Deck deck, String cards){
        hand.clear();
        hand.add(StringCardConverter.getCardFromString(cards.substring(0, 2), deck));
        hand.add(StringCardConverter.getCardFromString(cards.substring(2), deck));
    }
}
