package game.tower;
import game.GameObject;
import game.balloon.*;
import java.util.*;

/**An abstract class that represent an effect in the game*/
public abstract class Effect extends GameObject {
    /** this effect's duration */
    protected int duration;
    /** this effect's name */
    protected String name;
    /** this effect is stackable ? */
    protected boolean stackable;
    /** the balloons to apply this effect */
    protected List<Balloon> balloons;
    /** is this effect first tick ? */
    protected boolean isFirstTick;

    /**
     * Builds an effect with a name,duration and if it's stackable
     * @param name this effect's name
     * @param duration this effect's duration
     * @param stackable this effect is stackable ?
     */
    public Effect(String name, int duration, boolean stackable){
        super();
        this.name = name;
        this.duration = duration;
        this.stackable = stackable;
        this.balloons = new LinkedList<Balloon>();
        this.isFirstTick = true;
    }

    /**
     * Return the name of this effect
     * @return the name of this effect
    */
    public String getName(){
        return this.name;
    }

    /**
     * Return the duration of this effect
     * @return the duration of this effect
    */
    public int getDuration(){
        return this.duration;
    }

    /**
     * Return true if this effect is stackable
     * @return true if this effect is stackable
    */
    public boolean isStackable(){
        return this.stackable;
    }

    /**
     * Return the balloons to apply this effect
     * @return the balloons to apply this effect
    */
    public List<Balloon> getTargets(){
        return this.balloons;
    }

    /**
     * add a balloon which is affected by this effect
     * @param balloon the balloon which is affected by this effect
    */
    public void addTarget(Balloon balloon){
        this.balloons.add(balloon);
    }

    /**
     * apply this effect to all balloon affected by this effect
     * @param clock the game clock
    */
    public abstract void applyEffect(int clock);

    /**
     * apply this effect to all balloon affected by this effect an decrease this effect duration
     * @param clock the game clock
     * @return always true
    */
    public boolean onClock(int clock){
        this.applyEffect(clock);
        this.duration = this.duration -1;
        return true;
    }

    /**
     * Return a copy of this effect
     * @return a copy of this effect
    */
    public abstract Effect copy();
}