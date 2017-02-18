package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

public class UnblockedAttack implements Move {
	
	Card attackingcard;
	Player attackingplayer, defendingplayer;
	
	public UnblockedAttack(Card attackingcard, Player attackingplayer, Player defendingplayer){
		this.attackingcard = attackingcard;
		this.attackingplayer = attackingplayer;
		this.defendingplayer = defendingplayer;
	}

	@Override
	public void execute() {
		System.out.println(attackingcard.getName()+" attacks and is not blocked!");
		defendingplayer.dealDamage(attackingcard.getAttackPoints());
		
	}


	/**
	 * Displays the move for the console
	 */
	public void consoleDisplay(){
			System.out.println(attackingcard.getName()+" attacks and is not blocked");
	}	


}