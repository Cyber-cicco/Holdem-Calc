package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Card> communityCards = new ArrayList<>();

    public void createRandomBoard(Deck deck){
        communityCards.clear();
        for(int i = 0; i <5; i++){
            communityCards.add(deck.getRandomCard());
        }
    }

    public void resetRandomBoard(Deck deck){
        communityCards.clear();
        for(int i = 0; i <5; i++){
            communityCards.add(deck.getRandomCard());
        }
    }

    public void setBoard(String[] cards, Deck deck){
        communityCards.clear();
        for(int i = 0; i <5; i++){
            communityCards.add(StringCardConverter.getCardFromString(cards[i], deck));
        }
    }

    public List<Card> getCommunityCards(){
        return communityCards;
    }
}
