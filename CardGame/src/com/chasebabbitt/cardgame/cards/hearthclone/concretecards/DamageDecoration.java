package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class DamageDecoration extends HearthcloneAbilitiesDecorator{

	int damage;
	public DamageDecoration(Card card) {
		super(card);
		
	}

	public void dealDamage(int damage){
		this.damage += damage;
	}
	
	public int getHealth(){
		return card.getDefensePoints()-damage;
	}
}
