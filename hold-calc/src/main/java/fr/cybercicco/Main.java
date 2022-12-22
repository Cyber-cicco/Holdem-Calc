package fr.cybercicco;

import fr.cybercicco.deck.Board;
import fr.cybercicco.deck.Deck;
import fr.cybercicco.deck.Player;
import fr.cybercicco.handCalc.Showdown;
import fr.cybercicco.handCalc.Calculator;

public class Main {
    
    public static void main(String[] args){

        Calculator calc = new Calculator();
        Deck myDeck = new Deck();
        Showdown showdown = new Showdown();
        Board board = new Board();
        Player player1 = new Player();
        Player player2 = new Player();

        switch (args.length) {
            case 0 -> System.out.println("Erreur, pas de main passée en paramètre");
            case 1 -> System.out.println(calc.getHandEquity(myDeck, showdown, board, player1, player2, args[0]));
            case 2 ->
                    System.out.println(calc.getHandEquity(myDeck, showdown, board, player1, player2, args[0], args[1]));
            default -> System.out.println("Erreur, trop d'arguments passés en paramètres");
        }

    }   
}