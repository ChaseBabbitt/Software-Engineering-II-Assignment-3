
package com.chasebabbitt.cardgame;

import java.util.Scanner;

import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;
import com.chasebabbitt.cardgame.strategy.Move;

public class HearthcloneDuel extends Duel {
	
	Scanner input;
	Move move;
	public HearthcloneDuel(HearthclonePlayer player1, HearthclonePlayer player2) {
		super(player1, player2);
		input = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void beforeTurn(){
		attacker.incrementResources();
		attacker.refreshResources();
		attacker.drawCard();
	}
	
	@Override
	protected Player findWinner() {
		if(player1.getHealth()<=0){
			input.close();
			return player2;
		}
		else if(player2.getHealth()<=0){
			input.close();
			return player1;
		}
		return null;
	}

	@Override
	protected void turn() {
		System.out.print("\n\n\n\n\n\n");
		player1.toConsole();
		player2.toConsole();
		move = attacker.getMove();
		while(move!=null){
			input.nextLine();
			move.execute();
			move = attacker.getMove();
		}
		input.nextLine();		
	}

}
