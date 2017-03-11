package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;

public class HCMvPMove extends UnblockedAttack{

	public HCMvPMove(Card attackingcard, HearthclonePlayer attackingplayer, HearthclonePlayer defendingplayer) {
		super(attackingcard, attackingplayer, defendingplayer);
	}
	public void execute(){
		defendingplayer.dealDamage(attackingcard.getAttackPoints());
		System.out.println(attackingcard.getName()+" deals "+attackingcard.getAttackPoints()+" to "+defendingplayer.getName());
	}

}
