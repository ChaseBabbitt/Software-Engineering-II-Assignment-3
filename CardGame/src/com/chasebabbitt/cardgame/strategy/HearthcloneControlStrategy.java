package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;
import java.util.ArrayList;

public class HearthcloneControlStrategy implements Strategy {
	/**
	 *  Strategy for Control:
	 *  Play the  cards you can until your out of resources
	 *  attack any  
	 */
	public Move getMove(Player Defender, Player Attacker){
		Move move = null;
		Card attack = null;
		//Plays the cheapest card it can
		attack = Attacker.getHand().get(0);
		if(attack.getCost()>Attacker.getResources());{
			for(Card c : Attacker.getHand()){
				if(c.getCost()<attack.getCost())
					attack = c;
			}
		}
		for(Card c: Attacker.getHand()){
			if(attack.getCost()<c.getCost()&&c.getCost()<Attacker.getResources()){
				attack = c;
			}
		}
		if(attack.getCost()<Attacker.getResources()){
			move = new PlayCard(attack,Attacker);
			return move;
		}
		
		attack = null;
		//Get All cards that can be attacked
		ArrayList<Card> LegalTargets = null;
		for(Card c: Defender.getCards()){
			//In case of taunt only allow cards with taunt
			//needs to be done;
			//else list all legal cards
			LegalTargets = Defender.getCards();
		}
		
		// get all untapped cards the Attacker controls
		ArrayList<Card> ReadyCards = null;
		for(Card c : Attacker.getCards()){
			if(c.tappedStatus() == false)
				ReadyCards.add(c);
		}
		// go through the legal targets and find the best card to attack each one
		for(Card c: LegalTargets){
			for(Card k: ReadyCards){
				if(k.getAttackPoints()>=c.getDefensePoints()){
					if(k == null)
						attack = k;
					else if(k.getAttackPoints()<attack.getAttackPoints())
						attack = k;
				}
			}
			if(attack!= null){
				move = new BlockedAttack(attack, c, Attacker, Defender);
				return move;
			}
				
		}
		//If there are no good targets to attack, Attack the player;
		move = new UnblockedAttack(ReadyCards.get(0), Attacker, Defender);
		return move;
		
	}
}
