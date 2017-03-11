package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.player.Player;

public class DamageDecoration extends HearthcloneAbilitiesDecorator{

	int damage;
	public DamageDecoration(Card card) {
		super(card);
		
	}

	public void dealDamage(int damage, Player owner, Player opponent){
		this.damage += damage;
	}
	/*public void dealDamage(int damage){
		this.damage += damage;
	}*/
	
	public int getHealth(){
		return card.getDefensePoints()-damage;
	}
	
	/**
	 * Heals an amount of damage from the card
	 * Will not heal more damage than card has health
	 * @param heal the maximum amount that can be healed
	 */
	public void healDamage(int heal){
		while(damage>0 && heal > 0){
			damage--;
			heal--;
		}
	}
}
