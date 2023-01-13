package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

public class MainPlayer extends Player{

    public void setHand(Deck deck, String cards){
        hand.clear();
        hand.add(StringCardConverter.getCardFromStringPermanently(cards.substring(0, 2), deck));
        hand.add(StringCardConverter.getCardFromStringPermanently(cards.substring(2), deck));
    }
}
