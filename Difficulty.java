package battleship_;

public class Difficulty {
	
	private String name;
	public int shipNumber;
	public int trapNumber;
	public int potionNumber;
	
	/**
     * Creates a pre-set difficulty that we define.
     * @param nm contains the name of the difficulty.
     * @param shipNum contains the number of ships associated with the difficulty level.
     * @param trapNum contains the number of traps associated with the difficulty level.
     * @param potionNum contains the number of potion associated with the difficulty level.
     */
	public Difficulty (String nm, int shipNum, int trapNum, int potionNum){
		
		
		setShipNumber(shipNum);
		setTrapNumber(trapNum);
		setPotionNumber(potionNum);
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String nm) {
		
		this.name = nm;
	}
	public int getShipNumber() {
		return shipNumber;
	}
	public void setShipNumber(int shipNumber) {
		
		this.shipNumber = shipNumber;
	}

	public void setTrapNumber(int trapNumber) {
		this.trapNumber = trapNumber;
	}

	public void setPotionNumber(int potionNumber) {
		this.potionNumber = potionNumber;
	}
	
	

}

