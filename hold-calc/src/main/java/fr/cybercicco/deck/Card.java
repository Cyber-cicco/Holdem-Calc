package fr.cybercicco.deck;

import java.util.Comparator;

//initialisation de la classe carte
public class Card {
    public int strength;
    public int baseStrength;
    public char suite;

    //constructeur de la carte. Permet d'intialiser son rang et sa suite
    public Card(int strength, char suite){
        this.strength = strength;
        baseStrength = strength;
        this.suite = suite;
    }

    //permet de récupérer le nom de la carte
    public String getCardName(){
        
        if (strength < 8){
            return Integer.toString(strength+2) + suite;                
        } else {
            return switch (strength) {
                case 9 -> "T" + suite;
                case 10 -> "J" + suite;
                case 11 -> "Q" + suite;
                case 12 -> "K" + suite;
                default -> "A" + suite;
            };
        }
    }

}
