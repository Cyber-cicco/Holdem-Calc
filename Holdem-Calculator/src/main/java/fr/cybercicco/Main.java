package fr.cybercicco;

import fr.cybercicco.deckentities.*;
import fr.cybercicco.handcalculator.EquityCalc;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import fr.cybercicco.handcalculator.Showdown;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Deck deck = new Deck();
        MainPlayer player1 = new MainPlayer();
        MainPlayer player2 = new MainPlayer();
        EquityCalc eqc = new EquityCalc(deck, board);

        player1.setHand(deck, "KsKh");
        player2.setHand(deck, "AsAh");

        Date date = new Date();
        long time1 = date.getTime();
        System.out.println(eqc.getEquity(player1,player2));
        System.out.println(new Date().getTime()-time1);



    }
}