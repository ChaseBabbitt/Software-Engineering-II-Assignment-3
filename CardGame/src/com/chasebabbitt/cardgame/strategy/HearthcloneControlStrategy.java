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
		if(CanPlayCard(Attacker)){
			ArrayList<Card> playable = PlayableCard(Attacker);
			for(Card c : playable){
				if(c.getCost()<attack.getCost())
					attack = c;
			}
			move = new PlayCard(attack,Attacker);
			return move;
		}
		//Get a list of ready cards and see who they can attack
		ArrayList<Card> ReadyCards = ReadyCard(Attacker);
		if(ReadyCards.size()>0){
			ArrayList<Card> LegalTargets = new ArrayList<Card>();
			//check first if any cards have taunt
			for(Card c: Defender.getCards()){
				if(c.getKeywords()==1){
					LegalTargets.add(c);
				}
			}
			//if there is no taunt, all creature on the board are legal targets
			if(LegalTargets.size() == 0){
				LegalTargets = Defender.getCards();
			}
			
			//if the opponent has any creatures on the field, see if you can destroy one without killing your creature
			if(LegalTargets.size() > 0){
				for(Card c: LegalTargets){
					for(Card k: ReadyCards){
						if(k.getAttackPoints()>=c.getDefensePoints()&&k.getDefensePoints()>c.getAttackPoints()){
							move = new BlockedAttack(k,c,Attacker,Defender);
							k.exhaust();
							return move;
						}
					}
				}
				//if not, see if you can kill any of their cards
				for(Card c: LegalTargets){
					for(Card k: ReadyCards){
						if(k.getAttackPoints()>=c.getDefensePoints()){
							move = new BlockedAttack(k,c,Attacker,Defender);
							k.exhaust();
							return move;
						}
					}
				}
				// if not, use your weakest card to attack their weakest defender
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
			
			//if there are no creatures on the board attack the player
			if(LegalTargets.size() == 0){
				move = new UnblockedAttack(ReadyCards.get(0), Attacker, Defender);
				return move;
			}
		}
		// return null to pass the turn
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
		ArrayList<Card> playable = new ArrayList<Card>();
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
		ArrayList<Card> untapped = new ArrayList<Card>();
		for(Card c: Attacker.getCards()){
			if(!c.tappedStatus()){
				untapped.add(c);
			}
		}
		return untapped;
	}
}
