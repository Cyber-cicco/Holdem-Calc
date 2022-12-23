package fr.cybercicco;

import fr.cybercicco.deckentities.Board;
import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import fr.cybercicco.utils.StringCardConverter;

import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        Player player = new Player();
        String[] combination = {"Kh", "Kc", "Ks", "Th", "Tc"};

        for(int i = 0; i < 1000; i++){
            deck.clearCards();
            board.createRandomBoard(deck);
            List<Card> cards = board.getCommunityCards();
            Collections.sort(cards);
            for(Card card : cards){
                System.out.print(StringCardConverter.getStringFromCard(card) + " ");
            }
            System.out.println(HandStrengthCalc.getCombinationStrength(cards));
        }


        System.out.println(HandStrengthCalc.getCombinationStrength(board.getCommunityCards()));


    }
}