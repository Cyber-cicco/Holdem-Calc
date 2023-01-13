package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.deckentities.Player;
import fr.cybercicco.utils.StringCardConverter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * /!\ Usine à gaz, ne pas toucher.
 * Permet de créer toutes les combinaisons possible de cinq cartes étant donné une liste de n cartes
 */
public class CombinationCalc {

    private static final Logger LOG = LoggerFactory.getLogger(CombinationCalc.class);
    public static void setBestOfFiveCards(Player p1, List<Card> cards){
        int nbPointers = cards.size()-5;
        int[] pointers = new int[nbPointers];

        // |0|1|2|...|n-1
        for(int i = 0; i < nbPointers; i++){
            pointers[i] = i;
        }
        createAllPossibleHands(pointers, cards, new ArrayList<>(), p1);
        LOG.info("\n");
    }
    private static void createAllPossibleHands(int[] pointers, List<Card> cards, List<Card> currentCombination, Player p1) {
        int lastIndex = pointers.length - 1; // n-1
        while (pointers[lastIndex] < cards.size()) { // [...|n-1] != n+4
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
            pointers[lastIndex]++; //[...|n-1] += 1
            double strOfCurrCom = HandStrengthCalc.getHandStrength(currentCombination);
            if ( strOfCurrCom > p1.strength){
                p1.strength = strOfCurrCom;
                LOG.info("**NEW_MAX**");
            }
            LOG.info(currentCombination.stream().map(StringCardConverter::getStringFromCard).toList().toString());
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
