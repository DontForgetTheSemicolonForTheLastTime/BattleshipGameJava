/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship_;

public class Potion extends Item{
    
    public Potion(){
        super();
        this.symbol = potionSymbols[randomNum.nextInt(potionSymbols.length)];
    }
    
    public int getSymbol(){
        return this.symbol;
    }
    
	/**
     * This method executes the potion based on its symbol.
     * @param board Contains the board that is modified by the potion.
     * @param player Contains the player that is modifie.
     */
    public void action(Board board, Player player){
        System.out.println("\n *******YOU GOT A POTION! NICE!*******");
        switch(this.symbol){
            case 2:
                player.lifeGained(1);
                System.out.println("\n**** 1 Life Gained!****\n");
                break;
            case 3:
                int[] Scoordinates = board.findItem(1);
                Ship ship = board.getShip(Scoordinates[0],Scoordinates[1]);
                board.unmask(ship);
                ship.shipFound(player);
                System.out.println("\n**** 1 Ship Revealed!****\n");
                break;
            case 4:
                int trapSymbol = trapSymbols[randomNum.nextInt(trapSymbols.length)];
                int[] Tcoordinates = board.findItem(trapSymbol);
                board.unmask(Tcoordinates[0],Tcoordinates[1]);
                System.out.println("\n**** 1 Trap Revealed!****\n");
                break;
            default:
        }
    }
}
