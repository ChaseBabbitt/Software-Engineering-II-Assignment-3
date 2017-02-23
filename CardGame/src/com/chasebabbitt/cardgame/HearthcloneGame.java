package com.chasebabbitt.cardgame;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;
import com.chasebabbitt.cardgame.player.HearthclonePlayer;
import com.chasebabbitt.cardgame.player.Player;

public class HearthcloneGame {

	Duel duel;
	public static void main(String[] args) {
		Player player1 = new HearthclonePlayer("Player 1", 20);
		for(int i = 7;i>0;i--)
			player1.drawCard();
		player1.toConsole();
		
		CardFactory cardgenerator = new HearthcloneCardFactory();
		Card card = cardgenerator.createCard();

		
		System.out.println( card.getHealth());
		card.dealDamage(1);
		System.out.println(card.getHealth());

	}

}
