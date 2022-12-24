package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;

import java.util.List;

/**
 * Class providing static methods to determinate how strong a hand is.
 * There's certainly a lot of refactoring to do, but it's fast.
 */
public class HandStrengthCalc {

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
     * Changes hand card strength based on the hand rank in poker.
     * Sorting the array based on hand strength after it is given
     * to this function should make it so you can compare two or more
     * lists and get which hand is stronger by comparing every card strength
     * one by one.
     */
    public static long getHandStrength(List<Card> cards) {

        //on déclare les variables permettant de vérifier la nature de la main
        byte pair = 0;
        boolean trips = false;
        boolean flush = true;
        boolean straight = true;

        //on déclare les éléments permettant d'obtenir la force de la main.
        long[] str = {cards.get(0).strength, cards.get(1).strength, cards.get(2).strength, cards.get(3).strength, cards.get(4).strength};
        long accumulator = 0;

        //on lance la boucle itérant sur toutes les cartes
        for (int i = 1; i < 5; i++) {

            //Si la carte actuelle n'est pas égale à un de moins que la carte avant, alors ce n'est pas une suite
            if (straight && (cards.get(i).strength != cards.get(i-1).strength - 1)) {
                straight = false;
            }

            //Si la couleur de la carte actuelle n'est pas égale à celle de de la carte avant, alors ce n'est pas une flush
            if (flush && (cards.get(i).suite != cards.get(i - 1).suite)) {
                flush = false;
            }

            //Bloc de code ou toute situation est mutuellement exclusive
            //Si on a déjà identifié que trois cartes étaient identiques:
            if (trips) {
                //Si la carte actuelle est de même force que celle trois rang derrière alors il y a quads.
                if (cards.get(i).strength == cards.get(i - 3).strength) {
                    trips = false; // on indique qu'il ne s'agit pas de trois cartes identiques mais quatre
                    for (int j = 0; j > -4; j--) {
                        str[i + j] = cards.get(i + j).strength * (long) Math.pow(14, QUADS);
                    }
                }
            }

            if (pair > 0) {

                //On test s'il y a une carte de même force deux rangs avant. Si c'est le cas, il y a brelan
                if (cards.get(i).strength == cards.get(i - 2).strength) {
                    trips = true;
                    pair--; //On décrément la paire, puisqu'il s'agit de trois cartes identiques et non deux
                    for (int j = 0; j > -3; j--) {
                        str[i + j] = cards.get(i + j).strength * (long) Math.pow(14, TRIPS);
                    }
                } else if (cards.get(i).strength == cards.get(i - 1).strength) {
                    pair++;
                    str[i] = cards.get(i).strength * (long) Math.pow(14, PAIR);
                    str[i - 1] = cards.get(i - 1).strength * (long) Math.pow(14, PAIR);
                }
            } else if (cards.get(i).strength == cards.get(i - 1).strength) {
                pair++;
                str[i] = cards.get(i).strength * (long) Math.pow(14, PAIR);
                str[i - 1] = cards.get(i - 1).strength * (long) Math.pow(14, PAIR);
            }
        }

        //code assez parlant (relativement au reste), donc juste on a pas set correctement la force des mains pour
        //certaines combinaisons, donc on le fait maintenant.
        if (pair == 2) {
            for (int i = 0; i < 5; i++) {
                if (str[i] > 13) str[i] = cards.get(i).strength * (long) Math.pow(14, D_PAIR);
            }
        } else if (pair == 1 && trips) {
            for (int i = 0; i < 5; i++) {
                if (str[i] > Math.pow(14, D_PAIR)) str[i] = cards.get(i).strength * (long) Math.pow(14, FULL);
            }
        } else if (straight) {
            if (flush) {
                for (int i = 0; i < 5; i++) {
                    str[i] = cards.get(i).strength * (long) Math.pow(14, STRAIGHT_FLUSH);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    str[i] = cards.get(i).strength * (long) Math.pow(14, STRAIGHT);
                }
            }
        } else if (flush) {
            for (int i = 0; i < 5; i++) {
                str[i] = cards.get(i).strength * (long) Math.pow(14, FLUSH);
            }
        }
        for(int i = 0; i < 5; i++){
            accumulator += str[i];
        }
        return accumulator;
    }
}
