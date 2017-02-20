package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class TauntDecoration extends HearthcloneAbilitiesDecorator{

	public TauntDecoration(Card card) {
		super(card);
		keywords = HearthcloneAbilitiesDecorator.TAUNT;
	}
	
}
