package fr.cybercicco.handcalctest;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandStrTest {

    private static List<Card> strFlu;
    private static List<Card> quads;
    private static List<Card> full;
    private static List<Card> flu;
    private static List<Card> str;
    private static List<Card> trips;
    private static List<Card> dpair;
    private static List<Card> pair;
    private static List<Card> hcard;
    static final int PAIR = 1;
    static final int D_PAIR = 2;
    static final int TRIPS = 3;
    static final int STRAIGHT = 4;
    static final int FLUSH = 5;
    static final int FULL = 6;
    static final int QUADS = 7;
    static final int STRAIGHT_FLUSH = 8;

    @BeforeAll
    public static void makeLists(){
        strFlu = new ArrayList<>();
        quads = new ArrayList<>();
        full = new ArrayList<>();
        flu = new ArrayList<>();
        str = new ArrayList<>();
        trips = new ArrayList<>();
        dpair = new ArrayList<>();
        pair = new ArrayList<>();
        hcard = new ArrayList<>();

        strFlu.add(new Card(34, 1));
        strFlu.add(new Card(33, 1));
        strFlu.add(new Card(32,1));
        strFlu.add(new Card(31,1));
        strFlu.add(new Card(30,1));

        quads.add(new Card(30,1));
        quads.add(new Card(30,2));
        quads.add(new Card(30,3));
        quads.add(new Card(30,4));
        quads.add(new Card(25,2));

        full.add(new Card(29,1));
        full.add(new Card(29,2));
        full.add(new Card(25,1));
        full.add(new Card(25,2));
        full.add(new Card(25,3));

        flu.add(new Card(14,1));
        flu.add(new Card(6,1));
        flu.add(new Card(5,1));
        flu.add(new Card(4,1));
        flu.add(new Card(3,1));

        str.add(new Card(34, 1));
        str.add(new Card(33, 1));
        str.add(new Card(32,1));
        str.add(new Card(31,2));
        str.add(new Card(30,1));

        trips.add(new Card(30,1));
        trips.add(new Card(30,2));
        trips.add(new Card(30,3));
        trips.add(new Card(26,4));
        trips.add(new Card(25,2));

        dpair.add(new Card(14,1));
        dpair.add(new Card(14,2));
        dpair.add(new Card(13,1));
        dpair.add(new Card(2,2));
        dpair.add(new Card(2,3));

        pair.add(new Card(29,1));
        pair.add(new Card(29,2));
        pair.add(new Card(26,1));
        pair.add(new Card(25,2));
        pair.add(new Card(22,3));

        hcard.add(new Card(34, 1));
        hcard.add(new Card(33, 1));
        hcard.add(new Card(32,1));
        hcard.add(new Card(31,2));
        hcard.add(new Card(29,1));

    }

    @Test
    public void testHandStrength(){
        assertEquals( (int) HandStrengthCalc.getHandStrength(strFlu), STRAIGHT_FLUSH);
        assertEquals( (int) HandStrengthCalc.getHandStrength(quads), QUADS);
        assertEquals( (int) HandStrengthCalc.getHandStrength(full), FULL);
        assertEquals( (int) HandStrengthCalc.getHandStrength(flu), FLUSH);
        assertEquals( (int) HandStrengthCalc.getHandStrength(str), STRAIGHT);
        assertEquals( (int) HandStrengthCalc.getHandStrength(trips), TRIPS);
        assertEquals( (int) HandStrengthCalc.getHandStrength(dpair), D_PAIR);
        assertEquals( (int) HandStrengthCalc.getHandStrength(pair), PAIR);
        assertEquals( (int) HandStrengthCalc.getHandStrength(hcard), 0);
    }
    @Test
    public void testStrengthFromDPair(){
        List<Card> dpair2 = new ArrayList<>();
        dpair2.add(new Card(14,1));
        dpair2.add(new Card(14,2));
        dpair2.add(new Card(3,1));
        dpair2.add(new Card(3,2));
        dpair2.add(new Card(2,3));

        double dpairStr = HandStrengthCalc.getHandStrength(dpair);
        double dpair2Str = HandStrengthCalc.getHandStrength(dpair2);
        assert  dpair2Str > dpairStr;
    }

    @Test
    public void testStrengthFromFlush(){
        List<Card> flu2 = new ArrayList<>();
        flu2.add(new Card(13,1));
        flu2.add(new Card(12,1));
        flu2.add(new Card(11,1));
        flu2.add(new Card(10,1));
        flu2.add(new Card(8,1));

        assert HandStrengthCalc.getHandStrength(flu) > HandStrengthCalc.getHandStrength(flu2);
    }
}
