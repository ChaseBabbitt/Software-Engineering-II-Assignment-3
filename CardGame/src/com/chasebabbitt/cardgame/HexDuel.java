package com.chasebabbitt.cardgame;

import java.util.Scanner;

import com.chasebabbitt.cardgame.player.Player;
import com.chasebabbitt.cardgame.strategy.Move;

public class HexDuel extends Duel {

	Scanner input;
	public HexDuel(Player player1, Player player2) {
		super(player1, player2);
		input = new Scanner(System.in);
	}

	@Override
	protected Player findWinner() {
		if(player1.getHealth()<=0)
			return player2;
		else if (player2.getHealth()<=0)
			return player1;
		return null;
	}

	@Override
	protected void turn() {
		System.out.print("\n\n\n\n\n\n");
		player1.toConsole();
		player2.toConsole();
		
		attacker.setAttackingCards();
		defender.setDefendingCards();
	
		attacker.playCard();
		System.out.println(attacker.getName()+" is now attacking.");
		
		Move move = defender.getMove();
		while(move!=null){
			move.execute();
			move = defender.getMove();
		}
	
		
		input.nextLine();
	
	}
	
	protected void beforeTurn(){
		//Implement drawing cards
		attacker.drawCard();
		//Implement resources
	}
	
	protected void afterTurn(){
		//Discard cards from attacker's hand if >=7
	}
		

}
