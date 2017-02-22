package com.chasebabbitt.cardgame.strategy;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

public class HearthCloneAggroStrategy implements Strategy {

	@Override
	public Move getMove(Player Defender, Player Attacker){
		Move move = null;
		Card attack = null;
		//Plays the strongest attacker card it can
		if(CanPlayCard(Attacker)){
			ArrayList<Card> possiblecards = PlayableCard(Attacker);
			attack = possiblecards.get(0);
			for(Card c : possiblecards){
				if(c.getAttackPoints()<attack.getAttackPoints())
						attack = c;
			}
			move = new PlayCard(attack,Attacker);
			return move;
		}
		
		attack = null;
		//Get All cards that can be attacked
		ArrayList<Card> ReadyCards = ReadyCard(Attacker);
		
		if(ReadyCards!=null){
			ArrayList<Card> LegalTargets = null;
			
			for(Card c: Defender.getCards()){
				if(c.getKeywords()==1){
					LegalTargets.add(c);
				}
			}
			//if there are no cards with taunt attack the player
			if(LegalTargets == null){
				ReadyCards.get(0).exhaust();
				move = new UnblockedAttack(ReadyCards.get(0), Attacker, Defender);
				return move;
			}
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
