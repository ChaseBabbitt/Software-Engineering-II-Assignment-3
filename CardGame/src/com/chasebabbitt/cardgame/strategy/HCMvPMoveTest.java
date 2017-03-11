package com.chasebabbitt.cardgame.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;
import com.chasebabbitt.cardgame.player.HearthclonePlayer;

public class HCMvPMoveTest {
	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card attackingcard = cardgenerator.createCard(2);
	HearthclonePlayer attacker = new HearthclonePlayer("Attacker",30);
	HearthclonePlayer defender = new HearthclonePlayer("Defender",30);
	Move move = new HCMvPMove(attackingcard,attacker,defender);
	/**
	 * Tests that HCMvPMove correctly deals damage to the defending player
	 */
	@Test
	public void test() {
		assertEquals(defender.getHealth(),30);
		move.execute();
		assertEquals(defender.getHealth(),27);
	}

}
