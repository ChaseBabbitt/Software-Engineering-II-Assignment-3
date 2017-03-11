/*
 * David Ahmad
 */
package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;

public class BigGameHunter extends Card {
	public BigGameHunter(){
		name = "Big Game Hunter";
		attackpoints = 4;
		defensepoints = 2;
		cost = 5;
		keywords = 0;
		tapped=true;
		//Battlecry: Destroy a minion with 7 or more attack
	}

}
