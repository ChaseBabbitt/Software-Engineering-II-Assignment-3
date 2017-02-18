package com.chasebabbitt.cardgame.player;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;

public class Graveyard {
	ArrayList<Card> cards;
	
	/**
	 * Constructor for Graveyard
	 */
	public Graveyard(){
		cards = new ArrayList<Card>();
	}
	
	/**
	 * 
	 * @param card a card to be added to the graveyard
	 */
	public void toGraveyard(Card card){
		cards.add(card);
	}

}
