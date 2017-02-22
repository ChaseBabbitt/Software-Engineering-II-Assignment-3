package com.chasebabbitt.cardgame.player;

import java.util.ArrayList;

import com.chasebabbitt.cardgame.cards.Card;
import com.chasebabbitt.cardgame.cards.CardFactory;
import com.chasebabbitt.cardgame.cards.hex.HexCardFactory;
import com.chasebabbitt.cardgame.strategy.*;

public class Player {
		// The player's name
		String name;
		// The player's health
		int health;
		// The normal starting value of the player's health
		int startinghealth;
		// A reference to the opposing player
		Player opponent;
		// The player's hand
		Hand hand;
		// The player's field, all the cards the player has in play
		public Field field;
		// The player's deck, from which new cards are drawn
		Deck deck;
		// The player's graveyard, where cards go after they've been used or defeated
		Graveyard graveyard;
		// The player's strategy
		Strategy strategy;
		// A List of Card objects currently involved in an attack
		ArrayList<Card> attackingcards;
		// A List of Card objects that are currently available for defense
		ArrayList<Card> defendingcards;
		// A reference to a CardFactory class
		CardFactory cardgenerator;
		// The maximum resources the player currently has
		int maxresources;
		// The current value of the the resources the player currently has
		int currentresources;
		
	//initialize player
	public Player(String name,int health){
		this.name = name;
		this.startinghealth = health;
		this.health = startinghealth;
		
		cardgenerator = new HexCardFactory();
		
		strategy = new DumbStrategy();
		deck = new Deck(cardgenerator,60);
		field = new Field();
		hand = new Hand();
		graveyard = new Graveyard();
		
		attackingcards = new ArrayList<Card>();
		defendingcards = new ArrayList<Card>();
	}

	/**
	 * Method for dealing damage to a player, reduces the player's health by attackPoints
	 * @param attackPoints the damage being done to the player
	 */
	public void dealDamage(int attackPoints) {
		
		System.out.println(name+" was dealt "+attackPoints+" damage.");
		health -= attackPoints;
	}

	/**
	 * Getter for the cards in the Player's field
	 * @return an ArrayList of Card from the field
	 */
	public ArrayList<Card> getCards() {
		
		return field.getCards();

	}
	/**
	 * Setter for opponent
	 * @param opponent an object of type Player that is the opposing Player
	 */
	public void setOpponent(Player opponent){
		this.opponent = opponent;
		
	}
	/**
	 * Method for deciding a move
	 * @return a Move object generated by the Player's strategy
	 */
	public Move getMove(){
		return strategy.getMove(this, opponent);
		
	}

	public void removeAttackingCard(Card attackingcard) {
		
		attackingcards.remove(attackingcard);
	}

	public void removeDefendingCard(Card defendingcard) {
		// TODO Auto-generated method stub
		defendingcards.remove(defendingcard);
		
	}
	
	/**
	 * Accepts a Card object and attempts to remove that Card from the field and place it in the graveyard
	 * @param card The Card to be removed from the field
	 */
	public void moveCardToGraveyard(Card card) {
		if(field.moveCardToGraveyard(card))
			graveyard.toGraveyard(card);
		
	}
	
	public void toConsole(){
		System.out.println("");
		System.out.println("**************************************************************");
		System.out.println(name+"'s Field:             Health: "+health+"/"+startinghealth);
		field.toConsole();
		System.out.println(name+"'s Hand:            Deck: "+deck.size());
		hand.toConsole();
		System.out.println("**************************************************************");
	}
	
	public void setRandomCaseD(){
		field.discard();
		setPlayerHealth(20);
		field.addCard();
		field.addCard();
		field.addCard();
		field.addCard();
		setDefendingCards();
		/*defendingcards.clear();
		defendingcards.addAll(getCards());// = getCards();*/
	}
	/**
	 * Setter method for health, sets health to a specific value
	 * @param health an integer value that health will be set to
	 */
	private void setPlayerHealth(int health) {
		this.health = health;

	}

	/**
	 * Method for adding all available cards from the Player's field to the list of defendingcards
	 * to be called as the beginning of a combat phase when the Player is defending
	 */
	public void setDefendingCards() {
		defendingcards.clear();
		defendingcards.addAll(field.getCards());
	}
	/**
	 * Getter method for defending cards
	 * @return an ArrayList of Card objects that can defend
	 */
	public ArrayList<Card> getDefendingCards(){
		return defendingcards;
	}
	
	/**
	 * Method for adding all available card from the Player's field to the list of attackingcards
	 * to be called at the beginning of a combat phase in which the Player is attacking
	 */
	public void setAttackingCards(){
		attackingcards.clear();
		attackingcards.addAll(field.getCards());
		
	}
	/**
	 * Getter method for attackingcards
	 * @return an ArrayList of Card objects that are attacking
	 */
	public ArrayList<Card> getAttackingCards(){
		return attackingcards;
	}
	/**
	 * Draws a card from the Player's deck and adds it to their hand
	 */
	public void drawCard(){
		hand.draw(deck.draw());
	}

	/**
	 * 
	 */
	public void setRandomCaseA(){
		field.discard();
		setPlayerHealth(20);
		field.addCard();
		field.addCard();
		field.addCard();
		field.addCard();
		setAttackingCards();
	}

	/**
	 * Getter method for name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter method for health
	 * @return health integer value
	 */
	public int getHealth(){
		return health;
	}

	/**
	 * Plays the first card in the Player's hand
	 * Can be changed later to make smarter choices about card playing / obeying costs
	 */
	public void playCard() {
		field.addCard(hand.getCard());
		
	}
	
	/**
	 * Sets the Player's defensive strategy to the dumb strategy
	 */
	public void setDumbDefenseStrategy(){
		strategy = new DumbStrategy();
	}
	
	/**
	 * Sets the Player's defensive strategy to the smart strategy
	 */
	public void setSmartDefenseStrategy(){
		strategy = new DefenseStrategy();
	}
	/**
	 * Increments the Player's max resources
	 */
	public void incrementResources(){
		maxresources++;
	}
	/**
	 * Method to make current resources = maxresources, to be called at the beginning of a turn
	 */
	public void refreshResources(){
		currentresources = maxresources;
	}
	/**
	 * Getter method for currentresources
	 * @return currentresources
	 */
	public int getResources(){
		return currentresources;
	}
	/**
	 * Method for decreasing currentresources by the passed cost value
	 * @param cost the cost of a card being played
	 */
	public void useResources(int cost){
		currentresources -= cost;
	}
	/**
	*	A way to view the players hand of cards and play a card
	* 	-RK
	**/
	public ArrayList<Card> getHand(){
		return hand.cards;
	}	/**
	 * Plays the selected card in the Player's hand
	 */
	public void playCard(Card card) {
		field.addCard(hand.playCard(card));
		
	}
}
