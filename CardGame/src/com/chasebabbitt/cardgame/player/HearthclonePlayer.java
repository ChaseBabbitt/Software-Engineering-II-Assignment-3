package com.chasebabbitt.cardgame.player;

import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;

public class HearthclonePlayer extends Player {

	int weapondamage,weapondurability, armor;
	
	public HearthclonePlayer(String name, int health) {
		super(name, health);
		weapondamage = 0;
		weapondurability = 0;
		armor = 0;
		cardgenerator = new HearthcloneCardFactory();
		deck = new Deck(cardgenerator,30);
	}

}
