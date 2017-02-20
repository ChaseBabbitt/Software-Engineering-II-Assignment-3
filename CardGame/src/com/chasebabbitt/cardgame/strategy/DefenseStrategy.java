package com.chasebabbitt.cardgame.strategy;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

public class DefenseStrategy implements Strategy{

	@Override
	public Move getMove(Player defender, Player attacker) {
		Move move = null;
		// TODO Auto-generated method stub

		Card defendingcard=null;
		Card attackingcard=null;
		int incomingdamage = 0;
		
		System.out.println("Defense Strategy Method called.");
		
		//An array of cards that represents all the attacking cards the opponent controls
		ArrayList<Card> attackingcards = attacker.getAttackingCards();
		//An Array of cards that that defending player controls
		ArrayList<Card> defendingcards = defender.getDefendingCards();
		//An Array of cards that represents a legal defending move
		ArrayList<Card> legalblockingcards = new ArrayList<Card>();
		
		//Chooses the most threatening attacking card and removes it from the field.
		if(attackingcards.size()>0){
			//Calculate the total damage the attacking cards will do
			for(Card c: attackingcards){
				incomingdamage+=c.getAttackPoints();
			}
			//attackingcard = attackingcards.remove(0);
			attackingcard = attackingcards.get(0);
			//attackingcard = attackingcards.first();
			for(Card c:attackingcards){
				if(c.getAttackPoints()>attackingcard.getAttackPoints())
					attackingcard=c;
			}
			//remove the attacking card from the Enemy Field
			attacker.removeAttackingCard(attackingcard);
		}
		//If there were no attacking cards(the EnemyField was empty) returns null, as no move needs to be taken
		if(attackingcard == null)
			return move;
		
		//Pick from the defending cards all cards that would be a legal move
		legalblockingcards = getLegalBlockers(attackingcard,defendingcards);
		//Pick from the legal defense choices the best defense
		defendingcard = bestDefense(attackingcard,legalblockingcards,defender.getHealth(),incomingdamage);
		
		
		//construct a Move consisting of the most threatening attacking card and the best defense
		if(defendingcard ==null){
			move = new UnblockedAttack(attackingcard,attacker,defender);
			return move;
		}
		move = new BlockedAttack(attackingcard,defendingcard,attacker,defender);
		//If a defending card was found, remove it from the Player's field
		if(defendingcard!=null)
			defender.removeDefendingCard(defendingcard);
		return move;
	}
	/**
	 * This function returns an array of Cards that can legally defend against the attackingcard
	 * 
	 * @param attackingcard The attacking card that must be defended against
	 * @param defendingcards An array of all the defending cards
	 * @return returns an array of Card objects
	 */
	private ArrayList<Card> getLegalBlockers(Card attackingcard, ArrayList<Card> defendingcards){
		ArrayList<Card> legalblockingcards = new ArrayList<Card>();;
		if((attackingcard.getKeywords()&Card.FLY)==Card.FLY){
			System.out.println(attackingcard.getName()+" has flying.");
			short legalblocker = Card.FLY | Card.SKYGUARD;
			for(Card c:defendingcards){		
				if ((c.getKeywords() & legalblocker) > 0){
					System.out.println(c.getName()+" is a legal blocker");
					legalblockingcards.add(c);
				}
			}	
		}
		else{
			legalblockingcards=defendingcards;
		}
		return legalblockingcards;
	}
	
	
	/**
	 * 
	 * @param attackingcard This it the enemy card that is being defended against
	 * @param defendingcards This is an array of all the defending cards that can legally block the attacking card
	 * @param playerHealth The health of the defending player.
	 * @param incomingdamage The sum of the damage all attacking cards will deal
	 * @return returns a Card object chosen from the defendingcards array that the method has determined to be the best
	 * defensive choice. This value may be null if the method decides that not making a move is the best decision.
	 */
	private Card bestDefense(Card attackingcard, ArrayList<Card> defendingcards, int playerHealth, int incomingdamage){
		//The defending card choice
		Card defendingcard = null;
		//An array of candidates to be chosen for the defendingcard;
		ArrayList<Card> defensechoices = new ArrayList<Card>();
		
		//if there are no legal defending cards, return null
		if(defendingcards.size()==0)
			return defendingcard;
		
		//Put all defending cards that won't be defeated by the attacking card into an array of defendingcard candidates
		for(Card c:defendingcards){
			if(c.getDefensePoints()>attackingcard.getAttackPoints())
				defensechoices.add(c);
		}
		
		//Search the array of defendingcard candidates for cards that will defeat the attacking card
		if(defensechoices.size()>0){
			defendingcard = defensechoices.get(0);
			//defendingcard = defensechoices.first();
			for(Card c:defensechoices)
				if(c.getAttackPoints()>=attackingcard.getDefensePoints())
					return c;
			//if no card was found that would defeat the enemy card and not be defeated in return, return the first card that
			//won't be defeated.
			return defensechoices.get(0);
			//return defensechoices.first();
		}
		
		//Search the array of defendingcards for a card that will defeat the enemycard and costs less than the attacking card
		for(Card c:defendingcards)
			if(c.getAttackPoints()>attackingcard.getDefensePoints()&&c.getCost()<attackingcard.getCost())
				defensechoices.add(c);
		if(defensechoices.size()>0){
				defendingcard=defensechoices.get(0);
				//defendingcard=defensechoices.first();
				for(Card c:defensechoices)
					if(c.getCost()<defendingcard.getCost())
						defendingcard = c;
				return defendingcard;
		}
			
		//If none of the previous searches found a suitable card, and the attack won't kill the player, don't block it at all
		if(incomingdamage<playerHealth)
			return defendingcard;
		
		//Select all defending cards that will defeat the the attacking card as defense candidates
		for(Card c:defendingcards)
			if(c.getAttackPoints()>=attackingcard.getDefensePoints())
				defensechoices.add(c);
		//Select the lowest cost of these candidates and return it
		if(defensechoices.size()>0){
			defendingcard=defensechoices.get(0);
			//defendingcard=defensechoices.first();
			for(Card c:defensechoices)
				if(c.getCost()<defendingcard.getCost())
					defendingcard=c;
			return defendingcard;
		}
		
		//Select the least valuable defending card and return it
		defendingcard=defendingcards.get(0);
		//defendingcard=defendingcards.first();
		for(Card c:defendingcards)
			if(c.getCost()<defendingcard.getCost())
				defendingcard=c;
		
		
		return defendingcard;
		
	}
	

}