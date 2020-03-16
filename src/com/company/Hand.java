package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Hand {

    ArrayList<Integer> handList = new ArrayList<>();
    String[] suits = {"Clubs", "Diamonds", "Spades", "Hearts"};

    Random r1 = new Random();
    int max1 = 11;
    int min1 = 2;
    int max2 = 3;
    int min2 = 0;

    //Creates a new hand by removing the hold hand and dealing a new one
    public Hand() {
        clearHand();
        dealHand();
    }

    //Clears the previous hand
    private void clearHand() {
        handList.clear();
    }

    //Deals a hand with 2 cards
    public void dealHand() {
        dealCard();
        dealCard();
    }

    //Adds another card to the hand
    public void dealCard() {
        handList.add((r1.nextInt(max1 - min1) + 1) + min1);
    }

    //Prints out the players hand
    public void printCard() {
        System.out.println("Current Hand: ");
        printHand();
        System.out.println("Your total value is: " + getTotal());
        System.out.println();
    }

    public void printHand() {
        for(int i = 0; i < handList.size()-1; i++) {
            System.out.print(handList.get(i) + " of " + getSuit() + ", ");
        }
        System.out.print(handList.get(handList.size()-1) + " of " + getSuit());
        System.out.println();
    }

    //Prints out the healers hand
    public void printDealer() {
        System.out.println("Dealer's Hand: ");
        printHand();
        System.out.println("The Dealer's total value is: " + getTotal());
        System.out.println();
    }

    //Assings suit to card
    public String getSuit() {
        return suits[(r1.nextInt(max2 - min2) + 1) + min2];
    }

    //Gets the total for a hand
    public int getTotal() {
        int total = 0;
        for (Integer integer : handList) {
            total += integer;
        }
        return total;
    }

    //Checks to see if a hand has gone bust
    public boolean isBust() {
        boolean ret = false;
        if(getTotal() > 21) {
            ret = true;
        }
        return ret;
    }

    //Displays just the first card of a hand (for dealers)
    public void showDealer() {
        System.out.println("The Dealer has " + handList.get(0) + " of " + getSuit());
    }

    //Deals another card to the dealer if his total is under 16
    public void dealDealer() {
        if(getTotal() < 16) {
            dealCard();
        }
    }
}
