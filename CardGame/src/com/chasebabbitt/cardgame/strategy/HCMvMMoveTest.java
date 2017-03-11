package com.chasebabbitt.cardgame.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;
import com.chasebabbitt.cardgame.player.HearthclonePlayer;

public class HCMvMMoveTest {
	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card attackingcard = cardgenerator.createCard(2);
	Card defendingcard = cardgenerator.createCard(0);
	HearthclonePlayer attacker = new HearthclonePlayer("Attacker",30);
	HearthclonePlayer defender = new HearthclonePlayer("Defender",30);
	Move move = new HCMvMMove(attackingcard,defendingcard,attacker,defender);

	/**
	 * Test that HCMvMMove causes monsters to deal damage to each other
	 */
	@Test
	public void test() {
		assertEquals(attackingcard.getHealth(),3);
		assertEquals(defendingcard.getHealth(),4);
		move.execute();
		assertEquals(attackingcard.getHealth(),2);
		assertEquals(defendingcard.getHealth(),1);
	}

}
