package fr.cybercicco.handcalctest;

import fr.cybercicco.deckentities.Card;
import fr.cybercicco.handcalculator.HandStrengthCalc;
import fr.cybercicco.utils.StringCardConverter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HandStrTest {
    private static final int PAIR = 1;
    private static final int D_PAIR = 2;
    private static final int TRIPS = 3;
    private static final int STRAIGHT = 4;
    private static final int FLUSH = 5;
    private static final int FULL = 6;
    private static final int QUADS = 7;
    private static final int STRAIGHT_FLUSH = 8;
    private static final String[] strFlu1 = {"As", "Ks", "Qs", "Js", "Ts"};
    private static final String[] quads1 = {"As", "Ah", "Ad", "Ac", "Ks"};
    private static final String[] full1 = {"Kc", "Ks", "Kd", "3h", "3d"};
    private static final String[] flu1 = {"As", "6s", "4s", "3s", "2s"};
    private static final String[] str1 = {"6s", "5s", "4h", "3s", "2s"};
    private static final String[] trips1 = {"As", "Ah", "Ad", "3s", "2s"};
    private static final String[] dpair1 = {"As", "Ah", "Qs", "2h", "2s"};
    private static final String[] pair1 = {"As", "Ah", "Qs", "Js", "Ts"};
    private static final String[] hcard1 = {"As", "Ks", "Qs", "Js", "9h"};

    public static List<Card> createCardList(String[] cards){
        return Arrays.stream(cards).map(StringCardConverter::getCardFromString).toList();
    }

    @Test
    public void testHandStrength(){

        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(strFlu1)), STRAIGHT_FLUSH);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(quads1)), QUADS);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(full1)), FULL);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(flu1)), FLUSH);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(str1)), STRAIGHT);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(trips1)), TRIPS);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(dpair1)), D_PAIR);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(pair1)), PAIR);
        assertEquals( (int) HandStrengthCalc.getHandStrength(createCardList(hcard1)), 0);
    }
    @Test
    public void testStrengthFromDPair(){
        String[] dpair2 = {"As", "Ah", "3s", "3d", "2d"};

        double dpairStr = HandStrengthCalc.getHandStrength(createCardList(dpair1));
        double dpair2Str = HandStrengthCalc.getHandStrength(createCardList(dpair2));
        assert  dpair2Str > dpairStr;
    }

    @Test
    public void testStrengthFromFlush(){
        String[] flu2 = {"Ks", "Qs", "Js", "Ts", "8s"};
        String[] flu3 = {"Qs", "Ts", "8s", "5s", "4s"};
        String[] flu4 = {"Ts", "9s", "8s", "5s", "4s"};
        assert HandStrengthCalc.getHandStrength(createCardList(flu1)) > HandStrengthCalc.getHandStrength(createCardList(flu2));
        assert HandStrengthCalc.getHandStrength(createCardList(flu2)) > HandStrengthCalc.getHandStrength(createCardList(flu3));
        assert HandStrengthCalc.getHandStrength(createCardList(flu3)) > HandStrengthCalc.getHandStrength(createCardList(flu4));
    }

    @Test
    public void testStrengthFromHcard(){
        String[] hcard2 = {"As", "Ks", "Qs", "Js", "8h"};
        String[] hcard3 = {"As", "Ks", "Qs", "Ts", "9h"};
        assert HandStrengthCalc.getHandStrength(createCardList(hcard1)) > HandStrengthCalc.getHandStrength(createCardList(hcard2));
        assert HandStrengthCalc.getHandStrength(createCardList(hcard2)) > HandStrengthCalc.getHandStrength(createCardList(hcard3));
    }

    @Test
    void testFull(){
        String[] full2 = {"Kd", "Kh", "Ks", "2h", "2d"};
        String[] full3 = {"Ad", "Ah", "2s", "2h", "2d"};
        String[] full4 = {"3d", "3h", "3s", "2h", "2d"};
        assert HandStrengthCalc.getHandStrength(createCardList(full1)) > HandStrengthCalc.getHandStrength(createCardList(full2));
        assert HandStrengthCalc.getHandStrength(createCardList(full2)) > HandStrengthCalc.getHandStrength(createCardList(full3));
        assert HandStrengthCalc.getHandStrength(createCardList(full4)) > HandStrengthCalc.getHandStrength(createCardList(full3));
    }

    @Test
    void testPair(){
        String[] pair2 = {"As", "Ah", "Qs", "Js", "9s"};
        String[] pair3 = {"As", "Ah", "Ks", "3s", "2s"};
        assert HandStrengthCalc.getHandStrength(createCardList(pair1)) > HandStrengthCalc.getHandStrength(createCardList(pair2));
        assert HandStrengthCalc.getHandStrength(createCardList(pair3)) > HandStrengthCalc.getHandStrength(createCardList(pair2));
    }

    @Test
    void testWheel(){
        String[] wheel = {"As", "5s", "4h", "3h", "2h"};
        String[] fluWheel = {"As", "5s", "4s", "3s", "2s"};
        String[] str = {"6s", "5s", "4h", "3h", "2h"};
        assertTrue(HandStrengthCalc.getHandStrength(createCardList(wheel)) == STRAIGHT);
        assertTrue(HandStrengthCalc.getHandStrength(createCardList(fluWheel)) == STRAIGHT_FLUSH);
        assertTrue(HandStrengthCalc.getHandStrength(createCardList(str)) > HandStrengthCalc.getHandStrength(createCardList(wheel)));
    }
}
