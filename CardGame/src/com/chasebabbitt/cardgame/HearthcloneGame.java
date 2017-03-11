package com.chasebabbitt.cardgame;


import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;


public class HearthcloneGame {

	Duel duel;
	public static void main(String[] args) {
		HearthclonePlayer player1 = new HearthclonePlayer("Player 1", 20);
		HearthclonePlayer player2 = new HearthclonePlayer("Player 2", 20);
		player1.setManualStrategy();
		Player winner;
		HearthcloneDuel duel = new HearthcloneDuel(player1,player2);
		winner = duel.play();
		System.out.println("\n********************************************");
		System.out.println("*****"+winner.getName()+" won the duel.*****");
		System.out.println("********************************************");
	



	}

}
