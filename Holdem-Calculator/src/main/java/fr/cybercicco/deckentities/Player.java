package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    public List<Card> hand = new ArrayList<>();
    public double strength;

    public void setHand(Deck deck, String cards){
        hand.clear();
        hand.add(StringCardConverter.getCardFromString(cards.substring(0, 2), deck));
        hand.add(StringCardConverter.getCardFromString(cards.substring(2), deck));
    }

    public String getHandString(){
        return hand.stream().map(StringCardConverter::getStringFromCard).collect(Collectors.joining());
    }
}
