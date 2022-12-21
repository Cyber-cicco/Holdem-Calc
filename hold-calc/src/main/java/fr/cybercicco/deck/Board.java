package fr.cybercicco.deck;
import java.util.ArrayList;
import java.util.List;
import fr.cybercicco.deck.Card;

public class Board {
    
    private List<Card> communityCards = new ArrayList<>();

    public void createRandomBoard(Deck deck){
        communityCards.clear();
        for(int i = 0; i <5; i++){
            communityCards.add(deck.getRandomCard());
        }
    }

    public List<Card> getCommunityCards(){
        return communityCards;
    }
}
