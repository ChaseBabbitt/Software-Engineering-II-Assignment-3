package com.chasebabbitt.cardgame;



import com.chasebabbitt.cardgame.player.Player;


public class CardGame {
	

	//Duel duel;
	public static void main(String[] args) {
		//Scanner input = new Scanner(System.in);
		HexDuel duel;
		Player player = new Player("Player 1",20);
		Player other = new Player("Player 2",20);
		Player winner;
		//Player attacker, defender, temp;
		
		player.setRandomCaseD();
		player.setOpponent(other);
		other.setRandomCaseA();
		other.setOpponent(player);
		
		duel = new HexDuel(player,other);
		winner = duel.play();
		System.out.println(winner.getName()+" wins.");
		/*
		attacker = other;
		defender = player;
		
		while(player.getHealth()>0 && other.getHealth()>0){
			other.toConsole();
			player.toConsole();
			
			attacker.setAttackingCards();
			defender.setDefendingCards();
		
			System.out.println(attacker.getName()+" is now attacking.");
			
			Move move = defender.getMove();
			while(move!=null){
				//move.consoleDisplay();
				move.execute();
				move = defender.getMove();
			}
		
		
			input.nextLine();
		
			// switch attacker and defender
			temp = attacker;
			attacker = defender;
			defender = temp;
		}
		
		other.toConsole();
		player.toConsole();
		
		/*System.out.println(attacker.getName()+" is now attacking.");
		
		other.toConsole();
		player.toConsole();
		
		attacker.setAttackingCards();
		defender.setDefendingCards();
		move = defender.getMove();
		while(move!=null){
			move.consoleDisplay();
			move.execute();
			move = defender.getMove();
		}
		input.close();*/

	}

}
