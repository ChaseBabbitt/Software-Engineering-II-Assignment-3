/*
 * David Ahmad
 */

package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;

public class Ragnarosthefirelord extends Card {
	public Ragnarosthefirelord(){
	name = "Ragnaros the Firelord";
	attackpoints = 8;
	defensepoints = 8;
	cost = 8;
	keywords = 0;
	tapped=true;
	//Can't attack: at the end of your turn, deal 8 damage to a random enemy.
	}
}
