package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Card> hand = new ArrayList<>();
    public List<Card> bestCombination = new ArrayList<>();
    public int[] strengthOfBestCombination = new int[5];

    public void setHand(Deck deck, String cards){
        hand.clear();
        hand.add(StringCardConverter.getCardFromString(cards.substring(0, 2), deck));
        hand.add(StringCardConverter.getCardFromString(cards.substring(2), deck));
    }

    public void setBestCombination(List<Card> cards){
        for(int i = 0; i < 5; i++){
            bestCombination.add(cards.get(i));
            strengthOfBestCombination[i] = cards.get(i).strength;
        }
    }
}
