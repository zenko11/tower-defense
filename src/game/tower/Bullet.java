package game.tower;

import game.GameObject;
import game.balloon.*;
import java.util.*;

/** Represent a Tower bullet that can hit or apply effects to a balloon or a group of balloon */
public class Bullet extends GameObject{

    /**
     * area impact of this bullet
     */
    protected int area;
    /**
     * time left before the bullet reach the target
     */
    protected int timeLeft;
    /**
     * list of effects that this bullet has
     */
    protected List<Effect> effects;

    /**the initial target of this bullet*/
    protected Balloon target;

    /**the set of balloons that are currently in the game */
    protected Set<Balloon> balloons;


    /**
     * builds a bullet with a damage area, a timeLeft and a list of effects
     *
     * @param area int
     * @param timeLeft int
     * @param effects List of effects
     */
    public Bullet(int area, int timeLeft, List<Effect> effects) {
        this.area = area;
        this.timeLeft = timeLeft;
        this.effects = effects;
        this.target = null;
    }
    /**get the balloons of this bullet
     * @return the set of balloons
     */
    public Set<Balloon> getBalloons(){
        return this.balloons;
    }
    /** get the area damage of this bullet
     * @return int
     */
    public int getArea() {
        return this.area;
    }

    /** set the area of this bullet
     * @param area the area of this bullet
     */
    public void setArea(int area){
        this.area = area;
    }

    /**get the target of this bullet
     * @return null if not defined, else a balloon
     */
    public Balloon getTarget(){
        return this.target;
    }

    /** get the time left of this bullet
     * @return int
     */
    public int getTimeLeft() {
        return this.timeLeft;
    }
    /** set a list of balloons that are currently in the game
     * @param b a set of balloons
    */
    public void setBalloons(Set<Balloon> b){
        this.balloons = b;
    }
    /**return all the balloons in the area of the bullet when it will hit the initial target
     * @return a set of all the balloons that are targeted.
     */
    protected Set<Balloon> finalTargets(){
        Set<Balloon> balloonsTargeted = new HashSet<>();
        balloonsTargeted.add(this.target);
        int maxX = this.target.getCell().getCell().getX()+this.area;
        int maxY = this.target.getCell().getCell().getY()+this.area;
        int minX = this.target.getCell().getCell().getX()-this.area;
        int minY = this.target.getCell().getCell().getY()-this.area;
        if(this.area>-1){
        for(Balloon b:this.balloons){
            int balloonX = b.getCell().getCell().getX();
            int balloonY = b.getCell().getCell().getY();
            if(balloonX>=minX&&balloonX<=maxX&&balloonY>=minY&&balloonY<=maxY){
                balloonsTargeted.add(b);
            }
        }
        }
        return balloonsTargeted;
    }

    /** get the list of effects of this bullet
     * @return the list of effects
     */
    public List<Effect> getEffects() {
        return this.effects;
    }

    /**Updates the bullet's state every game tick
     * if the bullet has time left before hitting a target, the timeleft decrease,
     * when there is no time left it applies all the effects balloons in the area of this bullet.
     * @param clock the current clock of the game
     * @return false if bullet didn't hit the targets
    */
    @Override
    public boolean onClock(int clock){
        if ((this.timeLeft>0)){
            this.timeLeft--;
            if (this.timeLeft==0){
                Set<Balloon> balloonsTargeted = this.finalTargets();
                for(Effect effect : this.effects){
                    for(Balloon b : balloonsTargeted){
                        effect.addTarget(b);
                    }
                }
                this.destroy();
                return true;
            } 
        }
        return false;
        }
        

    /**add a target to the bullet
     * @param b a balloon
     */
    public void addTarget(Balloon b){
        this.target = b;
    }

}
