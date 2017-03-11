package com.chasebabbitt.cardgame.cards;

import com.chasebabbitt.cardgame.player.Player;

public abstract class Card {


	// Bit masks for card keywords
	public static final int FLY = 1;
	public static final int SKYGUARD = 2;
	public static final int CRUSH = 4;
	public static final int STEADFAST = 8;
	public static final int SWIFTSTRIKE = 16;
	public static final int DEATHTOUCH = 32;
	public static final int SPEED = 64;
	public static final int DEFENDER = 128;
	
	
	protected String name;
	protected int attackpoints;
	protected int defensepoints;
	protected int cost;
	protected int keywords;
	protected boolean tapped;
	protected boolean blocked;

	public Card(){
		keywords = 0;
		tapped = false;
		blocked = false;
	}
	//Getter method for attackpoints
	public int getAttackPoints(){
		return attackpoints;
	}
	
	//Getter method for defensepoints
	public int getDefensePoints(){
		return defensepoints;
	}
	
	//Getter method for cost;
	public int getCost(){
		return cost;
	}
	//Getter method for the name of the card
	public String getName(){
		return name;
	}
	public String toString(){
		String cardstats = new String();
		cardstats= name +" "+"("+attackpoints+","+defensepoints+")";
		return cardstats;
	}
	//Getter method for the keywords bitfield
	public int getKeywords(){
		return keywords;
	}
	/**
	 * getter method for specific keywords
	 * @param keyword keyword is a bitmask corresponding with the static keyword variables above
	 * @return as this this is an abstract of a base component, the base case will always return false,
	 *  as it has no keywords
	 */
	public boolean hasKeyword(int keyword){
		return false;
	}
	public boolean tappedStatus(){
		return tapped;
	}
	public void exhaust(){
		tapped = true;
	}
	public void untap(){
		tapped = false;
	}
	//Base case of a comes into play ability, does nothing
	public void comesIntoPlay(Player owner, Player opponent){} //
	public void dealDamage(int damage) {}
	public void dealDamage(int damage, Player owner, Player opponent){}
	public int getHealth() {
		return defensepoints;
	}
	public void healDamage(int health) {
		// TODO Auto-generated method stub
		
	}
}