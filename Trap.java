/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_;
/**
 * This class has inherits the methods and Item class
 */
public class Trap extends Item{
	/**
     * The switch case is to identify which type of trap it is for each type of method ; low or high danger 
     * @param board References the board class.
     * @param player References the player class.
     */
    public Trap(){
        super();
        this.symbol = trapSymbols[randomNum.nextInt(trapSymbols.length)];
        
    }
    
    public int getSymbol(){
        return this.symbol;
    }
    /**
     * The switch case is to identify which type of trap it is for each type of method ; low or high danger 
     * @param board References the board class.
     * @param player References the player class.
     */
    public void action(Board board, Player player){
        System.out.println("\n *******YIKES, A TRAP!*******");
        switch(this.symbol){
            case 5:
                player.lifeGained(-1);
                System.out.println("\n**** You lost a Life!****\n");
                break;
            case 6:
                player.lifeGained(-2);
                System.out.println("\n**** You lost 2 lives!****\n");
                break;
            default:

        }
    }
    
    
}
