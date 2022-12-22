package fr.cybercicco.deckentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck(){
        for(int h = 0; h <4; h++){
            for(int i = 1; i <14; i++){
                cards.add(new Card(i, h+1));
            }
        }
    }

    public Card getCardFromDeck(int strength, int suite){
        for(int i = 0; i < cards.size(); i++){
            if( cards.get(i).getStrength() == strength && cards.get(i).getSuite() == suite){
                return cards.remove(i);
            }
        }
        return getRandomCard();
    }

    public Card getRandomCard(){
        return cards.remove(new Random().nextInt(cards.size()));
    }

    public void clearCards(){
        cards.clear();
    }
}
