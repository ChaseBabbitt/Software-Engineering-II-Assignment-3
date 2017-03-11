package com.chasebabbitt.cardgame;
import com.chasebabbitt.cardgame.player.Player;

import java.io.*;
import java.util.*;

/**
 * Rather than use a SaveFile class for the Duel class to use, Chase 
 * recommended having instead a Campaign class that calls a series of duels
 * with the option to start a new campaign, continue, or quit between Duels.
 * 
 * For now, file with save information will be in this format:
 * (String)playername
 * (int) startingHealth
 * (int) winCount
 * (int) numOpponents
 * --Subject to change--, will need to support decklists
 * 
 * @author jtownsend
 */
public class Campaign {
    private HearthclonePlayer player;
    private ArrayList<HearthclonePlayer> opponents;
    private int winCount;
    
    public Campaign() throws FileNotFoundException, IOException{
        //default values
        player = new HearthclonePlayer();
        opponents = new ArrayList<>();
        winCount = 0;
        
        //Load whatever save (default new or otherwise) into this instance of campaign
        LoadGame();
    }
    
    /*
    Resets the campaign and player stats
    Overwrites the save file contents with the new info
    */
    public void NewGame() throws IOException{
        //initialize new player and opponents
        player = new HearthclonePlayer("Player 1", 20);
        addOpponents(3);
        winCount = 0;
        
        SaveGame();
    }
    
    
    /*
    An automatic save that is called in between games
    */
    public void SaveGame() throws IOException{
        //the non-append constructor for FileWriter clears the file when called
        BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
        
        bw.write(getPlayer().getName()); //player name
        bw.newLine();
        bw.write(20 + ""); //player starting hp
        bw.newLine();
        bw.write(getWinCount() + ""); //win count
        bw.newLine();
        bw.write(opponents.size() + ""); //opponents left
        bw.close();
    }
    
