package com.chasebabbitt.cardgame;

import java.util.Scanner;

import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;

public class HearthcloneDuel extends Duel {
	
	Scanner input;
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
		return null;
	}

	@Override
	protected void turn() {
		System.out.print("\n\n\n\n\n\n");
		player1.toConsole();
		player2.toConsole();
		input.nextLine();		
	}

}
