package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class BattlecryDecoration extends HearthcloneAbilitiesDecorator {
	public BattlecryDecoration(Card card) {
		super(card);
		keywords = HearthcloneAbilitiesDecorator.BATTLECRY;
		name = "Battlecry";
	}
	public String toString(){
		return card.toString()+" "+name;
	}
}
