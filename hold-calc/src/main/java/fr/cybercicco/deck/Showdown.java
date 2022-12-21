package fr.cybercicco.deck;


import java.util.ArrayList;
import java.util.List;


public class Showdown {

    //fonction permettant de changer l'ordre de deux cartes
    private void intervertCards(Card card1, Card card2){
        char cardSuite1 = card1.suite;
        card1.suite = card2.suite;
        card2.suite = cardSuite1;
        card1.strength += card2.strength;
        card2.strength = card1.strength - card2.strength;
        card1.strength = card1.strength - card2.strength;

    }

    // fonction privée permettant de trier les mains par force des cartes dans l'ordre décroissant
    private void sortCardsByStrength(List<Card> cards){
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 0; i < cards.size()-1; i++){
                if(cards.get(i).strength < cards.get(i+1).strength){
                    sorted = false;
                    Card card1 = cards.get(i);
                    Card card2 = cards.get(i+1);
                    intervertCards(card1, card2);
                }
            }
        }
    }

    //je veux mourrir
    private void createAllPossibleHands(int[] pointers,  List<Card> cards, List<List<Card>> combinations){
        int lastIndex = pointers.length-1;
        List<Card> currentCombination = new ArrayList<>();
        while(pointers[lastIndex] < cards.size()) {
            for(int i = 0; i<cards.size(); i++){
                boolean write = true;
                for (int pointer : pointers){
                    if(pointer == i){
                        write = false;
                    }
                }
                if (write){
                    currentCombination.add(cards.get(i));
                }
            }
            pointers[lastIndex]++;
            combinations.add(new ArrayList<>(currentCombination));
            currentCombination.clear();
        } 
        for(int i = pointers.length-1; i>=0; i--){
            if (i != pointers.length-1 && pointers[i] != pointers[i+1] -1 && pointers[lastIndex] != lastIndex){
                pointers[i]++;
                for(int j = i+1; j< pointers.length; j++){
                    pointers[j] = pointers[j-1]+1;
                }
                createAllPossibleHands(pointers, cards, combinations);
            }
        }
    }


    //trier l'array. La combinaison la plus haute dans la liste avec le plus haut score sera nécessairement la meilleure, si le tri est bien fait
    public List<Card> getBestHand(List<Card> cards){

        //Tri de la liste permettant d'avoir un tableau de combinaisons ordonné
        sortCardsByStrength(cards);

        //initialisation des variables permettant de créer une liste de toutes les combinaisons possibles
        int nbPointers = cards.size()-5;
        int[] pointers = new int[nbPointers];
        List<List<Card>> lstAllCombination = new ArrayList<>();
        for(int i = 0; i < nbPointers; i++){
            pointers[i] = i;
        }

        //appel de la fonction rembplissant la liste de toutes les combinaisons de main possible
        createAllPossibleHands(pointers, cards, lstAllCombination);

        //algorithme permettant de déterminer la combinaison de main la plus forte
        int lstSize = lstAllCombination.size();
        int[] bestStrength = {lstSize-1, 0};
        for(int i= lstSize-1; i>=0; i--){
            int strengthOfCurrentHand = getHandStrength(lstAllCombination.get(i));
            if( strengthOfCurrentHand > bestStrength[1]){
                bestStrength[0] = i;
                bestStrength[1] = strengthOfCurrentHand;
            }
        }
        return lstAllCombination.get(bestStrength[0]);
    }


    public int getHandStrength(List<Card> cards){
        
        // bloc de code servant à examiner le contenu de la main et à trier pour mettre les paires les plus fortes et les brelans devant, pour après aider à la comparaison de la force des mains
        int pair = 0;
        boolean trips = false;
        boolean flush = true;
        boolean straight = true;
        boolean quads = false;

        for(int i = 1; i<5; i++){
            if(straight && (cards.get(i).strength != cards.get(i-1).strength-1)){
                straight = false;
            }
            if( flush && (cards.get(i).suite != cards.get(i-1).suite)){
                flush = false;
            }
        }
        if(cards.get(0).strength == 12 && cards.get(1).strength == 3 && cards.get(2).strength == 2 && cards.get(3).strength == 1 && cards.get(4).strength == 0){
            straight = true;
            Card card1 = cards.get(0);
            cards.remove(0);
            cards.add(card1);
        }

        for (int i = 1; i<5; i++){
            if(trips && (cards.get(i).strength == cards.get(i-3).strength)){
                quads = true;
                trips = false;
                Card card = cards.get(i);
                cards.remove(i);
                cards.add(0,card);

            } else
            if(pair > 0 && ((cards.get(i).strength == cards.get(0).strength)||(pair == 2 && cards.get(i).strength == cards.get(2).strength))){
                trips = true;
                pair -= 1;
                if(pair == 1 && cards.get(i).strength == cards.get(2).strength){
                    for(int j = 0; j<3; j++){
                        Card card = cards.get(4);
                        cards.remove(4);
                        cards.add(0,card);
                    }
                } else {
                    Card card1 = cards.get(i);
                    cards.remove(i);
                    cards.add(0,card1);
                }
            } else
            if(cards.get(i).strength == cards.get(i-1).strength){
                if(pair > 0){
                    Card card1 = cards.get(i);
                    Card card2 = cards.get(i-1);
                    cards.remove(i);
                    cards.remove(i-1);
                    cards.add(2, card1);
                    cards.add(2, card2);
                } else if (!trips){
                    Card card1 = cards.get(i);
                    Card card2 = cards.get(i-1);
                    cards.remove(i);
                    cards.remove(i-1);
                    cards.add(0, card1);
                    cards.add(0, card2);
                }
                pair +=1;
            }
        }

        // bloc de code servant à déterminer la force de la main
        if(straight && flush){
            return 8;
        } else if ( quads){
            return 7;
        } else if (pair == 1 && trips){
            return 6;
        } else if (flush){
            return 5;
        } else if (straight){
            return 4;
        } else if (trips){
            return 3;
        } else if (pair == 2){
            return 2;
        } else if (pair == 1){
            return 1;
        } else {
            return 0;
        }
    }

    //code permettant de déterminer laquelle de deux mains est la meilleure
    public float getWinner(List<Card> player1, List<Card> player2){
        int strengthPlayer1 = getHandStrength(player1);
        int strengthPlayer2 = getHandStrength(player2);

        if (strengthPlayer1 > strengthPlayer2){
            return 1;
        } else if (strengthPlayer2 > strengthPlayer1){
            return 0;
        } else {
            for(int i = 0; i < 5; i++){
                if(player1.get(i).strength > player2.get(i).strength){
                    return 1;
                } else if(player1.get(i).strength < player2.get(i).strength){
                    return 0;
                }
            }
            return 0.5f;
        }
    }


    public float compareHands(List<Card> communityCards, List<Card> player1Cards, List<Card> player2Cards){
        List<Card> lstPlayer1Cards = new ArrayList<>(player1Cards);
        List<Card> lstPlayer2Cards = new ArrayList<>(player2Cards);
        List<Card> lstCommunityCards = new ArrayList<>(communityCards);

        for(Card card : lstCommunityCards){
            lstPlayer1Cards.add(new Card(card.strength, card.suite));
            lstPlayer2Cards.add(new Card(card.strength, card.suite));
        }

        List<Card> lstBestPlayer1Cards = getBestHand(lstPlayer1Cards);
        List<Card> lstBestPlayer2Cards = getBestHand(lstPlayer2Cards);

        return getWinner(lstBestPlayer1Cards, lstBestPlayer2Cards);
    }
}
