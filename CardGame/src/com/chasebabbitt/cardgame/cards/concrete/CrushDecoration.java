package com.chasebabbitt.cardgame.cards.concrete;

import com.chasebabbitt.cardgame.cards.AbilitiesDecorator;
import com.chasebabbitt.cardgame.cards.Card;

public class CrushDecoration extends AbilitiesDecorator{
	public CrushDecoration(Card card){
		super(card);
		keywords = Card.CRUSH;
		
	}
	
	public String toString(){
		return card.toString()+" Crush";
	}


}
