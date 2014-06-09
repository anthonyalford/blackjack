package com.teamolympusgames.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shoe {
	List<Card> cards;
	Random randomNumber;

	public Shoe(int numDecks) {
		randomNumber = new Random();
		cards = new ArrayList<Card>();

		Card newCard;

		for (int deck = 0; deck < numDecks; deck++) {
			for (int i = 1; i <= 13; i++) {
				newCard = new Card(i, Suit.HEARTS);
				cards.add(newCard);

				newCard = new Card(i, Suit.DIAMONDS);
				cards.add(newCard);

				newCard = new Card(i, Suit.SPADES);
				cards.add(newCard);

				newCard = new Card(i, Suit.CLUBS);
				cards.add(newCard);
			}
		}
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card getNextCard() {
		if (cards.isEmpty())
			return null;

		int deckSize = cards.size();
		int index = randomNumber.nextInt(deckSize);
		Card nextCard = cards.remove(index);
		return nextCard;
	}

}
