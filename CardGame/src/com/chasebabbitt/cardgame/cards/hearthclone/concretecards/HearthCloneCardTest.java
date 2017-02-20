package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;

public class HearthCloneCardTest {

	Card card = new TauntDecoration(new IronfurGrizzly());
	@Test
	public void test() {
		assertEquals(card.getKeywords(),HearthcloneAbilitiesDecorator.TAUNT);
		assertEquals(card.getAttackPoints(),3);
		assertEquals(card.getDefensePoints(),3);
		assertEquals(card.getCost(),3);
		assertEquals(card.getName(),"Ironfur Grizzly");
	}

}
