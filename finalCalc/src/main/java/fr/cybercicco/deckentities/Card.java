package fr.cybercicco.deckentities;

public class Card implements Comparable<Card>{
    public int strength;
    public final int baseStrength;
    public final int suite;

    /**
     *
     * @param strength
     * @param suite
     * creates a card from two integers.
     * This class is optimised for algorithms and should only be accessed to by factories, thus the abscence of getters
     * and setters
     * All the logical bits of the user interface should in now way get implemented here.
     */
    public Card(int strength, int suite){
        this.strength = strength;
        baseStrength = strength;
        this.suite = suite;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(strength, o.strength);
    }
}
