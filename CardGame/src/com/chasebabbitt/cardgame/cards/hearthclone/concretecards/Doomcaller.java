/*
 * David Ahmad
 */

package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;

public class Doomcaller extends Card {
	public Doomcaller(){
	name = "Doomcaller";
	attackpoints = 7;
	defensepoints = 9;
	cost = 8;
	keywords = 0;
	tapped=true;
	//Battlecry: Give your C'thun +2/+2. If it's dead, shuffle it back to your deck
	}
}
