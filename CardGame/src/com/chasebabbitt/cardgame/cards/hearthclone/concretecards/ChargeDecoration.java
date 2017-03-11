package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class ChargeDecoration extends HearthcloneAbilitiesDecorator {
	public ChargeDecoration(Card card) {
		super(card);
		keywords = HearthcloneAbilitiesDecorator.CHARGE;
		name = "Charge";
	}
	public String toString(){
		return card.toString()+" "+name;
	}
}
