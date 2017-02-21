package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;

public class FlyingMachineTest {

	
	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card card = cardgenerator.createCard("Flying Machine");
	/**
	 * Attributes test for FlyingMachine
	 */
	@Test
	public void test() {
		assertEquals(card.getKeywords(),HearthcloneAbilitiesDecorator.WINDFURY);
		assertEquals(card.getAttackPoints(),1);
		assertEquals(card.getDefensePoints(),4);
		assertEquals(card.getCost(),3);
		assertEquals(card.getName(),"Flying Machine");
	}
	
	/**
	 * Tests WindfuryDecoration and tappedStatus methods
	 * FlyingMachine should initially be considered tapped
	 * untap() method will be called to untap FlyingMachine
	 * tappedStatus() should no be false
	 * exhaust() method will now be called
	 * A card without Windfury would be tapped at this point
	 * but since this card has Windury tappedstatus() should still be false
	 * exhaust() method will be called once more
	 * tappedStatus() should now return true
	 */
	@Test
	public void test2(){
		assertEquals(card.tappedStatus(),true);
		card.untap();
		assertEquals(card.tappedStatus(),false);
		card.exhaust();
		assertEquals(card.tappedStatus(),false);
		card.exhaust();
		assertEquals(card.tappedStatus(),true);
		
	}

}
