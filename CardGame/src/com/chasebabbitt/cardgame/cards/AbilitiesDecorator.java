package com.chasebabbitt.cardgame.cards;

import com.chasebabbitt.cardgame.player.Player;

public abstract class AbilitiesDecorator extends Card {
	protected Card card;
	/**
	 * Constructor for decoration classes
	 * @param card the card the class will be decorating
	 */
	public AbilitiesDecorator(Card card){
		//a reference to the next decoration or base card
		this.card = card;


	}
	//Getter method for attackpoints
	public int getAttackPoints(){
		return card.getAttackPoints();
	}
	
	//Getter method for defensepoints
	public int getDefensePoints(){
		return card.getDefensePoints();
	}
		
	//Getter method for cost;
	public int getCost(){
		return card.getCost();
	}
	/**
	 * Getter method for keywords
	 * @return returns a bitmask consisting of all the keywords the card has bitwise or'd together
	 */
	public int getKeywords(){
		
		return card.getKeywords()|keywords;
	}
	/**
	 * Method for determining if a card has a specific keyword
	 * @param An integer with a single nonzero bit, corresponding with one of the static keywords located in the Card class
	 * @return returns true if a decoration is encountered with the keyword, otherwise it will keeping searching deeper
	 */
	public boolean hasKeyword(int keyword){
		if(this.keywords==keyword)
			return true;
		else
			return card.hasKeyword(keyword);
		
	}
	//Getter method for card name
	public String getName(){
		return card.getName();
	}
	//Method for printing out the stats of the card to the console
	public String toString(){
		return card.toString();
	}
	//Getter method for tapped
	public boolean tappedStatus(){
		return card.tappedStatus();
	}
	//Setter method for tapped
	public void exhaust(){
		card.exhaust();
	}
	//Setter method for tapped, sends the method down to the base card
	public void untap(){
		card.untap();
	}
	/**
	 * Base method for comes into play abilities, passes the instruction on to the next card
	 * Override this method to create a decoration that will have a comes into play effect.
	 */
	public void comesIntoPlay(Player owner, Player opponent){
		card.comesIntoPlay(owner, opponent);
	}

	

}
