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

        String[] comb1 = {"Ah", "Ad", "Kc", "Kd", "4h"};
        String[] comb2 = {"Ah", "Ad", "Kc", "Kd", "2c"};
        /*
        board.setBoard(comb1,deck);
        System.out.println(HandStrengthCalc.getHandStrength(board.getCommunityCards()));
        board.setBoard(comb2,deck);
        System.out.println(HandStrengthCalc.getHandStrength(board.getCommunityCards()));


        long time1 = date.getTime();
        for(int i = 0; i < 20000000; i++){
            //deck.clearCards();
            //board.resetRandomBoard(deck);
            //board.createRandomBoard(deck);
            HandStrengthCalc.getHandStrength(board.getCommunityCards());
        }
        System.out.println(new Date().getTime()-time1);
        */


    }
}