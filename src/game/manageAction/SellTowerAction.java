package game.manageAction;

import game.Game;
import game.player.*;
import game.tower.*;

/**A class to sell a tower */
public class SellTowerAction implements ManageAction {
    /**the player */
    protected Player player ;
    /**the tower to sell */
    protected Tower tower;
    /**the bloontd game */
    protected Game game;

    /**
     * An action with all the informations to sell a tower that a player already bought. 
     * @param player the player who's playing the bloontd game
     * @param tower the tower to sell
     * @param game the bloontd game
     */
    public SellTowerAction(Player player, Tower tower,Game game){
        this.player = player;
        this.tower = tower;
        this.game = game;
    }

    /**
     * The method to sell the tower
     */
    @Override
    public void perform(){
        if (this.game.getTowers().contains(this.tower)){
            this.player.sellTower(this.tower);        
        }
        System.out.println("you don't own this tower");
    }

    @Override
    public String toString(){
        return "Buy a tower";
    }
}
