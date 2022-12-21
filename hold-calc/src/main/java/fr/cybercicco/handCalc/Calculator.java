package fr.cybercicco.handCalc;

import fr.cybercicco.deck.Board;
import fr.cybercicco.deck.Deck;
import fr.cybercicco.deck.Player;
import fr.cybercicco.deck.Showdown;

import fr.cybercicco.deck.Card;
import java.util.List;


public class Calculator {
    
    public String getHandEquity(Deck deck, Showdown showdown, Board board, Player player1, Player player2, String handPlayer1, String handPlayer2){
        float accumulator = 0f;
        int loops = 100000;
        for(int i = 0; i<loops; i++){
            deck.resetDeck();
            player1.setHand(deck, handPlayer1);
            if(handPlayer2 == null){
                player2.setRandomHand(deck);
            } else {
                player2.setHand(deck, handPlayer2);
            }
            board.createRandomBoard(deck);
            List<Card> communityCards = board.getCommunityCards(deck);
            List<Card> player1Cards = player1.getPlayerHand();
            List<Card> player2Cards = player2.getPlayerHand();
            accumulator += showdown.compareHands(communityCards, player1Cards, player2Cards);
        }

        return "Équité de la main "+ handPlayer1 + " contre " + ((handPlayer2 == null) ? "une main aléatoire : " : handPlayer2 + " : ") + ((accumulator*100)/loops) + "%";
    }

    public String getHandEquity(Deck deck, Showdown showdown, Board board, Player player1, Player player2, String handPlayer1){
        return getHandEquity(deck, showdown, board, player1, player2, handPlayer1, null);
    }
}
