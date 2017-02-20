package com.chasebabbitt.cardgame.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hex.HexCardFactory;
import com.chasebabbitt.cardgame.player.Player;

public class UnblockedAttackTest {
	Player attackingplayer, defendingplayer;
	Move unblockedattack;
	Card attackingcard;
	CardFactory cardgenerator = new HexCardFactory();
	
	@Before
	public void setup(){
		attackingplayer = new Player("Attacking Player",20);
		defendingplayer = new Player("Defending Player",20);
	    attackingcard = cardgenerator.createCard(0);
	  
	    unblockedattack = new UnblockedAttack(attackingcard,attackingplayer,defendingplayer);
	}
	
	
	@Test
	public void test() {
		unblockedattack.execute();
		/* Expects the attack to deal 6 damage to the defending player, reducing their health to 20-6 = 14
		 */
		assertEquals(defendingplayer.getHealth(),14);
	}

}