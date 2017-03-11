package com.chasebabbitt.cardgame;
import com.chasebabbitt.cardgame.player.Player;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jtownsend
 */
public class CampaignTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testNewGame() throws Exception {
        System.out.println("NewGame");
        Campaign instance = new Campaign();
        
        //Assert DEFAULT VALUES present in Campaign after NewGame() is called
        instance.NewGame();
        assertEquals(instance.getPlayer().getName(), "Player 1");
        assertEquals(instance.getOpponents().size(), 3);
        assertEquals(instance.getWinCount(), 0);
        
        //Change a value, then call SaveGame() and compare to default values
        instance.addOpponents(2);
        instance.NewGame();
        assertEquals(instance.getOpponents().size(), 3);
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        System.out.println("SaveGame");
        Campaign instance = new Campaign();
        
        //Start a new game, change a value, then save. Contents of save file should not match default value when loaded
        instance.NewGame();
        instance.addOpponents(2); //value changed
        instance.SaveGame();
        instance.LoadGame();
        assertNotEquals(instance.getOpponents().size(), 3);
        assertEquals(instance.getOpponents().size(), 5);
    }

    @Test
    public void testAddOpponents() throws IOException {
        System.out.println("addOpponents");
        
        Campaign instance = new Campaign();
        
        //check number of opponents before and after method call
        assertEquals(instance.getOpponents().size(), 3);
        instance.addOpponents(2);
        assertEquals(instance.getOpponents().size(), 5);
    }

    /*
    //Note: Duel class altered for use in testing. 
    //Duel's "Play()" function was hardcoded to arbitrarily return the player or
    //the opponent to simulate a win/loss without needing to play an entire game.
    //As such, this test case is commented out when included with actual game
    @Test
    public void testRunDuel() throws Exception {
        System.out.println("RunDuel");
        Campaign instance = new Campaign();
        
        //When player wins a duel, win count in incremented by 1 (with altered Duel class)
        instance.NewGame();
        instance.RunDuel();
        assertEquals(instance.getWinCount(), 1);
        //After a duel, there is one less opponent in the list
        assertEquals(instance.getOpponents().size(), 2);
        //When player loses a duel, win count does not change but there is still one less opponent
        //(Duel class altered to make the player lose)
        instance.RunDuel();
        assertEquals(instance.getWinCount(), 1);
        assertEquals(instance.getOpponents().size(), 1);
    }
    */
}
