package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Showdown {

    public static float getWinner(Player p1, Player p2, List<Card> comCards){
        List<Card> p1Combination = new ArrayList<>(p1.hand);
        List<Card> p2Combination = new ArrayList<>(p2.hand);

        p1Combination.addAll(comCards);
        p2Combination.addAll(comCards);

        Collections.sort(p1Combination);
        Collections.sort(p2Combination);

        CombinationCalc.setBestOfFiveCards(p1, p1Combination);
        CombinationCalc.setBestOfFiveCards(p2, p2Combination);

        if(p1.strength > p2.strength) return 1;
        if(p2.strength > p1.strength) return 0;
        return 0.5f;
    }
}

