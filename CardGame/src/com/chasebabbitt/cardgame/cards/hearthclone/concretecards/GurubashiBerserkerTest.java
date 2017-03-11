package com.chasebabbitt.cardgame.cards.hearthclone.concretecards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hearthclone.HearthcloneCardFactory;

public class GurubashiBerserkerTest {

	CardFactory cardgenerator = new HearthcloneCardFactory();
	Card card = cardgenerator.createCard("Gurubashi Berkserker");
	@Test
	public void test() {
		assertEquals(card.getKeywords(),0);
		assertEquals(card.getAttackPoints(),2);
		assertEquals(card.getDefensePoints(),7);
		assertEquals(card.getCost(),5);
		assertEquals(card.getName(),"Gurubashi Berserker");
	}
	/**
	 * Tests to see if the GurubashiBerkserker's ability is working correctly
	 * getAttackPoints() should initially be 2
	 * dealDamage(1) will be called on card
	 * getAttackPoints() should now return 5
	 * dealDamage(1) will be called on card again
	 * getAttackPoints() should now return 8
	 */
	@Test
	public void test2(){
		System.out.println(card.toString());
		assertEquals(card.getAttackPoints(),2);
		card.dealDamage(1,null,null);
		System.out.println(card.toString());
		assertEquals(card.getAttackPoints(),5);
		card.dealDamage(1,null,null);
		assertEquals(card.getAttackPoints(),8);
		System.out.println(card.toString());
	}

}
