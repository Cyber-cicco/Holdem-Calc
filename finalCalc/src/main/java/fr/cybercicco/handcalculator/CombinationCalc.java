package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.utils.StringCardConverter;

import java.util.ArrayList;
import java.util.List;

public class CombinationCalc {

    static void setBestOfFiveCards(Player p1, List<Card> cards){
        int nbPointers = cards.size()-5;
        int[] pointers = new int[nbPointers];
        for(int i = 0; i < nbPointers; i++){
            pointers[i] = i;
        }
        createAllPossibleHands(pointers, cards, new ArrayList<>(), p1);
    }
    private static void createAllPossibleHands(int[] pointers, List<Card> cards, List<Card> currentCombination, Player p1) {
        int lastIndex = pointers.length - 1;
        while (pointers[lastIndex] < cards.size()) {
            for (int i = 0; i < cards.size(); i++) {
                boolean write = true;
                for (int pointer : pointers) {
                    if (pointer == i) {
                        write = false;
                        break;
                    }
                }
                if (write) {
                    currentCombination.add(cards.get(i));
                }
            }
            pointers[lastIndex]++;
            double strOfCurrCom = HandStrengthCalc.getHandStrength(currentCombination);
            if ( strOfCurrCom > p1.strength){
                p1.strength = strOfCurrCom;
                System.out.println("NEW MAX : " + strOfCurrCom);
            }
            System.out.println(currentCombination.stream().map(c -> StringCardConverter.getStringFromCard(c)).toList());
            currentCombination.clear();
        }
        for (int i = pointers.length - 1; i >= 0; i--) {
            if (i != pointers.length - 1 && pointers[i] != pointers[i + 1] - 1 && pointers[lastIndex] != lastIndex) {
                pointers[i]++;
                for (int j = i + 1; j < pointers.length; j++) {
                    pointers[j] = pointers[j - 1] + 1;
                }
                createAllPossibleHands(pointers, cards, currentCombination, p1);
            }
        }
    }
}
