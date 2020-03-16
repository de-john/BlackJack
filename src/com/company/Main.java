package com.company;

import java.util.Scanner;

public class Main {
    
    static double winnings = 100;
    static int bet = 0;

    public static void main(String[] args) {
        play();
    }

    //Plays the game
    public static void play() {
        String result = "";
        System.out.println("Winnings: " + winnings);
        System.out.println("Place your bet: ");
        setBet();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        playerHand.printCard();
        dealerHand.showDealer();
        System.out.println();
        if(playerHand.getTotal() == 21 && dealerHand.getTotal() != 21) {
            System.out.println("Congratulations, you've won!");
        }
        else {
            System.out.println("You can either hit (h) or stand (s)");

            for(int i = 0; i < 11; i++) {
                if(playerHand.isBust()) {
                    break;
                }
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();
                response = response.toLowerCase();
                if(response.equalsIgnoreCase("hit") || response.equalsIgnoreCase("h")) {
                    playerHand.dealCard();
                    playerHand.printCard();
                }
                else if(response.equalsIgnoreCase("stand") || response.equalsIgnoreCase("s")) {
                    break;
                }
                else{
                    System.out.println("Invalid response, please try again");
                }
            }
            if(playerHand.isBust()) {
                System.out.println("Sorry, you've gone bust!");
            }
            else {
                for(int i = 0; i < 11; i++) {
                    dealerHand.dealDealer();
                }
            }
            System.out.println();
            System.out.println("=======================================");
            System.out.println();
            playerHand.printCard();
            dealerHand.printDealer();
            System.out.println("===================================================================");
            if((playerHand.getTotal() > dealerHand.getTotal() && !playerHand.isBust()) || dealerHand.isBust()) {
                System.out.println("Congratulations, you've won!");
                result = "win";
            }
            else if(playerHand.getTotal() == dealerHand.getTotal()) {
                System.out.println("It's a push!");
                result = "push";
            }
            else {
                System.out.println("Sorry, you've lost");
                result = "lose";
            }
        }
        switch (result) {
            case "win":
                increaseBet();
                break;
            case "lose":
                decreaseBet();
                break;
            case "push":
                returnBet();
                break;
        }
        System.out.println("To play again, type deal (d)");
        Scanner replayScanner = new Scanner(System.in);
        String decision = replayScanner.nextLine().toLowerCase();
        if(decision.equals("d") || decision.equals("deal")) {
            play();
        }
    }

    public static void setBet() {
        Scanner scanner = new Scanner(System.in);
        int bet = 0;
        if(scanner.hasNextInt()) {
            bet = scanner.nextInt();
        }
        else {
            System.out.println("Please enter a number");
            setBet();
        }
        if(bet > winnings) {
            System.out.println("You're betting more than you have, please try again.");
            setBet();
        }
        else {
            Main.bet = bet;
        }
    }

    //Increases the bet if you win
    public static void increaseBet() {
        winnings += (bet * 1.5);
    }

    //Decreases the bet if you lose
    public static void decreaseBet() {
        winnings -= bet;
    }

    //Returns the bet if you push (fairly unnecessary but for consistency's sake
    public static void returnBet() {
        winnings = winnings;
    }
}