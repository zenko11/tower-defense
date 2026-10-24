package game.player;
import java.util.*;
import game.tower.*;
import game.tower.upgrade.Upgrade;

/**
 * A class for the shop of the game
 */
public class Shop {
    /**the towers that can be bought */
    protected List<Tower> towers;
    
    /**
     * Builds a Shop class that sell towers
     * @param towers towers that can be bought
     */
    public Shop(List<Tower> towers){
        this.towers = towers;
    }

    /**
     * get the towers for sale
     * @return the towers for sale
     */
    public List<Tower> getTowers(){
        return this.towers;
    }

    /**
     * Get the upgrades for a tower
     * @param tower the tower 
     * @return the upgrades for that tower for sale
     */
    public List<Upgrade> getUpgrades(Tower tower){
        return tower.getUpgradeTree().getInactiveUpgrades();
    }

    /**
     * Get the upgrades bought for a tower
     * @param tower the tower 
     * @return the upgrades bought for that tower
     */
    public List<Upgrade> getActiveUpgrades(Tower tower){
        return tower.getUpgradeTree().getActiveUpgrades();
    }
    
}

