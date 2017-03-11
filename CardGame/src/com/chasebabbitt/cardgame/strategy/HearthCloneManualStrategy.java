package com.chasebabbitt.cardgame.strategy;

import java.util.ArrayList;
import java.util.Scanner;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;

public class HearthCloneManualStrategy implements Strategy{
	Scanner input;
	@Override
	public Move getMove(Player attacker, Player defender) {
		input = new Scanner(System.in);
		int command;
		Move move = null;
		System.out.println("Choose a type of move: (1)Attack, (2)Play, (3)End");
		do{
			command = input.nextInt();
		}while(command<1||command>3);
		if(command==1)
			move = getAttackMove(defender,attacker);
		else if(command==2)
			move = getPlayMove(defender, attacker);
		
		//input.close();
		return move;
	}

	private Move getPlayMove(Player defender, Player attacker) {
		//An Array of all the cards that the player has in their hand
		ArrayList<Card> cardsinhand;
		//An Array of Cards that the player has the available resources to play
		ArrayList<Card> playablecards = new ArrayList<>();
		//The currently available resources the player has
		int availableresources;
		cardsinhand = attacker.getHand();
		availableresources = attacker.getResources();
		//Scan through the cardsinhand and add each card that is playable to the playablecards array
		System.out.println("You have "+availableresources+" mana.");
		for(Card c: cardsinhand){
			if(c.getCost()<=availableresources){
				playablecards.add(c);
			}
		}
		if(playablecards.isEmpty()){
			System.out.println("You have no playable cards.");
			return null;
		}
		int biasedindex=1;
		System.out.println("Choose a card to play: ");
		for(Card c: playablecards){
			System.out.println(biasedindex+" "+c.toString());
			biasedindex++;
		}
		biasedindex = input.nextInt();
		while(biasedindex<=0||(biasedindex>=playablecards.size()+1))
			biasedindex = input.nextInt();
		Card cardtobeplayed = playablecards.get(biasedindex-1);
		return new PlayCard(cardtobeplayed,attacker);
	}

	
	
	private Move getAttackMove(Player defender, Player attacker) {
		//An Array of all the cards that the attacking player has in the field
		ArrayList<Card> cardsinfield;
		//An Array of all the cards that are still usable
		ArrayList<Card> availablecards = new ArrayList<>();
		//An Array of all the cards in the opponent's field
		ArrayList<Card> enemycardsinfield;
		//An Array of enemy cards that can be legally attacked
		ArrayList<Card> legaltargets = new ArrayList<>();
		boolean enemyhastaunters = true;
		cardsinfield = attacker.getCards();
		enemycardsinfield = defender.getCards();
		//Scan through the attacking player's card in the field and determine which are available to attack with
		for(Card c: cardsinfield){
			if(c.tappedStatus()==false)
				availablecards.add(c);
		}
		//If the attacking player has no cards that can attack notify the player and return null
		if(availablecards.isEmpty()){
			System.out.println("You have no cards left to attack with.");
			return null;
		}
		//Scan through the cards in the enemy's field and determine which are legal targets
		
		//First add all cards that have Taunt and do not have Stealth to the array of legal targets
		for(Card c:enemycardsinfield){
			if(c.hasKeyword(HearthcloneAbilitiesDecorator.STEALTH)==false && c.hasKeyword(HearthcloneAbilitiesDecorator.TAUNT))
				legaltargets.add(c);
		}
		//If there are no cards with Taunt in the array add all cards from the enemy field that don't have stealth to the array
		//of legal targets
		if(legaltargets.isEmpty()){
			enemyhastaunters=false;
			for(Card c:enemycardsinfield){
				if(c.hasKeyword(HearthcloneAbilitiesDecorator.STEALTH)==false)
					legaltargets.add(c);
			}
		}
		//Display the available cards to attack with to the user, and prompt them for a choice
		int biasedindex = 1;
		System.out.println("Choose a card to attack with: ");
		for(Card c:availablecards){
			System.out.println(biasedindex+" "+c.toString());
		}
		biasedindex = input.nextInt();
		while(biasedindex<=0||(biasedindex>availablecards.size()))
			biasedindex = input.nextInt();
		Card attackingcard = availablecards.get(biasedindex-1);
		//Display the legal targets to the user, and prompt them for a choice
		biasedindex = 1;
		for(Card c:legaltargets){
			System.out.println(biasedindex+" "+c.toString());
			biasedindex++;
		}
		if(enemyhastaunters==false)
			System.out.println(biasedindex+" "+defender.getName());
		biasedindex = input.nextInt();
		//If user chooses to attack the player(player "index" = size
		if(enemyhastaunters==false&&biasedindex==legaltargets.size()+1){
			//Construct and return HSMvPMove
			// TBI
			System.out.println("Constructing MvP move");
			Move move = new HCMvPMove(attackingcard,(HearthclonePlayer)attacker,(HearthclonePlayer) defender);
			return move;
		}
		//Otherwise read input until a valid index is given
		while(biasedindex<=0||(biasedindex>legaltargets.size()))
			biasedindex = input.nextInt();
		Card defendingcard = legaltargets.get(biasedindex-1);
		Move move = new HCMvMMove(attackingcard,defendingcard,attacker,defender);
		return move;
	}
	
	

}
