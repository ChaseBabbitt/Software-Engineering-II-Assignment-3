package com.chasebabbitt.cardgame.cards;

public interface CardFactory {
	
	public Card createCard(); //Creates a random card
	public Card createCard(int index); //Creates a specific card by its index number
	public Card createCard(String cardname); //Creates a specific card by its name

}
