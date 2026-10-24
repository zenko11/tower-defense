package game.tower.upgrade;

/**
 * A class managing cooldown upgrades
 */
public class CooldownUpgrade extends Upgrade {
    /**the value to multiply a tower cooldown */
    protected double multiplier;
    
    /**
     * Builds a CooldownUpgrade class with a multiplier
     * @param cost the cost of this upgrade
     * @param multiplier the multiplier of this upgrade
     */
    public CooldownUpgrade(int cost,double multiplier){
        super(cost);
        this.multiplier = multiplier;
    }

    /**
     * Get the cooldown multiplier.
     * @return the cooldown multiplier.
     */
    @Override
    public double getCooldownMultiplier(){
        return this.multiplier;
    }

    /**
     * Display the upgrade with its name and percentage buff
     * @return a string
     */
    @Override
    public String toString(){
        return "Upgrade : cooldown, buff : - " + this.multiplier*10 + "%"; 
    }
}
