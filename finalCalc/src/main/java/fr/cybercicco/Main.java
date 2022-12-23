package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import fr.cybercicco.utils.StringCardConverter;
import java.util.List;

public class Main {
    static class C {
        int[] i = {1};
    }
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        Player player = new Player();
        String[] combination = {"Ah", "Ad", "As", "Kc", "Kh"};
        board.setBoard(combination, deck);
        player.setBestCombination(board.getCommunityCards());

        HandStrengthCalc.setHandStrength(player);
        for(int str : player.strengthOfBestCombination){
            System.out.print(str + " ");
        }
        System.out.println();
        /*
        for(int i = 0; i < 10; i++){
            board.createRandomBoard(deck);
            player.setBestCombination(board.getCommunityCards());

            HandStrengthCalc.setHandStrength(player);

            for(int str : player.strengthOfBestCombination){
                System.out.print(str + " ");
            }
            System.out.println();
            deck.clearCards();
        }
        */


    }
}