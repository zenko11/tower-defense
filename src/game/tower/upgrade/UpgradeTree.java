package game.tower.upgrade;

import java.util.ArrayList;
import java.util.List;

/**A class to manage tower upgrade */
public class UpgradeTree {
    /**active upgrades that are applied, can be sold */
    protected List<Upgrade> activeUpgrades ;
    /**inactive upgrades, can be bought */
    protected List<Upgrade> inactiveUpgrades ;

    /**
     * Builds an UpgradeTree with a list of upgrades.
     * Create a new empty list that will contain active upgrades.
     * Copy the list in parameter to create a new list of inactives upgrades.
     * @param inactiveUpgrades the list copied.
     */
    public UpgradeTree(List<Upgrade> inactiveUpgrades){
        this.activeUpgrades = new ArrayList<>();
        this.inactiveUpgrades = new ArrayList<>();
        for(Upgrade upgrade : inactiveUpgrades){
            this.inactiveUpgrades.add(upgrade);
        }
    }

    /**
     * Switch an upgrade from the inactiveUpgrades list to the active one
     * No effect if upgrade is not in the inactive list.
     * @param upgrade the upgrade to buy
     */
    public void buy(Upgrade upgrade){
        if (this.inactiveUpgrades.contains(upgrade)){
            this.inactiveUpgrades.remove(upgrade);
            this.activeUpgrades.add(upgrade);
        }
    }

    /**
     * Switch an upgrade from the activeUpgrades list to the inactive one
     * No effect if upgrade is not in the active list.
     * @param upgrade the upgrade to sell
     * @return true if you can sell the upgrade, else false
     */
    public boolean sell(Upgrade upgrade){
        if (this.activeUpgrades.contains(upgrade)){
            this.activeUpgrades.remove(upgrade);
            this.inactiveUpgrades.add(upgrade);
            return true;
        }
        return false;
    }

    /**
     * Get the list of active upgrades
     * @return the list of active upgrades
     */
    public List<Upgrade> getActiveUpgrades(){
        return this.activeUpgrades;
    }

    /**
     * Get the list of inactive upgrades
     * @return the list of inactive upgrades
     */
    public List<Upgrade> getInactiveUpgrades(){
        return this.inactiveUpgrades;
    }

    /**
     * Get the whole range multiplier (sum of the different buffs)
     * @return the multiplier
     */
    public double getRangeMultiplier(){
        double multiplier = 1.00;
        for (Upgrade upgrade : this.activeUpgrades){
            multiplier += upgrade.getRangeMultiplier();
        }
        return multiplier;
    }

    /**
     * Get the whole damage multiplier (sum of the different buffs)
     * @return the multiplier
     */
    public double getDamageMultiplier(){
        double multiplier = 1.00;
        for (Upgrade upgrade : this.activeUpgrades){
            multiplier += upgrade.getDamageMultiplier();
        }
        return multiplier;
    }

    /**
     * Get the whole area of effect multiplier (sum of the different buffs)
     * @return the multiplier
     */
    public double getAreaMultiplier(){
        double multiplier = 1.00;
        for (Upgrade upgrade : this.activeUpgrades){
            multiplier += upgrade.getAreaMultiplier();
        }
        return multiplier;
    }

    /**
     * Get the whole cooldown multiplier (sum of the different buffs)
     * @return the multiplier
     */
    public double getCooldownMultiplier(){
        double multiplier = 1.00;
        for (Upgrade upgrade : this.activeUpgrades){
            multiplier -= upgrade.getCooldownMultiplier();
        }
        return multiplier;
    }

    public UpgradeTree copy(){
        List<Upgrade> inactiveUpgrades = new ArrayList<Upgrade>();
        for(Upgrade upgrade : this.inactiveUpgrades){
            inactiveUpgrades.add(upgrade);
        }
        return new UpgradeTree(inactiveUpgrades);
    }
}
