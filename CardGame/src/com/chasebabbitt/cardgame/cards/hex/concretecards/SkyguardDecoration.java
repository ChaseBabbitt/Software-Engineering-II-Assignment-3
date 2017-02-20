package com.chasebabbitt.cardgame.cards.hex.concretecards;

import com.chasebabbitt.cardgame.cards.*;

public class SkyguardDecoration extends AbilitiesDecorator{
	public SkyguardDecoration(Card card){
		super(card);
		keywords = Card.SKYGUARD;		
	}
	
	public String toString(){
		return card.toString()+" Skyguard";
	}

}
