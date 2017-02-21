package com.chasebabbitt.cardgame.player;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;

public class Hand {
	ArrayList<Card> cards;
	public Hand(){
		cards = new ArrayList<Card>();
	}
	/**
	 * Places a new card in the hand
	 * @param card a new Card being drawn to the hand
	 */
	public void draw(Card card){
		cards.add(card);
	}
	public void toConsole() {
		for(Card c:cards){
			System.out.println(c.toString());
		}
	}
	public Card getCard(){
		if(cards.size()>0)
			return cards.remove(0);
		return null;
	}
		//Added a way to remove cards from hand
	public Card playCard(Card card){
		cards.remove(card);
		return card;
	}
}
