package com.chasebabbitt.cardgame.cards.hearthclone;

import com.chasebabbitt.cardgame.cards.AbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.Card;

public class HearthcloneAbilitiesDecorator extends AbilitiesDecorator{
	
	// Bit masks for Hearthclone keywords
	public static final int TAUNT = 1;
	public static final int DIVINESHIELD = 2;
	public static final int ENRAGE = 4;
	public static final int BATTLECRY = 8;
	public static final int CHARGE = 16;
	public static final int DEATHRATTLE = 32;
	public static final int STEALTH = 64;
	public static final int WINDFURY = 128;
	
	public HearthcloneAbilitiesDecorator(Card card) {
		super(card);
	}
	
	public void dealDamage(int damage){
		card.dealDamage(damage);
	}
	public int getHealth(){
		return card.getHealth();
	}
	public void healDamage(int heal){
		card.healDamage(heal);
	}

}
