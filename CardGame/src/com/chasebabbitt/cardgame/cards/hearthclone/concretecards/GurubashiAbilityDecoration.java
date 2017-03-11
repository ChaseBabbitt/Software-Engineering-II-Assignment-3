package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.player.Player;

public class GurubashiAbilityDecoration extends HearthcloneAbilitiesDecorator{

	public GurubashiAbilityDecoration(Card card) {
		super(card);
		attackpoints = 0;
		
	}
	public void dealDamage(int damage, Player owner, Player opponent){
		if(damage>0){
			System.out.println("Gurubashi Berserker's ability triggered");
			attackpoints+=3;
		}
		card.dealDamage(damage);
		
	}
	public int getAttackPoints(){
		return card.getAttackPoints()+attackpoints;
	}
	
	public String toString(){
		String cardstats = new String();
		cardstats= getName() +" "+"("+getAttackPoints()+","+getHealth()+")  Whenever this minion takes damage, gain +3 Attack.";
		return cardstats;
	}
	
}
