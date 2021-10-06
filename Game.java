package battleship_;
import java.util.*;

public class Game {
	
	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);
		Board board;
		int userRow;
		int userCol;
		Difficulty Beginner = new Difficulty("Beginner",80,10,18);
		Difficulty Intermediate = new Difficulty("Intermediate",50,20,18);
		Difficulty Advanced = new Difficulty("Advanced",20,30,18);
		
		
			System.out.print("Welcome to the game of battleship!\n"+ " Try to find 5 ships before losing all your lives!\n"+ " Look out for traps & potions!"+ " Enter your name to start: ");
			String Pname = input.nextLine();
			Player player = new Player(Pname);
			

		System.out.print("Difficulty Levels:\n"  + "1 = Beginner \n" + "2 = Intermediate \n" + "3 = Advanced \n" + "Input 1,2 or 3 to pick your difficulty:");
		
		int diff = input.nextInt();
		
		
		switch(diff) {
                    case 1:
                            board = new Board(Beginner);
                            break;
                    case 2:
                            board = new Board(Intermediate);
                            break;
                    case 3:
                            board = new Board(Advanced);
                            break;
                    default:
                            board = new Board(Beginner);
                }
		
		board.printDisplayBoard();
		player.printGameStats();
                
		while ((player.getLife() > 0) && (player.getShipsFound() < 5)){
			

			try {
				System.out.print("Enter row: ");
				userRow = input.nextInt();
				System.out.print("Enter col: ");
				userCol = input.nextInt();
				if (board.indexIsLegit(userRow,userCol)) {
					
                    board.processInput(userRow-1,userCol-1,player); //-1 to match indexes of array
                    board.printDisplayBoard();
                    player.printGameStats();
		
					}
					else {
						
						System.out.println("\n****NOT VALID COORDINATES**.\n");
						board.printDisplayBoard();
						
					}
					
			}catch (InputMismatchException e) {
				
				
				System.out.println("That's not even a number");
				input.next();
				
			}
			
                }
		
				//high score
		
            
	

		System.out.print("The game has ended!");
		if (player.getLife() < 1 ){  
		System.out.println("Game Over, you're out of lives. Try again another time!");  
		}  
		else{
        System.out.println("Congrats, you found 5 ships in the last minute!");
		}
		System.out.println("You Won");
	}

}