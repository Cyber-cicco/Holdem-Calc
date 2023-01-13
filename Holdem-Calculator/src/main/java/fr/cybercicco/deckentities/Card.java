package fr.cybercicco.deckentities;

import fr.cybercicco.utils.StringCardConverter;

public class Card implements Comparable<Card>{
    public int strength;
    public final int suite;
    /**
     *
     * @param strength number of the card, from 2 to 14, 14 being Ace, 2 being 2
     * @param suite suite of the card, 1-4 : c,d,s,h
     * creates a card from two integers.
     * This class is optimised for algorithms and should only be accessed to by factories, thus the abscence of getters
     * and setters
     * All the logical bits of the user interface should in now way get implemented here.
     */
     public Card(int strength, int suite){
        this.strength = strength;
        this.suite = suite;
    }

    @Override
    public int compareTo(Card o) {
        return -Integer.compare(strength, o.strength);
    }
    @Override
    public String toString(){
         return StringCardConverter.getStringFromCard(this);
    }
}
