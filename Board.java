package battleship_;
import java.util.*;


public class Board {

        public static int boardWidth = 60;
        public static int boardheight = 20;
        private Difficulty difficulty;
        Random randomNum = new Random();
        private int [][] ItemIndex = new int[20][60]; //generate 2D list containing item indexes (refer to javadoc)
        public String[][] displayBoard = new String[20][60]; //
           
        //CONTENTS
        ArrayList<Ship>Ships = new ArrayList<Ship>();
        ArrayList<Trap>Traps = new ArrayList<Trap>();
        ArrayList<Potion>Potions = new ArrayList<Potion>();

        //METHOD: generateItems(), so when you create an object in the driver class, you call Board.generateItems first

        public Board(Difficulty diff) {

                setDifficulty(diff);
                populateShips();
                populateTraps();
                populatePotions();
                setDisplayBoard();
        }



        public static int getBoardWidth() {
                return boardWidth;
        }


        public static int getBoardheight() {
                return boardheight;
        }


        public void setDifficulty(Difficulty difficulty) {
                this.difficulty = difficulty;
        }


        
        /**
         * Initializes the display board with '#'in all array indexes at the beginning of the game.
         */
        public void setDisplayBoard() { //initializes displayboard list to ####

            for (int i = 0; i < getBoardheight(); i++) {

                for (int j = 0; j < getBoardWidth(); j++) {

                    displayBoard[i][j] = "#";

                }

            }

        }

        public int[][] getItemIndex(){

            return this.ItemIndex;

        }


        /**
         * This is for programmers to print out the board with all indexes revealed. 
         */
        public void showAll() { //prints item index, where every item is

            for (int i = 0; i < getBoardheight(); i++) {

                for (int j = 0; j < getBoardWidth(); j ++) {

                    if (j == getBoardWidth()-1) {

                            System.out.println(ItemIndex[i][j]);

                    }

                    else {

                            System.out.print(ItemIndex[i][j]);

                    }

                }

            }

        }
        /**
         * This is is used in the method printDisplayBoard().
         * @param i A counter for the display board.
         */
        public void printColNo(int i){
            if(i<9){
                System.out.printf(" %d ",i + 1);
            }
            else{
                System.out.printf("%d ",i + 1);
            }
        }
        
        /**
         * Prints the outer display of the gameboard that is played.
         */
        public void printDisplayBoard() { //simply prints display board

            System.out.print("            1         2         3         4         5         6\n");
            System.out.print("   123456789012345678901234567890123456789012345678901234567890\n");
                for (int i = 0; i < boardheight; i++) {
                    printColNo(i);
                        for (int j = 0; j < boardWidth; j ++) {

                                if (j == boardWidth-1) {

                                        System.out.println(displayBoard[i][j]);

                                }

                                else {

                                        System.out.print(displayBoard[i][j]);

                                }

                        }

                }
                //System.out.printf("You have %d Lives left\n",player.lives);

        }


        /**
         * Method locates the items in the board based on its symbol.
         * @param symbol determines item to be located by method
         * @return returns a 2 dimensional array containing the coordinates of the item found.
         */
        
        public int[] findItem(int symbol) { //finds first occurence of given item type
                for(int i = 0; i < Board.boardheight;i++) {
                        for(int j = 0; j < Board.boardWidth;j++) { //the ship will always be revealed in the first few rows
                                if(this.getItemIndex()[i][j] == symbol) { // if we find an x,y pair that is a ship (1)
                                        int[] coordinates = {i,j}; 
                                        return coordinates;
                                }
                        }
                }
                int[] coordinates = {0,0};
                return coordinates;
            }

        
		        /**
		         * This method takes the row and column and checks if that position is occupied by an item or ship.
		         * @param r contains the row values.
		         * @param c contains the column values.
		         * @return returns true or false.
		         */
                public boolean freeSpot(int r, int c){
                    if(this.getItemIndex()[r][c] == 0) { // if we find an x,y pair that is a ship (1) 
                        return true;	
                    }
                    return false;
                }
                /**
                 *This method unmasks the board depending on the input of the player based on the three cases.
                 * @param r contains the row value.
                 * @param c contains the column value.
                 * @param player Player who inputted the row and column,
                 */
                public void processInput(int r, int c, Player player){ //takes user input and calls necessary methods to process it. Uses switchcase
                    int symbolAtPosition = retrieveSymbol(r,c);
                    if (symbolAtPosition != 7) { //if not yet found, as 7 represents found items

                        player.lifeGained(-1);
                        player.setStepsTaken(1);

                    }

                    switch(symbolAtPosition){
                        case 0:
                            //nothing at position:
                            displayBoard[r][c] = " ";
                            ItemIndex[r][c] = 7; 
                            System.out.println("\n *******YOU MISSED!*******\n");
                            break;
                        case 1:
                            //Ship Found:
                            //Find ship object in ships array
                            Ship ship = getShip(r,c);
                            //Reveal it all - loop through ship coordinates and change corresponding displayboard positions to "O"
                            unmask(ship);
                            //Update player accordingly
                            ship.shipFound(player);
                            System.out.println("\n *******YOU FOUND A SHIP! GOOD JOB!*******\n");
                            break;
                        case 7:
                                System.out.println("\n *******YOU'VE ALREADY MADE THAT MOVE!*******\n");
                        default:
                            if(symbolAtPosition < 5){
                                findPotion(r,c).action(this,player);
                            }
                            else if (symbolAtPosition < 7){
                                findTrap(r,c).action(this,player);
                            }
                            unmask(r,c);        
                    }
                }
                /**
                 * This methods retrieves the symbol occupied by position of the row and column.
                 * @param r contains the row value.
                 * @param c contains the column value.
                 */

