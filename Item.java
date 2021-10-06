package battleship_;

import java.util.ArrayList;
import java.util.Random;


/**
 * Is a superclass that will be inherited by potion,ship and trap.
 * @param row contains the row position of the item.
 * @param col contains the column position of the item.
 * @param symbol contains the symbol that denotes the item in the gameboard.
 * @param potionSymbols contains array of symbols that denotes the potions in the gameboard.
 * @param trapSymbols array of array of symbols that denotes the traps in the gameboard.
 */
public class Item {
	
     private int row;
     private int col;
     protected int symbol;
     static int[] potionSymbols = {2, 3, 4};
     static int[] trapSymbols = {5, 6};
	 Random randomNum = new Random();


	public int getRow() {
		return row;
	}

	public void setRow(int r) {
		
		this.row = r;
		
	}
        public int getCol() {
                return col;
        }
        public void setCol(int c) {
                this.col = c;
        }
        public void action(Board board, Player player){
        
        }
    }
	
	
