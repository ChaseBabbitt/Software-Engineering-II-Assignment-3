package com.chasebabbitt.cardgame;


import com.chasebabbitt.cardgame.player.HearthclonePlayer;


public class HearthcloneGame {

	Duel duel;
	public static void main(String[] args) {
		HearthclonePlayer player1 = new HearthclonePlayer("Player 1", 20);
		HearthclonePlayer player2 = new HearthclonePlayer("Player 2", 20);
		HearthcloneDuel duel = new HearthcloneDuel(player1,player2);
		duel.play();
	



	}

}
