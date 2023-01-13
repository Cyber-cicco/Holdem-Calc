package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;

public class EquityCalc {

    private Deck deck;
    private Board board;
    private Showdown showdown;
    private Player player1;
    private Player player2;

    private final int LOOPS =100000;

    public EquityCalc(Deck deck, Board board) {
        this.deck = deck;
        this.board = board;
    }

    public String getEquity(Player player1, Player player2){
        float accumulator = 0f;
        for(int i = 0; i < LOOPS; i++){
            board.createRandomBoard(deck);
            accumulator += Showdown.getWinner(player1, player2, board.communityCards);
        }
        return "Équité de la main "+ player1.getHandString() +" contre "+ player2.getHandString() + " : " + Float.toString((accumulator*100)/LOOPS) + "%";
    }
}
