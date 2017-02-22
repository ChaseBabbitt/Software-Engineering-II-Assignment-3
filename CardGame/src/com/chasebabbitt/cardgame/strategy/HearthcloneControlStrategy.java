package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;
import java.util.ArrayList;

public class HearthcloneControlStrategy implements Strategy {
	/**
	 *  Strategy for Control:
	 *  Play smallest costing cards first.
	 *  attack in any fight where you win first
	 *  attack in any fight where you trade
	 *  attack the weakest defender with your weakest attacker
	 *  if there are no legal targets attack the player
	 */
	public Move getMove(Player Defender, Player Attacker){
		Move move = null;
		Card attack = null;
		//Plays the cheapest card it can
		
		if(CanPlayCard(Attacker)){
			ArrayList<Card> playable = PlayableCard(Attacker);
			for(Card c : playable){
				if(c.getCost()<attack.getCost())
					attack = c;
			}
			move = new PlayCard(attack,Attacker);
			return move;
		}
		ArrayList<Card> ReadyCards = ReadyCard(Attacker);
		if(ReadyCards!=null){
			ArrayList<Card> LegalTargets = null;
			for(Card c: Defender.getCards()){
				//In case of taunt only allow cards with taunt
				//needs to be done;
				//else list all legal cards
				LegalTargets = Defender.getCards();
			}

			// go through the legal targets and find the best card to attack each one
			for(Card c: LegalTargets){
				for(Card k: ReadyCards){
					if(k.getAttackPoints()>=c.getDefensePoints()&&k.getDefensePoints()>c.getAttackPoints()){
						move = new BlockedAttack(k,c,Attacker,Defender);
						k.exhaust();
						return move;
					}
				}
			}	
			for(Card c: LegalTargets){
				for(Card k: ReadyCards){
					if(k.getAttackPoints()>=c.getDefensePoints()){
						move = new BlockedAttack(k,c,Attacker,Defender);
						k.exhaust();
						return move;
					}
				}
			}
			if(LegalTargets!= null){
					Card target = LegalTargets.get(0);
					for(Card c: LegalTargets){
						if(target.getDefensePoints()>c.getDefensePoints()){
							target = c;
						}
					}
					attack = ReadyCards.get(0);
					for(Card k: ReadyCards){
						if(attack.getAttackPoints()>k.getAttackPoints()){
							attack = k;
						}
					}
					move = new BlockedAttack(attack, target, Attacker, Defender);
					return move;
			}
			//If there are no good targets to attack, Attack the player;
			move = new UnblockedAttack(ReadyCards.get(0), Attacker, Defender);
			return move;
		}
		return move;
	}
	/**
	 * Checks the players hand to see if he can play one or more cards.
	 * @param Attacker
	 * @return true if there is at least 1 card the player can play to the field
	 */
	private Boolean CanPlayCard(Player Attacker){
		for(Card c: Attacker.getHand()){
			if(c.getCost()<Attacker.getResources())
				return true;
		}
		return false;
	}
	/**
	 * Gets a list of all the cards the player could play
	 * @param Attacker
	 * @return a list of all cards that cost less than the players current resources
	 */
	private ArrayList<Card> PlayableCard(Player Attacker){
		ArrayList<Card> playable = null;
		for(Card c: Attacker.getHand()){
			if(c.getCost()<Attacker.getResources()){
				playable.add(c);
			}
		}
		return playable;
	}
	/**
	 * Gets all untapped cards
	 * @param Attacker
	 * @return a list of ready cards on the players field
	 */
	private ArrayList<Card> ReadyCard(Player Attacker){
		ArrayList<Card> untapped = null;
		for(Card c: Attacker.getCards()){
			if(!c.tappedStatus()){
				untapped.add(c);
			}
		}
		return untapped;
	}
}
