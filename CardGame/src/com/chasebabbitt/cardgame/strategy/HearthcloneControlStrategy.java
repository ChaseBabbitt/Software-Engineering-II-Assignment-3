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
		
		//Plays the strongest attacker card it can
		if(CanPlayCard(Attacker)){
			move = PlayCard(Attacker);
			return move;
		}
		
		//Get All cards that can be attacked
		ArrayList<Card> ReadyCards = ReadyCard(Attacker);
		
		//If there are ready cards make an attack
		if(ReadyCards.size()>0){
			
			ArrayList<Card> LegalTargets = new ArrayList<Card>();
			
			//If the enemy has cards, check for taunt
			if(hasDefender(Defender)){
				LegalTargets = findTaunt(Defender);
				//if there is taunt, find the best attack
				if(LegalTargets.size()>0){
					move = FindTarget(ReadyCards,LegalTargets,Attacker,Defender);
					return move;
				}
				else{
					LegalTargets = Defender.getCards();
					move = FindTarget(ReadyCards,LegalTargets,Attacker,Defender);
					return move;
				}
			}
			
			//if there are no cards with taunt attack the player
			else{
				ReadyCards.get(0).exhaust();
				move = new HCMvPMove(ReadyCards.get(0), Attacker, Defender);
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
		if(Attacker.getHand().size() == 0){
			return false;
		}
		for(Card c: Attacker.getHand()){
			if(c.getCost()<Attacker.getResources())
				return true;
		}
		return false;
	}
	
	
	
	
	
	/**
	 * PlayCard
	 * Finds the cheapest card and plays it to the field
	 * @param Attacker
	 * @return A PlayCard move
	 */
	private Move PlayCard(Player Attacker){
		Move move = null;
		Card Toplay = Attacker.getHand().get(0);
		for(Card c : Attacker.getHand()){
			if(c.getCost()<Toplay.getCost()){
				Toplay = c;
			}
		}
		move = new PlayCard(Toplay, Attacker);
		return move;
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
	 * hasDefender
	 * @param defender
	 * @return true if the oppenent has 1 or more cards on the field
	 */
	private Boolean hasDefender(Player defender){
		System.out.println("hasDefender");
		if(defender.getCards().size()>0)
			return true;
		else
			return false;
	}
	
	/**
	 * findTaunt
	 * @param defender
	 * @return a list of cards the oppenent controls with taunt
	 */
	private ArrayList<Card> findTaunt(Player defender){
		ArrayList<Card> LegalTargets = new ArrayList<Card>(); 
		for(Card c: defender.getCards()){
			if(c.getKeywords()==1){
				LegalTargets.add(c);
			}
		}
		return LegalTargets;
	}
	
	
	/**
	 * FindTarget
	 * @param Ready
	 * @param Target
	 * @param one
	 * @param two
	 * @return a BlockedAttackMove
	 */
	private Move FindTarget(ArrayList<Card> Ready, ArrayList<Card> Target, Player one, Player two){
		Card Attacker = Ready.get(0);
		Card Defender;
		Move move = null;
		for(Card c : Target){
			for(Card k: Ready){
				if(k.getAttackPoints()>c.getDefensePoints()&&Attacker.getAttackPoints()>k.getAttackPoints()){
					Attacker = k;
				}
			}
			if(Attacker.getAttackPoints()>c.getDefensePoints()){
				move = new HCMvMMove(Attacker,c,one,two);
				return move;
			}
		}
		if(Ready.size()> 1){
			for(Card c : Target){
				for(int k = 0;k<Ready.size()-1;k++){
					for(int i = k+1; i < Ready.size();i++){
						if(c.getDefensePoints()-(Ready.get(k).getAttackPoints()+Ready.get(i).getAttackPoints())==0){
							move = new HCMvMMove(Ready.get(i),c,one,two);
							return move;
						}
					}
				}
			}
		}
		Attacker = Ready.get(0);
		for(Card k : Ready){
			if(Attacker.getAttackPoints()>k.getAttackPoints()){
				Attacker = k;
			}
		}
		Defender = Target.get(0);
		for(Card c: Target){
			if(Defender.getDefensePoints()>c.getDefensePoints()){
				Defender = c;
			}
		}
		move = new HCMvMMove(Attacker,Defender,one,two);
		return move;
		
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
