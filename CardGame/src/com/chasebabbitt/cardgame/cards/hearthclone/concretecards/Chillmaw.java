/*
 * David Ahmad
 */

package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import com.chasebabbitt.cardgame.cards.Card;

public class Chillmaw extends Card {
	public Chillmaw(){
	name = "Chillmaw";
	attackpoints = 6;
	defensepoints = 6;
	cost = 7;
	keywords = 0;
	tapped=true;
	//Deathrattle: If you're holding a dragon, deal 3 damage to all minions
	//Type:Dragon
	}
}
