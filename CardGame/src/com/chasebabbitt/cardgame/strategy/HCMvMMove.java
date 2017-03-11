package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

public class HCMvMMove extends BlockedAttack {

	public HCMvMMove(Card attacker, Card defender, Player attackingplayer, Player defendingplayer) {
		super(attacker, defender, attackingplayer, defendingplayer);	
	}
	public void execute(){
		System.out.println(attackingcard.getName()+" attacks "+defendingcard.getName());
		//Exhaust attacking card
		attackingcard.exhaust();
		//Get damage values from attacking and defending cards
		int attackerdamage = attackingcard.getAttackPoints();
		int defenderdamage = defendingcard.getAttackPoints();
		//Deal damage to attacking and defending cards
		defendingcard.dealDamage(attackerdamage,defendingplayer,attackingplayer);
		attackingcard.dealDamage(defenderdamage,attackingplayer,defendingplayer);
		//Check if attacking or defending card was killed and
		//move card to it's owner's graveyard if it was killed
		if(attackingcard.getHealth()<=0){
			System.out.println(attackingcard+" was defeated.");
			attackingplayer.moveCardToGraveyard(attackingcard);
		}
		if(defendingcard.getHealth()<=0){
			System.out.println(defendingcard+" was defeated.");
			defendingplayer.moveCardToGraveyard(defendingcard);
		}
	}

}
