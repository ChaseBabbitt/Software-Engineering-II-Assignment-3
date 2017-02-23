package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class WindfuryDecoration extends HearthcloneAbilitiesDecorator{

	public boolean windfuryattack;
	public WindfuryDecoration(Card card) {
		super(card);
		windfuryattack = false;
		keywords = HearthcloneAbilitiesDecorator.WINDFURY;
		name = "Windfury";
		
	}
	public void untap(){
		windfuryattack=true;
		card.untap();

	}
	public void exhaust(){
		if(windfuryattack==true){
			windfuryattack = false;
			return;
		}
		card.exhaust();
	}
	public String toString(){
		return card.toString()+" "+name;
	}
	

}
