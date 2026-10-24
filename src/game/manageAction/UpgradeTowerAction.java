package game.manageAction;

import game.player.Player;
import game.tower.Tower;
import game.tower.upgrade.Upgrade;

/**A class to upgrade a tower */
public class UpgradeTowerAction implements ManageAction{
    /**the player */
    protected Player player ;
    /**the tower to upgrade */
    protected Tower tower;
    /**the upgrade to buy */
    protected Upgrade upgrade;

    /**
     * An action to upgrade a tower with all the informations needed
        * @param player the player of the game
     * @param tower the tower that the player want to upgrade
     * @param upgrade the upgrade that the player want to buy
     */
    public UpgradeTowerAction(Player player, Tower tower, Upgrade upgrade){
        this.player = player;
        this.tower = tower;
        this.upgrade = upgrade;
    }

    /**
     * Perform the action to upgrade the tower
     */
    @Override
    public void perform(){
        this.player.buyUpgrade(tower, upgrade);
    }
    @Override
    public String toString(){
        return "Buy an upgrade";
    }
}


