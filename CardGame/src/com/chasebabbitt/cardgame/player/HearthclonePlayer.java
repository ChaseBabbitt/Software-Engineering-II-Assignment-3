package com.chasebabbitt.cardgame.player;

import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;
import com.chasebabbitt.cardgame.strategy.HearthCloneAggroStrategy;
import com.chasebabbitt.cardgame.strategy.Move;

public class HearthclonePlayer extends Player {

	int weapondamage,weapondurability, armor;
	
	public HearthclonePlayer(String name, int health) {
		super(name, health);
		weapondamage = 0;
		weapondurability = 0;
		armor = 0;
		cardgenerator = new HearthcloneCardFactory();
		deck = new Deck(cardgenerator,30);
		strategy = new HearthCloneAggroStrategy();
	}
	
	public void toConsole(){
		System.out.println("");
		System.out.println("**************************************************************");
		System.out.println(name+"'s Field:             Health: "+health+"/"+startinghealth);
		field.toConsole();
		System.out.println("Mana: "+manaToString());
		System.out.println(name+"'s Hand:            Deck: "+deck.size());
		hand.toConsole();
		System.out.println("**************************************************************");
	}
	public String manaToString(){
		String mana = new String();
		int availablemana = currentresources;
		int maxmana = maxresources;
		while(maxmana>0){
			mana = mana+"(";
			if(availablemana>0){
				availablemana--;
				mana=mana+"#";
			}
			else
				mana=mana+" ";
			mana=mana+")";
			maxmana--;
			
		}
		return mana;
	}
	public void incrementResources(){
		if (maxresources<10){
			maxresources++;
		}
	}
	public Move getMove(){
		return strategy.getMove(this, opponent);
	}

}