    /*
    Reads the save.txt file to get campaign information of the player and opponents
    playername, startingHealth, winCount, numOpponents
    */
    public void LoadGame() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("save.txt"));
        
        String name = br.readLine();
        int startingHealth = Integer.parseInt(br.readLine());
        int wins = Integer.parseInt(br.readLine());
        int numOpponents = Integer.parseInt(br.readLine());
        
        //populate local information with info from file
        player = new HearthclonePlayer(name, startingHealth);
        winCount = wins;
        addOpponents(numOpponents);
    }
    
    /*
    Appends the opponent roster with a number of opponents
    */
    public void addOpponents(int num){
        for(int i = 0; i < num; i++){
            opponents.add(new HearthclonePlayer("Opponent", 20));
        }
    }
    
    /*
    Checks if there are opponents, then pops the first enemy in the list
    to run a duel against. Auto saves after the duel
    */
    public void RunDuel() throws IOException{
        if(opponents.isEmpty())
            System.out.println("Defeated all opponents, you win!\nWinCount: " + getWinCount());
        else{
            Player currentOpponent = opponents.remove(0);
            HearthcloneDuel currentDuel = new HearthcloneDuel(getPlayer(), currentOpponent);
            Player winner = currentDuel.play();
            if(winner.getName().equals(getPlayer().getName()))
                winCount++;
            SaveGame();
        }
    }
    
    /*
    Prints out prompt text for the 3 options in a loop until a valid action
    is taken
    */
    public void MenuPrompt() throws IOException{
        System.out.println("\nWhat would you like to do?");
        boolean actionTaken = false;
        
        while(!actionTaken){
            System.out.println("1) New Game\n2) Continue\n3)Quit ");
            actionTaken = MenuAction(getIntChoice(1, 3));
        }
    }
    
    /*
    Menu actions are called depending on the choice made in the prompt
    An invalid choice will return a false value
    */
    public boolean MenuAction(int choice) throws IOException{
        switch(choice){
                case 1:
                    NewGame();
                    RunDuel(); //Player plays first game of new campaign
                    break;
                case 2:
                    RunDuel(); //Player plays a game
                    break;
                case 3:
                    //Quit, do nothing and exit loop
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    return false;
            }
        return true;
    }
    
    /*
    Reads in an integer from user and returns it
    */
    public int getIntChoice(int min, int max){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while(choice < min || choice > max){
            System.out.println("Input a number " + min + " - " + max);
            choice = scanner.nextInt();
        }
        return choice;
    }
    
    public Player getPlayer() {
        return player;
    }

    public int getWinCount() {
        return winCount;
    }
    
    public ArrayList<Player> getOpponents() {
        return opponents;
    }
    
    
    /***************************************
    SAVE & LOAD GAME STATES
    ***************************************/
    
     /*
    During a duel on the player's turn, a valid move can be to save the current
    game state with each player's boards, hand, and deck.
    
    public void SaveGameState() throws IOException{
        //Save the same information as a normal save
        //the non-append constructor for FileWriter clears the file when called
        BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"));
        
        bw.write(getPlayer().getName()); //player name
        bw.newLine();
        bw.write(getPlayer().getHealth()); //current player hp
        bw.newLine();
        bw.write(getWinCount() + ""); //win count
        bw.newLine();
        bw.write(opponents.size() + ""); //opponents left
        
        //save the player's card collection
        for(int i = 0; i < player.getCards().getSize(); i++){
            bw.write(player.getCards().get(i).getName());
            bw.newLine();
        }
        
        //save the player's hand
        bw.write("#P1HAND");
        bw.newLine();
        for(int i = 0; i < player.getHand().getSize(); i++){
            bw.write(player.getHand().get(i));
            bw.newLine();
        }
        
        //save the player's board
        bw.write("#P1BD");
        bw.newLine();
        for(int i = 0; i < player.field.getCards().getSize(); i++){
            bw.write(player.field.getCards().get(i));
            bw.newLine();
        }
        
        //save the player's graveyard
        bw.write("#P1GY");
        bw.newLine();
        
        //place marker for next player's information
        bw.write("#PLAYER2");
        bw.newLine();
        bw.write(getPlayer().getHealth()); //current opponent hp
        bw.newLine();
        
        //save the opponent's hand
        bw.write("#P2HAND");
        bw.newLine();
        for(int i = 0; i < player.opponent.getHand().getSize(); i++){
            bw.write(player.opponent.getHand().get(i));
            bw.newLine();
        }
        
        //save the opponent's board
        bw.write("#P2BD");
        bw.newLine();
        for(int i = 0; i < player.opponent.field.getCards().getSize(); i++){
            bw.write(player.opponent.field.getCards().get(i));
            bw.newLine();
        }
        
        //save the opponent's graveyard
        bw.write("#P2GY");
        bw.newLine();
        
        bw.close();
    }*/

    
    /*
    A menu action will later be implemented to allow a game state to be loaded
    from any menu
    
    public void LoadGameState() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("save.txt"));
        
        String name = br.readLine();
        int startingHealth = Integer.parseInt(br.readLine());
        int wins = Integer.parseInt(br.readLine());
        int numOpponents = Integer.parseInt(br.readLine());

        //populate local information with info from file
        player = new HearthclonePlayer(name, startingHealth);
        winCount = wins;
        addOpponents(numOpponents);
        
        String input = br.readLine(); //get #P1HAND
        while(!"#P1BD".equals(input)){
            //player.getHand().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        while(!"#P1GY".equals(input)){
            //player.getField().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        while(!"#PLAYER2".equals(input)){
            //player.getGY().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        HearthclonePlayer opponent = new HearthclonePlayer(); //make opponent
        while(!"#P2BD".equals(input)){
            //opponent.getHand().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        while(!"#P2GY".equals(input)){
            //opponent.getField().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        while(input != null){
            //opponent.getGY().addCard(input) //implement switch statement later to add specific cards based on input
            input = br.readLine();
        }
        
        br.close();
        
        //From here, call new mid-game duel with loaded players and info
        RunDuel(player1, player2);
        */
    }
    
}
