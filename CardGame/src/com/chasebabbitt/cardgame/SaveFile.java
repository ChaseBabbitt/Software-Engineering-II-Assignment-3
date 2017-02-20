package com.chasebabbitt.cardgame;
import com.chasebabbitt.cardgame.player.Player;

/**
 * The SaveFile class holds a Player object to be carried over between 
 * games within a single play session. When a new Duel is started, it will 
 * recieve the SaveFile's Player object as a parameter for Player 1.
 * 
 * Because the player is stored in the save file, any future implementations
 * that access the player's deck or card collection (booster packs, campaign
 * rewards, etc.) will be done to the SaveFile's Player object, which in turn
 * gets passed to duels in the campaign.
 * 
 * @author jtownsend
 */
public class SaveFile {
    protected Player profilePlayer;
    protected int campaignLevel;
    
    /*
    Default Constructor for NEW GAME with blank slate Player object
    */
    public SaveFile(){
        profilePlayer = new Player(); 
        campaignLevel = 0;
    }
    
    /*
    COPY Constructor for if a user wants to copy another profile to the new 
    SaveFile. 
    */
    public SaveFile(SaveFile sv){
        profilePlayer = sv.getPlayer(); 
        campaignLevel = sv.getCampaignLevel();
    }
    
    public void WonAGame(){
        campaignLevel++;
    }
    
    /*
    Blank slates the SaveFile's Player object to "reset" the player's progress
    */
    public void ProfileReset(){
        profilePlayer = new Player(); 
    }
    
    /*
    Getter references
    */
    public Player getPlayer() {
        return profilePlayer;
    }

    public int getCampaignLevel() {
        return campaignLevel;
    }
}
