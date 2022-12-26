package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.CombinationCalc;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import fr.cybercicco.handcalculator.Showdown;

import java.util.Date;


public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        Showdown showdown = new Showdown();

        Player p1 = new Player();
        Player p2 = new Player();

        p1.setHand(deck, "AhAd");
        p2.setHand(deck, "AcKs");
        board.createRandomBoard(deck);

        showdown.getWinner(p1,p2,board.getCommunityCards());

        /*
        String[] comb1 = {"Ah", "As", "Qh", "3s", "3h"};
        String[] comb2 = {"Ah", "As", "4h", "3s", "3d"};

        board.setBoard(comb1,deck);
        double str1 = HandStrengthCalc.getHandStrength(board.getCommunityCards());
        System.out.println(str1);
        board.setBoard(comb2,deck);
        double str2 = HandStrengthCalc.getHandStrength(board.getCommunityCards());
        System.out.println(str2);

        System.out.println(str1 < str2);

*/
        Date date = new Date();
        long time1 = date.getTime();
        for(int i = 0; i < 20_000_000; i++){
            board.createRandomBoard(deck);
            //showdown.getWinner(p1, p2, board.getCommunityCards());
            HandStrengthCalc.testFunction(board.getCommunityCards());
        }
        System.out.println(new Date().getTime()-time1);



    }
}