package com.teamolympusgames.blackjack;

import java.io.IOException;

public class Blackjack {

	public static void main(String[] args) throws IOException {

		// Brand new Shoe with 5 decks
		Shoe myDeck = new Shoe(5);

		int totalMoney = 100;
		int currentBet = 1;

		while (true) {
			System.out.println("********************");
			System.out.println("");
			System.out.println("New game!");
			System.out.format("Your total money is %d\n", totalMoney);

			do {
				currentBet = getNewBet(currentBet);
				if (currentBet > totalMoney) {
					System.out
							.format("You must bet less than %d\n", totalMoney);
				}
			} while (currentBet > totalMoney);
			// start with empty hands
			Hand myHand = new Hand();
			Hand dealerHand = new Hand();

			// deal one card to each
			myHand.addCard(myDeck.getNextCard());
			dealerHand.addCard(myDeck.getNextCard());

			// deal second card to each
			myHand.addCard(myDeck.getNextCard());
			dealerHand.addCard(myDeck.getNextCard());

			// if player has blackjack, he wins
			if (myHand.isBlackJack()) {
				System.out.println("BlackJack, you win!");
				totalMoney += currentBet * 2;
			}
			// if dealer has blackjack, player losers
			else if (dealerHand.isBlackJack()) {
				System.out.println("Dealer BlackJack you lose!");
				totalMoney -= currentBet;
			}
			// player's turn; hit til busted or stand
			else {
				int playerValue = playerTurn(myDeck, myHand, dealerHand);

				// dealer's turn
				if (playerValue <= 21) {
					HandResult result = dealerTurn(myDeck, myHand, dealerHand);
					if (result == HandResult.PLAYER_WINS) {
						totalMoney += currentBet;
					} else if (result == HandResult.DEALER_WINS) {
						totalMoney -= currentBet;
					}

				} else {
					System.out.println("Busted!");
					totalMoney -= currentBet;
				}
			}

			if (totalMoney < 1) {
				System.out
						.println("You are broke! You are kicked out of the casino!");
				break;
			}
		}

	}

	private static int getNewBet(int currentBet) throws IOException {

		int newBet = currentBet;
		System.out.println("");
		System.out.format("Place your bet (%d):", currentBet);

		StringBuffer sb = new StringBuffer();
		int charRead = System.in.read();
		while (charRead != 10) {
			sb.append((char) charRead);
			charRead = System.in.read();
		}

		if (sb.length() > 0) {
			newBet = Integer.parseInt(sb.toString());
		}

		System.out.format("Your bet is %d\n", newBet);

		return newBet;
	}

	private static HandResult dealerTurn(Shoe myDeck, Hand myHand,
			Hand dealerHand) {
		HandResult res = HandResult.DEALER_WINS;

		System.out.println("You have: " + myHand);
		System.out.println("Dealer has: " + dealerHand);

		// dealer hits until he gets 17 or more
		while (dealerHand.getHandValue() < 17) {
			dealerHand.addCard(myDeck.getNextCard());
			System.out.println("Dealer has: " + dealerHand);
		}

		// who wins?
		if (myHand.getHandValue() > dealerHand.getHandValue()
				|| dealerHand.getHandValue() > 21) {
			System.out.println("You win!");
			res = HandResult.PLAYER_WINS;
		} else if (myHand.getHandValue() == dealerHand.getHandValue()) {
			System.out.println("You push!");
			res = HandResult.PUSH;

		} else {
			System.out.println("You lose!");
		}
		System.out.println("You have: " + myHand);
		System.out.println("Dealer has: " + dealerHand);
		return res;
	}

	private static int playerTurn(Shoe myDeck, Hand myHand, Hand dealerHand)
			throws IOException {
		boolean myTurn = true;
		while (myTurn) {
			System.out.println(myHand);
			System.out.println("Dealer shows: " + dealerHand.upCard);

			// what is your command?
			System.out.println("(H)it or (S)tand?");
			int input = System.in.read();

			// this is the "enter" that is hit after H or S
			int ignoreMe = System.in.read();
			char key = (char) input;
			if (key == 'h' || key == 'H') {
				// HIT
				myHand.addCard(myDeck.getNextCard());
			} else if (key == 's' || key == 'S') {
				// STAND
				myTurn = false;
			} else {
				// some other key, try again
			}

			// if over 21, then busted!
			if (myHand.getHandValue() > 21) {
				myTurn = false;
			}
		}

		return myHand.getHandValue();
	}
}
