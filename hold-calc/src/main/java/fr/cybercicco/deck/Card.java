package fr.cybercicco.deck;

//initialisation de la classe carte
public class Card {
    int strength;
    char suite;

    //constructeur de la carte. Permet d'intialiser son rang et sa suite
    public Card(int strength, char suite){
        this.strength = strength;
        this.suite = suite;
    }

    //permet de récupérer le nom de la carte
    public String getCardName(){
        
        if (strength < 8){
            return Integer.toString(strength+2) + suite;                
        } else {
            return switch (strength) {
                case 8 -> "T" + suite;
                case 9 -> "J" + suite;
                case 10 -> "Q" + suite;
                case 11 -> "K" + suite;
                default -> "A" + suite;
            };
        }
    }
}
