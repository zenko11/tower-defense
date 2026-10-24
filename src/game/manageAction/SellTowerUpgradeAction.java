package game.manageAction;

import game.player.Player;
import game.tower.Tower;
import game.tower.upgrade.Upgrade;

/**A class to sell a tower upgrade */
public class SellTowerUpgradeAction implements ManageAction{
    /**the player */
    protected Player player ;
    /**the tower */
    protected Tower tower;
    /**the upgrade to sell */
    protected Upgrade upgrade;

    /**
     * An action to sell a tower upgrade with all the informations needed
     * @param player the player of the game
     * @param tower the tower that the player want to upgrade
     * @param upgrade the upgrade that the player want to sell
     */
    public SellTowerUpgradeAction(Player player, Tower tower, Upgrade upgrade){
        this.player = player;
        this.tower = tower;
        this.upgrade = upgrade;
    }

    /**
     * Perform the action to sell the tower upgrade
     */
    @Override
    public void perform(){
        this.player.sellUpgrade(tower, upgrade);
    }
    @Override
    public String toString(){
        return "Sell a an Upgrade";
    }
}



