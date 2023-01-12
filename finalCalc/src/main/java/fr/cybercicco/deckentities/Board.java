package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Card> communityCards = new ArrayList<>();

    public void createRandomBoard(Deck deck){
        deck.clearCards();
        communityCards.clear();
        for(int i = 0; i <5; i++){
            communityCards.add(deck.getRandomCard());
        }
    }

    public List<Card> getCommunityCards(){
        return communityCards;
    }
}
