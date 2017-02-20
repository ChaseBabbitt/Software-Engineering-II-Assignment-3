package com.chasebabbitt.cardgame.cards.hex.concretecards;

import com.chasebabbitt.cardgame.cards.*;

public class SteadfastDecoration extends AbilitiesDecorator{
	public SteadfastDecoration(Card card){
		super(card);
		keywords = Card.STEADFAST;
	}
	public String toString(){
		return card.toString()+" Steadfast";
	}

}
