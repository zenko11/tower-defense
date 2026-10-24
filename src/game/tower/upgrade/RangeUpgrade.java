package game.tower.upgrade;

/**
 * A class managing Range upgrades
 */
public class RangeUpgrade extends Upgrade{
    /**the value to multiply a tower range */
    protected double multiplier;
    
    /**
     * Builds a RangeUpgrade class with a multiplier
     * @param multiplier the multiplier of this upgrade
     * @param cost the cost of this upgrade
     */
    public RangeUpgrade(int cost,double multiplier){
        super(cost);
        this.multiplier = multiplier;
    }

    /**
     * Get the range multiplier.
     * @return the range multiplier.
     */
    @Override
    public double getRangeMultiplier(){
        return this.multiplier;
    }

    /**
     * Display the upgrade with its name and percentage buff
     * @return a string
     */
    @Override
    public String toString(){
        return "Upgrade : range, buff : + " + this.multiplier*10 + "%"; 
    }
}
