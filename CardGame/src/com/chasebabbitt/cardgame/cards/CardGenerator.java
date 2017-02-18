package com.chasebabbitt.cardgame.cards;

import java.util.Random;

import com.chasebabbitt.cardgame.cards.concrete.*;


public class CardGenerator {
	
	public static Card getCard(){
		Random rand = new Random();
		int randomcard = rand.nextInt(22);
		return getCard(randomcard);		
	}
	
	public static Card getCard(int index){
		Card card = null;
		
		switch(index){
		case 0:
			card = new CrushDecoration(new BlasphemousHorror());
			//card = new Card(manager.get("Cards/Blasphemous Horror.jpg", Texture.class),"Blasphemouse Horror",6,6,6);
			break;
		case 1:
			card = new DireSpider();
			break;
		case 2:
			card = new FlyingDecoration(new DuskwingMaiden());
			//card = new FlyingDecoration(new DuskwingMaiden());
			//card = new Card(manager.get("Cards/Duskwing Maiden.jpg", Texture.class),"Duskwing Maiden",4,3,2,Card.FLY);
			break;
		case 3:
			card = new Llama();
			//card = new Card(manager.get("Cards/Llama.jpg", Texture.class),"Llama",2,2,2);
			break;
		case 4:
			card = new PaladinOfNagaan();
			//card = new Card(manager.get("Cards/Paladin of Naagaan.jpg", Texture.class),"Paladin of Naagaan",5,3,5);
			break;
		case 5:
			card = new FlyingDecoration(new Phantom());
			//card = new Card(manager.get("Cards/Phantom.jpg", Texture.class),"Phantom",1,1,1,Card.FLY);
			break;
		case 6:
			card = new CrushDecoration(new Rhinoceros());
			//card = new Card(manager.get("Cards/Rhinoceros.jpg", Texture.class),"Rhinoceros",3,3,3,Card.CRUSH);
			break;
		case 7:
			card = new RigidBuffalo();			
			//card = new Card(manager.get("Cards/Rigid Buffalo.jpg", Texture.class),"Rigid Buffalo",2,2,3);
			break;
		case 8:
			card = new RisenLancer();
			//card = new Card(manager.get("Cards/Risen Lancer.jpg", Texture.class),"Risen Lancer",2,3,1);
			break;
		case 9:
			card = new SteadfastDecoration( new SkyguardDecoration(new RotrootEnchanter()));
			//card = new Card(manager.get("Cards/Rotroot Enchanter.jpg", Texture.class),"Rotroot Enchanter",5,3,5,Card.SKYGUARD);
			break;
		case 10:
			card = new RottingBuffalo();
			//card = new Card(manager.get("Cards/Rotting Buffalo.jpg", Texture.class),"Rotting Buffalo",3,2,3);
			break;
		case 11:
			card = new CrushDecoration(new Smashodon());
			//card = new Card(manager.get("Cards/Smashodon.jpg", Texture.class),"Smashodon",6,5,5);
			break;
		case 12:
			card = new FlyingDecoration( new SpearcliffPegasus());
			//card = new Card(manager.get("Cards/Spearcliff Pegasus.jpg", Texture.class),"Spearcliff Pegasus",3,2,1,Card.FLY);
			break;
		case 13:
			card = new SteadfastDecoration(new StalwartSpirit());
			//card = new Card(manager.get("Cards/Stalwart Spirit.jpg", Texture.class),"Stalwart Spirit",4,3,3);
			break;
		case 14:
			card = new StartouchedBrave();
			//card = new Card(manager.get("Cards/Startouched Brave.jpg", Texture.class),"Startouched Brave",4,0,7);
			break;
		case 15:
			card = new SkyguardDecoration(new SterlingSkywatcher());
			//card = new Card(manager.get("Cards/Sterling Skywatcher.jpg", Texture.class),"Sterling Skywatcher",2,2,2,Card.SKYGUARD);
			break;
		case 16:
			card = new TrottingBuffalo();
			//card = new Card(manager.get("Cards/Trotting Buffalo.jpg", Texture.class),"Trotting Buffalo",2,2,3);
			break;
		case 17:
			card = new CrushDecoration(new TyrannosaurusHex());
			//card = new Card(manager.get("Cards/Tyrannosaurus Hex.jpg", Texture.class),"Tyrannosaurus Hex",6,6,4);
			break;
		case 18:
			card = new VineTrap();
			//card = new Card(manager.get("Cards/Vine Trap.jpg", Texture.class),"Vine Trap",3,2,4);
			break;
		case 19:
			card = new WildChild();
			//card = new Card(manager.get("Cards/Wild Child.jpg", Texture.class),"Wild Child",2,2,2);
			break;
		case 20:
			card = new FlyingDecoration( new ZombieVulture());
			//card = new Card(manager.get("Cards/Zombie Vulture.jpg", Texture.class),"Zombie Vulture",5,4,1,Card.FLY);
			break;
		case 21:
			card = new Zombie();
			//card = new Card(manager.get("Cards/Zombie.jpg", Texture.class),"Zombie",2,2,2);
			break;
		}
		return card;
	}

}
