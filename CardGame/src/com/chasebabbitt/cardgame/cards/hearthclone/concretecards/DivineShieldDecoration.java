package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.player.Player;

public class DivineShieldDecoration extends HearthcloneAbilitiesDecorator{
	
	private boolean shieldactive;
	public DivineShieldDecoration(Card card) {
		super(card);
		shieldactive = true;
		keywords = HearthcloneAbilitiesDecorator.DIVINESHIELD;
		name = "Divine Shield";
	}
	public void dealDamage(int damage, Player owner, Player opponent){
		if(shieldactive){
			shieldactive = false;
			keywords = 0;
			return;
		}
		card.dealDamage(damage,owner,opponent);
	}
	public String toString(){
		if(shieldactive)
			return card.toString()+" "+name;
		return card.toString();
	}

}
