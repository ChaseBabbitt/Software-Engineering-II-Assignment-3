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
		player.setSmartDefenseStrategy();
		other.setRandomCaseA();
		other.setOpponent(player);
		
		duel = new HexDuel(player,other);
		winner = duel.play();
		System.out.println(winner.getName()+" wins.");	

	}

}