                public int retrieveSymbol(int r, int c){ //takes user input and translates it to a corresponding board symbol in ItemIndex
                    return ItemIndex[r][c];
                }

                /**
                 * Finds the ship in the array that occupies the row and column inputted.
                 * @param r contains the row value.
                 * @param c contains the column value.
                 * @return returns the ship found in the array.
                 */
                public Ship getShip(int r, int c){

                    for (Ship s : Ships) {
                        if(r == s.getRow()){
                            for (int i = 0 ; i < s.getLength(); i++) { 		
                                if ( c == ((s.getColumn())[i])) {

                                    return s;
                                }
                            }
                        }
                    }

                    return null;
                }
                /**
                 * This method produces a ship object that does overlap any other ship object on the board.
                 * @return Returns a valid ship.
                 */
                public Ship newShip(){

                    Ship ship = new Ship();
                    //create a ship that does not overlap anything
                    while(true){
                        int r = randomNum.nextInt(Board.boardheight);
                        int c = randomNum.nextInt(Board.boardWidth - ship.getLength());
                        int i = 0; 
                        while(i < ship.getLength()){
                            if(freeSpot(r,c + i)){
                                ship.setColumn(i,c + i);;
                                i++;
                            }
                            else{
                                break;
                            }
                        }
                        if(i == ship.getLength()){
                            ship.setRow(r);
                            return ship;
                        }    
                    }
                }
                /**
                 * This method adds ship to the board.
                 * @param ship Contains the ship object.
                 */
                public void addShip(Ship ship){
                    for (int i = 0; i < ship.getLength(); i++) {

                        ItemIndex[ship.getRow()][ship.getColumn()[i]] = 1;

                    }
                    Ships.add(ship);
                }
                
                /**
                 * Adds all ship to the board based on the difficulty.
                 */
                public void populateShips(){
                    for (int i = 0; i < difficulty.getShipNumber(); i++) {

                        addShip(newShip());

                    }
                }
                
                /**
                 * This method produces a trap object that does overlap any other trap object on the board.
                 */
                public Trap newTrap(){
                    Trap trap = new Trap();

                   while(1==1){ //random condition temporarily
                    int r = randomNum.nextInt(Board.boardheight);
                    int c = randomNum.nextInt(Board.boardWidth);
                    if(freeSpot(r,c)){
                        trap.setRow(r);
                        trap.setCol(c);
                        return trap;
                }
                   }
                }
                
                /**
                 * This method produces a potion object that does overlap any other potion object on the board.
                 */
                public Potion newPotion(){
                   Potion potion = new Potion();

                   while(true){ //random condition temporarily
                        int r = randomNum.nextInt(Board.boardheight);
                        int c = randomNum.nextInt(Board.boardWidth);
                        if(freeSpot(r,c)){
                            potion.setRow(r);
                            potion.setCol(c);
                            return potion;
                        }
                   }
                }
                
                
                /**
                 * This method adds trap to the board.
                 * @param trap Contains the trap object.
                 */
                public void addTrap(Trap trap){

                    ItemIndex[trap.getRow()][trap.getCol()] = trap.getSymbol();
                    Traps.add(trap);

                }
                
                /**
                 * This method adds potion to the board.
                 * @param potion Contains the potion object.
                 */
                public void addPotion(Potion potion){

                    ItemIndex[potion.getRow()][potion.getCol()] = potion.getSymbol();
                    Potions.add(potion);

                }
                
                /**
                 * Adds all traps to the board based on the difficulty.
                 */
                public void populateTraps(){
                    for(int i = 0;i<difficulty.trapNumber;i++){
                        addTrap(newTrap());
                    }
                }
                
                /**
                 * Adds all potions to the board based on the difficulty.
                 */
                public void populatePotions(){
                    for(int i = 0;i<difficulty.potionNumber;i++){
                        addPotion(newPotion());
                    }
                }
                /////////////////////////////////
                /**
                 * Finds trap in the array of traps
                 * @param r contains the row value.
                 * @param c contains the column value.
                 */
                public Trap findTrap(int r,int c){ //in arraylist
                    for(Trap t : Traps){
                        if(r == t.getRow() && c == t.getCol()){
                            return t;
                        }
                    }
                    return null;
                }
                /**
                 * Finds potion in the array of potion.
                 * @param r contains the row value.
                 * @param c contains the column value.
                 */
                public Potion findPotion(int r,int c){ //in arraylist
                    for(Potion p : Potions){
                        if(r == p.getRow() && c == p.getCol()){
                            return p;
                        }
                    }
                    return null;
                }

                /**
                 * Unmasks a ship in the board.
                 * @param ship contains the ship to be unmasked.
                 */
                public void unmask(Ship ship){ //takes ship, loops through its coord, sets corresp ItemIndex to 2 and displayBoard to "O"
                    int r = ship.getRow();
                    int[] c = ship.getColumn();
                    for (int i = 0;  i < ship.getLength(); i++) { //successful
                                        displayBoard[r][c[i]] = "O"; //unmask accordingly
                                        ItemIndex[r][c[i]] = 7;					
                    }


                }
                /**
                 * Unmasks a item in the board.
                 * @param r contains the row value.
                 * @param c contains the column value
                 */
                public void unmask(int r, int c){ //takes item, gets coord, sets corresp ItemIndex 
                    displayBoard[r][c] = Integer.toString(ItemIndex[r][c]);
                    ItemIndex[r][c] = 7;	
                }

                /**
                 * This method checks if the users input is within the the range of 0-20 for rows and 0-60 for columns.
                 * @param r contains the row value.
                 * @param c contains the column value
                 */
                public boolean indexIsLegit(int r, int c) {

                        if ((r > 0 && r <= 20) && (c > 0 && c <= 60)){

                                return true;

                        }

                        else {

                                return false;
                        }

                }
}

		