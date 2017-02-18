package com.chasebabbitt.cardgame.cards.concrete;

import com.chasebabbitt.cardgame.cards.*;

public class FlyingDecoration extends AbilitiesDecorator{
	
	public FlyingDecoration(Card card) {
		super(card);
		keywords = Card.FLY;
	}

	public String toString(){
		return card.toString()+" Flying";
	}

}