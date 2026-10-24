package game.manageAction;

import game.Game;
import game.player.*;
import game.tower.*;

/**A class to buy a tower */
public class BuyTowerAction implements ManageAction {
    /**the player */
    protected Player player ;
    /**the tower to place */
    protected Tower tower;
    /**x coordinate to place the tower */
    protected int x;
    /**y coordinate to place the tower */
    protected int y;
    /**the bloontd game */
    protected Game game;

    /**
     * An action with all the informations to buy a tower with player's money and then place it. 
     * @param player the player who's playing the bloontd game
     * @param tower the tower to buy and place
     * @param x the x coodinate to place the tower
     * @param y the y coordinate to place the tower
     * @param game the bloontd game
     */
    public BuyTowerAction(Player player, Tower tower, int x, int y, Game game){
        this.player = player;
        this.tower = tower;
        this.x = x;
        this.y = y;
        this.game = game;
    }

    /**
     * The method to buy and place the tower
     */
    @Override
    public void perform(){
        Tower towerToPlace = this.player.buyTower(tower, x, y);
        if (towerToPlace != null){
            this.game.setTower(towerToPlace);
        }
    }

    @Override
    public String toString(){
        return "Buy a tower";
    }
}
