package com.chasebabbitt.cardgame;


import com.chasebabbitt.cardgame.player.Player;

public abstract class Duel {
	protected Player player1, player2;
	protected Player attacker;
	protected Player defender;
	
	public Duel(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
		attacker = player1;
		defender = player2;
		
	}
	
	public Player play(){
		Player winner;
		while(null==(winner=findWinner())){
			beforeTurn();
			turn();
			afterTurn();
			switchroles();	
		}
		
		
		return winner;
	}
	
	protected abstract Player findWinner();
	
	/**
	 * Optional method to be overloaded for things that should happen before a turn starts	
	 */
	protected void beforeTurn(){}
	
	/**
	 * Method that must be implemented 
	 */
	protected abstract void turn();
	
	/**
	 * Optional method to be overloaded for things that should happen at the end of a turn
	 */
	protected void afterTurn(){}
	
	/**
	 * Method that switches that roles of the players for the next turn
	 */
	protected void switchroles(){
		Player temp;
		temp = attacker;
		attacker = defender;
		defender = temp;
	}
	
}
 