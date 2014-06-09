package com.teamolympusgames.blackjack;

public class Card {
	private final int rank;
	private final Suit suit;

	public Card(int rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	
	@Override
	public String toString(){
		if(this.rank==1)
			return "A of " + this.suit;
		
		if(rank==11)
			return "J of " + this.suit;

		if(rank==12)
			return "Q of " + this.suit;

		if(rank==13)
			return "K of " + this.suit;
		
		return this.rank + " of " + this.suit;
	}
}
