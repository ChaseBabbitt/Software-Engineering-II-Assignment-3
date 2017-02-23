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
    private Player player;
    private ArrayList<Player> opponents;
    private int winCount;
    private String saveFilePath;
    
    public Campaign() throws FileNotFoundException, IOException{
        //default values
        player = new Player();
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
        player = new Player("Player 1", 20);
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
        player = new Player(name, startingHealth);
        winCount = wins;
        addOpponents(numOpponents);
    }
    
    /*
    Appends the opponent roster with a number of opponents
    */
    public void addOpponents(int num){
        for(int i = 0; i < num; i++){
            opponents.add(new Player("Opponent", 20));
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
            Duel currentDuel = new Duel(getPlayer(), currentOpponent);
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
}
