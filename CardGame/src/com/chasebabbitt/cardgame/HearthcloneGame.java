package com.chasebabbitt.cardgame;

import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;

public class HearthcloneGame {

	Duel duel;
	public static void main(String[] args) {
		Player player1 = new HearthclonePlayer("Player 1", 20);
		for(int i = 7;i>0;i--)
			player1.drawCard();
		player1.toConsole();
		

	}

}
