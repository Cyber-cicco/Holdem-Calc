package fr.cybercicco.handcalctest;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Deck;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.handcalculator.CombinationCalc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HandCombTest {

    @Test
    public void testCombination(){
        String[] cards1 = {"As", "Ah", "Tc", "9c", "7h", "4d", "3s"};
        String[] cards2 = {"Ks", "Kh", "Tc", "9c", "7h", "4d", "3s"};
        String[] cards3 = {"As", "Kh", "Tc", "Ts", "7h", "4d", "3s"};
        String[] cards4 = {"Tc", "9c", "8s", "3s", "3h", "3d", "2h"};
        String[] cards5 = {"Ks", "Kh", "Tc", "9c", "8c", "4c", "3c"};
        String[] cards6 = {"Ks", "Kh", "Tc", "9c", "7h", "4d", "3s"};
        String[] cards7 = {"Ks", "Kh", "Tc", "9c", "7h", "4d", "3s"};
        String[] cards8 = {"Ks", "Kh", "Tc", "9c", "7h", "4d", "3s"};
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();

        CombinationCalc.setBestOfFiveCards(player1, HandStrTest.createCardList(cards1));
        CombinationCalc.setBestOfFiveCards(player2, HandStrTest.createCardList(cards2));
        CombinationCalc.setBestOfFiveCards(player3, HandStrTest.createCardList(cards3));
        CombinationCalc.setBestOfFiveCards(player4, HandStrTest.createCardList(cards4));
        CombinationCalc.setBestOfFiveCards(player5, HandStrTest.createCardList(cards5));

        assertTrue(player1.strength > player2.strength);
        assertTrue(player2.strength > player3.strength);
        assertTrue(player4.strength > player1.strength);
        assertTrue(player5.strength > player4.strength);
    }

    @Test
    private void testAllCombinations(){
        Deck deck = new Deck();
        Player player = new Player();
        CombinationCalc.setBestOfFiveCards(player, deck.getAllCards());
        assertTrue(player.strength > 8);
    }
}
