package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;

import java.util.List;

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
     *
     * Changes hand card strength based on the hand rank in poker.
     * Sorting the array based on hand strength after it is given
     * to this function should make it so you can compare two or more
     * lists and get which hand is stronger by comparing every card strength
     * one by one.
     */
    public static void setHandStrength(List<Card> cards){
        int pair = 0;
        boolean trips = false;
        boolean flush = true;
        boolean straight = true;

        for(int i = 1; i<5; i++) {
            if (straight && (cards.get(i).strength != cards.get(i - 1).strength - 1)) {
                straight = false;
            }
            if (flush && (cards.get(i).suite != cards.get(i - 1).suite)) {
                flush = false;
            }
            if(trips && cards.get(i).strength == cards.get(i-1).strength){
                trips = false;
                for(int j = 0; j > -4; j--){
                    cards.get(i-j).strength = cards.get(i-j).baseStrength*(int)Math.pow(14,QUADS);
                }
            } else if(pair > 0){
                if(cards.get(i) == cards.get(i-2)){
                    trips = true;
                    pair--;
                    for(int j = 0; j > -3; j--){
                        cards.get(i-j).strength = cards.get(i-j).baseStrength*(int)Math.pow(14,TRIPS);
                    }
                }
            } else if (cards.get(i) == cards.get(i-1)){
                pair++;
                cards.get(i).strength = cards.get(i).baseStrength*(int) Math.pow(14,PAIR);
                cards.get(i-1).strength = cards.get(i-1).baseStrength*(int) Math.pow(14,PAIR);
            }
        }
        if (pair == 2){
            for(Card card : cards){
                if(card.strength > 13) card.strength = card.baseStrength * (int) Math.pow(14, D_PAIR);
            }
        } else if (pair == 1 && trips) {
            for(Card card : cards){
                if(card.strength >Math.pow(14, D_PAIR)) card.strength = card.baseStrength * (int) Math.pow(14,FULL);
            }
        } else if (straight) {
            if(flush){
                for(Card card : cards){
                    card.strength = card.baseStrength * (int) Math.pow(14, STRAIGHT_FLUSH);
                }
            } else{
                for(Card card :cards){
                    card.strength = card.baseStrength * (int) Math.pow(14, STRAIGHT);
                }
            }
        } else if(flush){
            for (Card card : cards){
                card.strength = card.baseStrength * (int) Math.pow(14, FLUSH);
            }
        }
    }
}
