package battleship_;

import java.util.Arrays;
import java.util.Random;



public class Ship{
	
        static int symbol = 1;
	private int length;
	private int row;
	private int column[];
        Random randomNum = new Random();
	
	public Ship (){
		
		setLength();
                column = new int[this.length];
        }
		
	

	public int getLength() {
		return this.length;
	}

	public void setLength() {
		this.length = 3 + randomNum.nextInt(3);
	}
	
	public int getRow() {
		return this.row;
	}

	public void setRow(int r) {
		
		this.row = r;
		
	}

	public int[] getColumn() {
		return this.column;
	}
	
	public void setColumn(int[] c) {
		
		this.column = c;
		//column size it can appear on
		
	}
        public void setColumn(int i,int c){
             
            this.column[i] = c;
        }
        
        public void shipFound(Player player) {
		player.setShipsFound(player.getShipsFound() + 1);
	}

	

}
