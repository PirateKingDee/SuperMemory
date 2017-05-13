package com.googleappliedandroid.supermemory;
public class Card {
	private int cardNum;
	private char suit;   //s = spades c = clubs h = hearts d = diamonds
	public Card (int c, char s) {
		cardNum = c;
		suit = s;

	}
	public int getNum () {return cardNum;}
	public char getSuit () {return suit;}
	public String toString() {
		if (cardNum < 10 && cardNum != 0) {
			return "Card: " + (cardNum + 1) + " " + "Suit: " + suit;
		}
		switch (cardNum) {
		case 0: return "Card: Ace " + "Suit: " + suit;
		case 10: return "Card: Jack " + "Suit: " + suit;
		case 11: return "Card: Queen " + "Suit: " + suit;
		case 12: return "Card: King " + "Suit: " + suit;
		}
		return "";
	}
}
