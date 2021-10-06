package battleship_;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.io.*;
import java.lang.SecurityException;
import java.util.NoSuchElementException;
import java.util.Collections;

public class Scoreboard {
	//Instance / Class variables
	private ArrayList<Player> playerlist;
	
	//Constructors
	public Scoreboard(){
		
	}
	//Getters & Setters
	public ArrayList<Player> getPlayerList(){
		return playerlist;
	}
	//Other Methods
	/**Reads the file "Score.txt" and gets the names and scores of the top 10 players inside the file.
	 * 
	 */
	public void readScoreFile(){
		playerlist = new ArrayList<Player>();
		Scanner scoreReader;
		try{
			scoreReader= new Scanner(new File("Score.txt"));
			while(scoreReader.hasNext()){
				playerlist.add(new Player(scoreReader.next(),scoreReader.nextInt()));
			}
			if(playerlist.size() < 10){
				throw new NoSuchElementException();
			}
		}
		catch(FileNotFoundException fe){
			System.out.println("Score file not found. A new score file will be generated.");
			generateScoreFile();
			playerlist = new ArrayList<Player>();
			playerlist = new ArrayList<Player>();
			for(int c = 1; c <= 10; c++){
				playerlist.add(new Player(String.format("Player%d",c),10+2*c));
			}
		}
		catch(NoSuchElementException ex){
			System.out.println("File improperly formed. A new file will be generated");
			generateScoreFile();
			playerlist = new ArrayList<Player>();
			for(int c = 1; c <= 10; c++){
				playerlist.add(new Player(String.format("Player%d",c),10+2*c));
			}
		}
	}
	
	/**Creates a new file which contains the names and score of the top 10 players.
	 * 
	 */
	public void writeScoreFile(){
		try{
			Formatter scoreWriter = new Formatter(new File("Score.txt"));
			for(Player x : playerlist){
				scoreWriter.format("%s %d\n",x.getName(),x.getScore());
			}
			scoreWriter.close();
		}
		catch(FileNotFoundException fe){
			System.out.println("Error creating score file.");
			System.exit(1);
		}
		catch(SecurityException se){
			System.out.println("Error: Program does not have write access! Shutting down..");
			System.exit(1);
		}
	}
	/**Creates the default file for the file "Score.txt".
	 * 
	 */
	private void generateScoreFile(){
		try{
			Formatter scoreGenerate = new Formatter("Score.txt");
			for(int c = 1; c <= 10; c++){
				scoreGenerate.format("Player%d %d\n",c,10+2*c);
			}
			scoreGenerate.close();
		}
		catch(FileNotFoundException fe){
			System.out.println("Error creating new score file. Please contact administrator.");
			System.exit(1);
		}
		catch(SecurityException se){
			System.out.println("Error: Program does not have write access! Shutting down..");
			System.exit(1);
		}
		
	}	
	/**Shows the score of the top ten players.
	 * 
	 */
	public void showTopScores(){
		System.out.println("====================================================================\n"+
				"                       TOP     TEN      SCORES                       \n"+
				"====================================================================\n"+
				"\t\tName\t\t\t     Steps Taken");
		Collections.sort(playerlist);
		for(int x = 0; x < 10; x++){
			System.out.printf("\t%2d.\t%-20s\t\t%4d\n",x+1,playerlist.get(x).getName(),playerlist.get(x).getScore());
		}
	}
	//toString
}
