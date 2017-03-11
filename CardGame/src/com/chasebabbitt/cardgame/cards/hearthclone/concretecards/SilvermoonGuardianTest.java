package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.*;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;

public class SilvermoonGuardianTest {

	
	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card card = cardgenerator.createCard("Silvermoon Guardian");
	/**
	 * Basic attributes testing for Silvermoon Guardian
	 */
	@Test
	public void test() {
		assertEquals(card.getName(), "Silvermoon Guardian");
		assertEquals(card.getAttackPoints(), 3);
		assertEquals(card.getHealth(), 3);
		assertEquals(card.getCost(), 4);
		assertEquals(card.hasKeyword(HearthcloneAbilitiesDecorator.DIVINESHIELD), true);
		
		//((HearthcloneAbilitiesDecorator) card).dealDamage(1);
		
	}
	
	/**
	 * Tests that DivineShieldDecoration works
	 * Expected outcome: dealDamage(1) method for the card will be called, no damage will be dealt to the card however
	 * and the DivineShieldDecoration's effect will be turned off. dealDamage(1) will be called again and damage should
	 * successfully be dealt to the card
	 */
	@Test  
	public void test2(){
		
		card.dealDamage(1,null,null);
		assertEquals(card.hasKeyword(HearthcloneAbilitiesDecorator.DIVINESHIELD), false);
		assertEquals(card.getKeywords(),0);
		assertEquals(card.getHealth(), 3);
		card.dealDamage(1,null,null);
		assertEquals(card.getHealth(), 2);
	}

}
