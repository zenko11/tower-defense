package game.tower.upgrade;

/**
 * A class managing area of effect upgrades
 */
public class AreaUpgrade extends Upgrade {
    /**the value to multiply a bullet area of effect */
    protected double multiplier;
    
    /**
     * Builds a AreaUpgrade class with a multiplier
     * @param multiplier the multipler
     * @param cost the cost of this upgrade
     */
    public AreaUpgrade(int cost,double multiplier){
        super(cost);
        this.multiplier = multiplier;
    }

    /**
     * Get the area of effect multiplier.
     * @return the area of effect multiplier.
     */
    @Override
    public double getAreaMultiplier(){
        return this.multiplier;
    }

    /**
     * Display the upgrade with its name and percentage buff
     * @return a string
     */
    @Override
    public String toString(){
        return "Upgrade : aoe, buff : + " + this.multiplier*10 + "%"; 
    }
    
}
