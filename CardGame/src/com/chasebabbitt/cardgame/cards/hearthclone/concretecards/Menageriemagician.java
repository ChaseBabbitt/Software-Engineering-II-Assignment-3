/*
 * David Ahmad
 */

package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;

public class Menageriemagician extends Card {
	public Menageriemagician(){
	name = "Menagerie Magician";
	attackpoints = 4;
	defensepoints = 4;
	cost = 5;
	keywords = 0;
	tapped=true;
	//Battlecry: Give a random friendly beast, dragon, murloc +2/+2.
	}
}
