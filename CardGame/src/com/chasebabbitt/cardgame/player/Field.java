package com.chasebabbitt.cardgame.player;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hex.HexCardFactory;

public class Field {
	ArrayList<Card> cards;
	CardFactory cardgenerator = new HexCardFactory();
	
	/**
	 * Constructor for Field
	 */
	public Field(){
		cards = new ArrayList<Card>();
	}
	
	/**
	 * Getter method for cards
	 * @return
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	/**
	 * adds a random card to the field
	 */
	public void addCard(){
		cards.add(cardgenerator.createCard());
	}
	/**
	 * 
	 * @param index the index number of the card to be added
	 */
	public void addCard(int index){
		cards.add(cardgenerator.createCard(index));
	}

	/**
	 * 
	 * @return the number of cards in the field
	 */
	public int size(){
		return cards.size();
	}
	
	/**
	 * Removes all cards from the Field
	 */
	public void discard(){
		cards.clear();
	}
	
	/**
	 * Prints the contents of the Field to the console
	 */
	public void toConsole(){
		
		for(Card c:cards){
			System.out.println(c.toString());
		}
	}

	public boolean moveCardToGraveyard(Card card) {
		return cards.remove(card);
		
	}

	public void addCard(Card card) {
		if(card!=null)
			cards.add(card);
		
	}

	
}
