package com.teamolympusgames.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	List<Card> cards;
	Card upCard;

	public Hand() {
		upCard = null;
		cards = new ArrayList<Card>();
	}

	public void addCard(Card newCard) {
		if (upCard == null)
			upCard = newCard;
		cards.add(newCard);
	}

	public Card getUpCard() {
		return upCard;
	}

	public boolean isBlackJack() {

		boolean blackJack = cards.size() == 2 && getHandValue() == 21;
		return blackJack;

	}

	public int getHandValue() {
		int total = 0;

		for (Card card : cards) {
			int cardValue = card.getRank();

			if (cardValue > 10)
				cardValue = 10;

			if (cardValue == 1)
				cardValue = 11;

			total += cardValue;
		}

		return total;
	}

	@Override
	public String toString() {
		String retVal = "";
		for (Card card : cards) {
			retVal += card.toString() + ",";
		}

		retVal += "total=" + this.getHandValue();
		return retVal;
	}
}
