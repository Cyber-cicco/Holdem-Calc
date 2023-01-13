package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;

import java.util.*;

/**
 * Class providing static methods to determinate how strong a hand is.
 */
public class HandStrengthCalc {

    static final double RATIO_TRIPS = 0.001;
    static final double RATIO_PAIR = 0.0000001;
    static final double RATIO_HIGH = 0.00000001;
    static final int PAIR = 1;
    static final int D_PAIR = 2;
    static final int TRIPS = 3;
    static final int STRAIGHT = 4;
    static final int FLUSH = 5;
    static final int FULL = 6;
    static final int QUADS = 7;
    static final int STRAIGHT_FLUSH = 8;
    /**
     * THIS METHOD NEEDS A SORTED LIST TO WORK.
     * Takes a list of 5 cards and returns a double
     * based on its strength, that can be used to
     * compare the strength of two hands.
     * As simple as that.
     */

    //TODO: change how straights and flushes work to adapt to potential seven cards Lists
    public static double getHandStrength(List<Card> cards) {

        //on déclare les variables permettant de vérifier la nature de la main
        byte pair = 0;
        boolean trips = false;
        boolean flush = true;
        boolean straight = true;
        boolean quads = false;

        //On crée une map permettant de flag les cartes de la main comme kicker ou non
        Map<Card, Double> flags = new HashMap<>();
        cards.forEach((c) -> flags.put(c, RATIO_HIGH));

        //on lance la boucle itérant sur toutes les cartes
        for (int i = 1; i < cards.size(); i++) {
            if(cards.get(i-1).suite != cards.get(i).suite){
                flush =false;
            }
            //Si la carte actuelle n'est pas égale à un de moins que la carte avant, alors ce n'est pas une suite
            if (cards.get(i-1).strength - cards.get(i).strength != 1) {
                straight = false;
            }
            //Bloc de code ou toute situation est mutuellement exclusive
            //Si on a déjà identifié que trois cartes étaient identiques:
            if (trips) {
                //Si la carte actuelle est de même force que celle trois rang derrière alors il y a quads.
                if (cards.get(i).strength == cards.get(i - 3).strength) {
                    trips = false; // on indique qu'il ne s'agit pas de trois cartes identiques mais quatre
                    quads = true;
                    flags.put(cards.get(i), RATIO_TRIPS);
                }
            }
            if (pair > 0) {
                //On test s'il y a une carte de même force deux rangs avant. Si c'est le cas, il y a brelan
                if (cards.get(i).strength == cards.get(i - 2).strength) {
                    trips = true;
                    pair--; //On décrément la paire, puisqu'il s'agit de trois cartes identiques et non deux
                    flags.put(cards.get(i), RATIO_TRIPS);
                } else if (cards.get(i).strength == cards.get(i - 1).strength && pair ==1) {
                    pair++;
                    flags.put(cards.get(i), RATIO_PAIR);
                }
            } else if (cards.get(i).strength == cards.get(i - 1).strength) {
                pair++;
                flags.put(cards.get(i), RATIO_PAIR);
                flags.put(cards.get(i-1), RATIO_PAIR);
            }
        }

        if(straight && flush) return setRelativeStrength(cards, flags, STRAIGHT_FLUSH);
        if(flush && cards.get(0).strength == 14 && cards.get(1).strength == 5) return STRAIGHT_FLUSH;
        if(quads) return setRelativeStrength(cards, flags, QUADS);
        if(trips && pair > 0) return setRelativeStrength(cards, flags, FULL);
        if(flush) return setRelativeStrength(cards, flags, FLUSH);
        if(straight) return setRelativeStrength(cards, flags, STRAIGHT);
        if(trips) return setRelativeStrength(cards, flags, TRIPS);
        if(pair > 1) return setRelativeStrength(cards, flags, D_PAIR);
        if(pair == 1) return setRelativeStrength(cards, flags, PAIR);
        if(cards.get(0).strength == 14 && cards.get(1).strength == 5) return STRAIGHT;
        return setRelativeStrength(cards, flags, 0);
    }

    /**
     * Method to decide which of two hands in the same category is the strongest
     * @param cards list of cards
     * @param flags map to get a weaker multiplier if card is a kicker
     * @param mainStr Main strength of combination
     * @return Total strength of the hand
     */
    private static double setRelativeStrength(List<Card> cards, Map<Card, Double> flags, double mainStr){
        double i = 0.1;
        double str = 0;
        for(Card card : cards){
            str += (flags.get(card) == RATIO_HIGH) ? card.strength * flags.get(card) * i : card.strength * flags.get(card);
            i *= 0.1;
        }
        return str + mainStr;
    }
}