package fr.cybercicco.deckentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Deck {

    private List<Card> cards = new ArrayList<>();
    private Deck deck;

    public Deck(){
        for(char suite :"cdsh".toCharArray()){
            for(int i = 1; i <14; i++){
                cards.add(new Card(i, suite));
            }
        }
    }

    public Card getCardFromDeck(int strength, int suite){
        return cards.remove(strength*suite-1);
    }

    public void clearCards(){
        cards.clear();
    }
}
