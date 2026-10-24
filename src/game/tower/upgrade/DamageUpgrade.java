package game.tower.upgrade;

/**
 * A class managing damages upgrades
 */
public class DamageUpgrade extends Upgrade{
    /**the value to multiply a bullet damage */
    protected double multiplier;
    
    /**
     * Builds a DamageUpgrade class with a multiplier
     * @param cost the cost of this upgrade
     * @param multiplier the multiplier of this upgrade
     */
    public DamageUpgrade(int cost,double multiplier){
        super(cost);
        this.multiplier = multiplier;
    }

    /**
     * Get the damage multiplier.
     * @return the damage multiplier.
     */
    @Override
    public double getDamageMultiplier(){
        return this.multiplier;
    }

    /**
     * Display the upgrade with its name and percentage buff
     * @return a string
     */
    @Override
    public String toString(){
        return "Upgrade : damage, buff : + " + this.multiplier*10 + "%"; 
    }
}
