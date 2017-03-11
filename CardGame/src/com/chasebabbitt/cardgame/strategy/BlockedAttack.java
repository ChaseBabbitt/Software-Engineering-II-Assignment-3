package com.chasebabbitt.cardgame.strategy;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.player.Player;

/**
	 * A Move object
	 * @author Chase
	 *
	 */
public class BlockedAttack implements Move{
	/**
	 * attackingCard is the attacking card and defendingCard is the defending card, attackingplayer is the attacking player
	 *  and defendingplayer is the defending player;
	 */
		Player attackingplayer, defendingplayer;
		Card attackingcard, defendingcard;
		
		public BlockedAttack(Card attacker, Card defender, Player attackingplayer, Player defendingplayer){
			this.attackingcard=attacker;
			this.defendingcard=defender;
			this.attackingplayer = attackingplayer;
			this.defendingplayer = defendingplayer;
		}

		/**
		 * Displays the move for the console
		 */
		public void consoleDisplay(){
			if(defendingcard==null){
				System.out.println(attackingcard.getName()+" attacks and is not blocked");
			}
			else{
				System.out.println(attackingcard.getName()+" attacks and is blocked by "+defendingcard.getName());
			}
		}

		@Override
		public void execute() {
			System.out.println(attackingcard.getName()+" attacks and is blocked by "+defendingcard.getName());
			attackingcard.exhaust();
			if(attackingcard.getAttackPoints()>=defendingcard.getDefensePoints()){
				System.out.println(attackingcard.getName()+" defeats "+ defendingcard.getName());
				//Do methods to kill defending card
				defendingplayer.moveCardToGraveyard(defendingcard);
				//If attacker has crush, do methods do deal damage to defending player equal to attackingcard attack - defendincard defense
				if(((attackingcard.getKeywords()&Card.CRUSH)==Card.CRUSH)&&(attackingcard.getAttackPoints()>defendingcard.getDefensePoints()))
					defendingplayer.dealDamage(attackingcard.getAttackPoints()-defendingcard.getDefensePoints());
			}
			if(defendingcard.getAttackPoints()>=attackingcard.getDefensePoints()){
				System.out.println(defendingcard.getName()+" defeats "+ attackingcard.getName());
				//Do methods to kill attacking card
				attackingplayer.moveCardToGraveyard(attackingcard);
			}
			
		}
	}



