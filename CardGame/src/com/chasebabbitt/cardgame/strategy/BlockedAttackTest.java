package com.chasebabbitt.cardgame.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hex.HexCardFactory;
import com.chasebabbitt.cardgame.player.Player;

/**
 * Tests that the BlockedAttack Move class works correctly
 * @author Chase
 *
 */
public class BlockedAttackTest {
	Player attackingplayer, defendingplayer;
	Move blockedattack;
	Card attackingcard, defendingcard;
	CardFactory cardgenerator = new HexCardFactory();
	
	@Before
	public void setup(){
		attackingplayer = new Player("Attacking Player",20);
		defendingplayer = new Player("Defending Player",20);
	    attackingcard = cardgenerator.createCard(0);
	    defendingcard = cardgenerator.createCard(21);
	    blockedattack = new BlockedAttack(attackingcard,defendingcard,attackingplayer,defendingplayer);
	}
	
	
	@Test
	public void test() {
		blockedattack.execute();
		/*Expects the attacking to deal 4 damage to the defending player, 6 attackpower from Blasphemous Horror,
		 *  2 defense on Zombie, and Blasphemous Horror has Crush so it will deal 6-2 damage to the defending player;
		 */
		assertEquals(defendingplayer.getHealth(),16);
	}

}
