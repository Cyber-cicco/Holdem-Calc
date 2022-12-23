package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        Player player = new Player();
        String[] comb1 = {"Ac", "Kc", "Qc", "Jc", "Tc"};
        String[] comb2 = {"Kc", "Qc", "Jc", "Tc", "9c"};
        board.createRandomBoard(deck);
        Date date = new Date();
        long time1 = date.getTime();
        for(int i = 0; i < 20000000; i++){
            //deck.clearCards();
            //board.resetRandomBoard(deck);
            //board.createRandomBoard(deck);
            HandStrengthCalc.getHandStrength(board.getCommunityCards());
        }
        System.out.println(new Date().getTime()-time1);



    }
}