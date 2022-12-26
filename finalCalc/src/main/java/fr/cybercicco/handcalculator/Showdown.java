package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Showdown {

    public void getWinner(Player p1, Player p2, List<Card> comCards){
        List<Card> p1Combination = new ArrayList<>(p1.hand);
        List<Card> p2Combination = new ArrayList<>(p2.hand);

        p1Combination.addAll(comCards);
        p2Combination.addAll(comCards);

        Collections.sort(p1Combination);
        Collections.sort(p2Combination);

        CombinationCalc.setBestOfFiveCards(p1, p1Combination);

    }

    public void testFunction() {
        for (int i = 0; i < 5; i++) {

        }
    }

}

