package battleship_;

public class Player {
	
	String name;
	private int life;
	private int shipsFound;
	private int stepsTaken;
	private String difficultyName; //to keep inside the score board
	
	public Player(String nm) {
		
		setName(nm);
		setLife(15);
		setStepsTaken(0);
		shipsFound = 0;
			
	}



	public int getShipsFound() {
		return shipsFound;
	}



	public void setShipsFound(int shipsFound) {
		this.shipsFound = shipsFound;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	public String getDifficultyName() {
		return difficultyName;
	}

	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}
	
	public void lifeGained(int lives) {
		this.life = this.life + lives;
	}
        public void printGameStats(){
                    for(int i=0;i<50;i++){
                        System.out.print("_");
                    }
                    System.out.print("\n");
                    System.out.printf("Lives Left: %d \n",this.life);
                    System.out.printf("Ships found: %d \n", this.shipsFound);
                    System.out.printf("Steps taken: %d \n", this.stepsTaken);
                    
                }



		public int getStepsTaken() {
			return stepsTaken;
		}



		public void setStepsTaken(int stepsTaken) {
			this.stepsTaken = this.stepsTaken + stepsTaken;
		}
        
}
