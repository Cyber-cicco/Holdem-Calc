package fr.cybercicco.deck;
import java.util.ArrayList;
import java.util.List;

public class Deck { 

    //initialisation de la liste contenant toutes les cartes du paquet
    private final List<Card> cards = new ArrayList<>();
    
    //constructeur initialisant toutes les cartes du paquet
    public Deck(){
        resetDeck();
    }

    public void resetDeck(){
        cards.clear();
        char[] suite = {'h', 'd', 's', 'c'};

        //boucle permettant de créer le paquet
        for (char c : suite) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(j, c));
            }
        }
    }

    //fonction permettant, à la saisie du code d'une carte, de la récupérer du paquet
    public Card getCardFromDeck(String card){
        int strength;
        char suite;
        char firstChar = card.charAt(0);
        char secondChar = card.charAt(1);
        if (Character.isDigit(firstChar)){
            strength = Character.getNumericValue(card.charAt(0))-2;
        } else {
            switch (firstChar) {
                case 'T' -> strength = 8;
                case 'J' -> strength = 9;
                case 'Q' -> strength = 10;
                case 'K' -> strength = 11;
                case 'A' -> strength = 12;
                default -> {
                    System.out.println("Erreur de saisie");
                    return getRandomCard();
                }
            }
        }
        if ("scdh".indexOf(secondChar) != -1){
            suite = secondChar;
            Card newCard = new Card(strength, suite);
            for(int i = 0; i < cards.size(); i++){
                if( cards.get(i).strength == newCard.strength && cards.get(i).suite == newCard.suite){
                    cards.remove(i);
                }
            }
            return newCard;
        } else {
            System.out.println("Erreur de saisie");
            return getRandomCard();
        }
    }

    //permet de récupérer une carte aléatoire du paquet et de la supprimer du paquet
    public Card getRandomCard(){
        int cardPicker = (int) (Math.random()*cards.size());
        Card card = cards.get(cardPicker);
        cards.remove(cardPicker);
        return card;
    }
}
