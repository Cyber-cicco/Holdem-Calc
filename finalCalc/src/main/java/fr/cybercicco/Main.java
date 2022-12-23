package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.HandStrengthCalc;


public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        Player player = new Player();
        String[] combination = {"Kc", "Kd", "Ah", "As", "2c"};
        board.setBoard(combination, deck);
        player.setBestCombination(board.getCommunityCards());


        HandStrengthCalc.setHandStrength(player);
        for(long str : player.strengthOfBestCombination){
            System.out.print(str + " ");
        }
        System.out.println();


    }
}