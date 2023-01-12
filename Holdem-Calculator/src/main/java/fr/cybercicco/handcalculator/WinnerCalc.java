package fr.cybercicco.handcalculator;

import fr.cybercicco.deckentities.Card;

import java.util.List;

public class WinnerCalc {

    public static List<Card> getWinner(List<Card> cardsPlayer1, List<Card> cardsPlayer2){
        for(int i = 0; i < 5; i++){
            if(cardsPlayer1.get(i).strength > cardsPlayer2.get(i).strength){
                return cardsPlayer1;
            } else if(cardsPlayer1.get(i).strength < cardsPlayer2.get(i).strength){
                return cardsPlayer2;
            }
        }
        return cardsPlayer1;
    }
}
