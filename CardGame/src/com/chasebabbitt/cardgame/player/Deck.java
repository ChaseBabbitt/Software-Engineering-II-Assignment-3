package com.chasebabbitt.cardgame.player;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;


public class Deck {
	
	ArrayList<Card> cards;
	
	CardFactory cardgenerator;//= new HexCardFactory();
	public Deck(CardFactory cardgenerator){
		this.cardgenerator = cardgenerator;
		cards = new ArrayList<Card>();
		for(int i = 0;i<60;i++){
			cards.add(cardgenerator.createCard());
		}		
	}
	/**
	 * Returns the number of cards left in the deck
	 * @return the number of cards in the deck
	 */
	public int size(){
		return cards.size();
	}
	
	/**
	 * Draws the card off the top of the deck
	 * @return
	 */
	public Card draw(){
		return cards.remove(0);
	}

}
