package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.factory.StringCardConverter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();

        for(int i = 0; i < 100; i++){
            board.createRandomBoard(deck);
            List<Card> cards = board.getCommunityCards();
            for(Card card : cards){
                System.out.print(StringCardConverter.getStringFromCard(card) + " ");
            }
            System.out.println();
        }
    }
}