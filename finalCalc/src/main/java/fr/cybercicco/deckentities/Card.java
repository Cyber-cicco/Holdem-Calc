package fr.cybercicco.deckentities;

public class Card implements Comparable<Card>{
    public int strength;
    public final int baseStrength;
    public final int suite;

    //constructeur de la carte. Permet d'intialiser son rang et sa suite
    public Card(int strength, int suite){
        this.strength = strength;
        baseStrength = strength;
        this.suite = suite;
    }

    public int getStrength() {
        return strength;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getSuite() {
        return suite;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(strength, o.getStrength());
    }
}
