package com.chasebabbitt.cardgame.cards.hearthclone;


import java.util.Random;

import com.chasebabbitt.cardgame.cards.*;
import com.chasebabbitt.cardgame.cards.hearthclone.concretecards.*;


public class HearthcloneCardFactory implements CardFactory{

	@Override
	public  Card createCard(){
		Random rand = new Random();
		int randomcard = rand.nextInt(6);
		return createCard(randomcard);		
	}

	@Override
	public Card createCard(int index) {
		Card card = null;
		
		switch(index){
		case 0:
			card = new WindfuryDecoration(new DamageDecoration(new FlyingMachine()));	
			break;
		case 1:
			card = new DamageDecoration(new GnomereganInfantry());
			break;
		case 2:
			card = new TauntDecoration(new DamageDecoration(new IronfurGrizzly()));
			
			break;
		case 3:
			card = new TauntDecoration(new DamageDecoration(new MogushanWarden()));
			break;
		case 4:
			card = new DivineShieldDecoration(new DamageDecoration(new SilvermoonGuardian()));
			break;
		case 5:
			card = new DamageDecoration(new StormwindKnight());
			break;
		}
		return card;
	}

	@Override
	public Card createCard(String cardname) {
		if(cardname.equals("Ironfur Grizzly"))
			return new TauntDecoration(new DamageDecoration(new IronfurGrizzly()));
		else if(cardname.equals("Mogushan Warden"))
			return new TauntDecoration(new DamageDecoration(new MogushanWarden()));
		else if(cardname.equals("Silvermoon Guardian"))
			return new DivineShieldDecoration(new DamageDecoration(new SilvermoonGuardian()));
		else if(cardname.equals("Flying Machine"))
			return new WindfuryDecoration(new DamageDecoration(new FlyingMachine()));	
		return null;
	}

}
