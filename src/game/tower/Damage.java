package game.tower;
import game.balloon.*;


/**A class that represent the effect "damage" in the game*/
public class Damage extends Effect{
    /** this effect's damage */
    protected int damage;

    /**
     * Builds an damage effect with a name,duration, if it's stackable and damage
     * @param name this effect's name
     * @param duration this effect's duration
     * @param stackable this effect is stackable ?
     * @param damage this effect's damage
     */
    public Damage(String name, int duration, boolean stackable,int damage){
        super(name,duration,stackable);
        this.damage = damage;
    }

    /**return the damage of the effect 
    @return the damage 
    */
    public int getDamage(){
        return this.damage;
    }

    /** set the damage of the effect whit the value in parameter
     * @param damageToSet new damage for this effect
     */
    public void setDamage(int damageToSet){
        this.damage = damageToSet;
    }


    /**method that applies the effect Damage to the balloon list and removes the effect when its durability reaches 0 
     * @param clock the current clock of the game
    */
   @Override
    public void applyEffect(int clock){
        if (this.duration >= 0){
            
            for(Balloon b: this.balloons){

                if(b.isDead()){
                    continue; 
                }
                b.decreaseResistance(this.damage);
                System.out.println("Balloon "+ b+ " received "+ this.damage+ " damages");
                if(b.isDead()){
                    b.destroy();
                    System.out.println("Balloon "+ b+ " has been destroyed");
                    
                }

            }
        }
        else{
            this.destroy();
        }
    }
    
    

    /**Copy a Damage effect in order to have new bullets */
    @Override
    public Effect copy(){
        return new Damage(this.name,this.duration,this.stackable, this.damage);
    }
}