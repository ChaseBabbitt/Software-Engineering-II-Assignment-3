package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

public class PlayCard implements Move {
	
	Player Attacker;
	Card Toplay;
	
	//constructor for a move with a selected card
	public PlayCard(Card Toplay, Player Attacker){
		this.Attacker = Attacker;
		this.Toplay = Toplay;
	}
	// constructer for a move with no card selected
	public PlayCard(Player Attacker){
		this.Attacker = Attacker;
		this.Toplay = null;
	}
	
	//Plays a selected card from a hand or plays the first card in the players hand
	public void execute(){
		Attacker.useResources(Toplay.getCost());
		if(Toplay!= null){
			Attacker.playCard(Toplay);
			Toplay.exhaust();
		}
		if(Toplay == null){
			Attacker.playCard();
		}
	}

	@Override
	public void consoleDisplay() {
		System.out.println( Attacker.getName()+" plays "+Toplay.getName()+" to the field" );
		
	}
}
