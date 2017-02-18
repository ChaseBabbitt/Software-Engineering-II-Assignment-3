package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.player.Player;

public interface Strategy {
	
	public Move getMove(Player defender, Player attacker);

}
