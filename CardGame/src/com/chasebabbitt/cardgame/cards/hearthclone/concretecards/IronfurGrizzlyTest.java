package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneAbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;

public class IronfurGrizzlyTest {

	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card card = cardgenerator.createCard("Ironfur Grizzly");
	@Test //Attributes Test
	public void test() {
		assertEquals(card.getKeywords(),HearthcloneAbilitiesDecorator.TAUNT);
		assertEquals(card.getAttackPoints(),3);
		assertEquals(card.getDefensePoints(),3);
		assertEquals(card.getCost(),3);
		assertEquals(card.getName(),"Ironfur Grizzly");
		
	}
	
	/**
	 *  Tests getHealth and dealDamage methods
	 *  Expected results: getHealth() will initially be 3
	 *  dealDamage(2) method will be called
	 *  getHealth() will then return 1
	 */
	@Test 
	public void test2(){
		assertEquals(card.getHealth(),3);
		card.dealDamage(2);
		assertEquals(card.getHealth(),1);		
	}

}
