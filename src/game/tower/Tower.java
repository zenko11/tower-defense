package game.tower;
import game.tower.upgrade.*;
import game.GameObject;
import game.balloon.Balloon;
import game.path.*;
import java.util.*;
/**represents a tower in the game that can shoot */
public class Tower extends GameObject{
    /**
     * range of this tower, if a balloon is inside this range, then the tower can
     * hit it
     */
    protected int range;
    /** cost of this tower */
    protected int cost;
    /** bullet of this tower */
    protected Bullet bullet;
    /**Cell of this tower */
    protected Cell cell;
    /**paths that are in the board*/
    protected List<Path> paths;
    /**List of cell in range of this tower */
    protected List<Cell> cellInRange;
    /**time before the tower can shoot an other time*/
    protected int reloadTime;
    /**the list of possible upgrades on this tower*/
    protected UpgradeTree upgradeTree;
    /**the name of this tower */
    protected String name;



    /** builds a tower with a range, a cost, a bullet and a cell
     * @param range int
     * @param cost int
     * @param bullet Bullet
     * @param cell Cell
     * @param paths List of paths
     * @param reloadTime the reloading time of this tower
     *@param upgradeTree the upgrade list of this tower
     *@param name the name of this tower
     */
    public Tower(int range, int cost, Bullet bullet,int reloadTime,Cell cell,List<Path> paths,UpgradeTree upgradeTree,String name) {
        this.range = range;
        this.cost = cost;
        this.bullet = bullet;
        this.reloadTime = reloadTime;
        this.cell = cell;
        this.paths = paths;
        this.upgradeTree = upgradeTree;
        
        this.name = name;
        

    }

    /**get the cells in the range of this tower
     * @return a List of cell
     */
    public List<Cell> getCellInRange(){
        List<Cell> cellInRange = new ArrayList<Cell>();
        int maxX = (int) Math.ceil(this.cell.getX()+this.range*this.upgradeTree.getRangeMultiplier());
        int maxY = (int) Math.ceil(this.cell.getY()+this.range*this.upgradeTree.getRangeMultiplier());
        int minX = (int) Math.ceil(this.cell.getX()-this.range*this.upgradeTree.getRangeMultiplier());
        int minY = (int) Math.ceil(this.cell.getY()-this.range*this.upgradeTree.getRangeMultiplier());
    

        int i = 0;
        for(Path p : this.paths){
            List<CellPath> cellpaths= p.getPath();
            for(CellPath cp : cellpaths){
                Cell c = cp.getCell();
                if( c.getX()<=maxX && c.getX()>=minX && c.getY()>=minY && c.getY()<=maxY){
                    cellInRange.add(c);
                }
            }
        }
        return cellInRange;
    }
    /**get the reload time of this tower
     * @return int
     */
    public int getReloadTime(){
        return ((int) Math.ceil(this.reloadTime*this.upgradeTree.getCooldownMultiplier()));
    }
    /**get the range of this tower
     * @return int
     */
    public int getRange() {
        return ((int) Math.ceil(this.range*this.upgradeTree.getRangeMultiplier()));
    }
    /**get the cost of this tower
     * @return int
     */
    public int getCost() {
        return this.cost;
    }
    /**get the bullet of this tower
     * @return Bullet
     */
    public Bullet getBullet() {
        return this.bullet;
    }
    /**
     * get the cell of this tower
     * @return Cell
     */
    public Cell getCell(){
        return this.cell;
    }
    
    /** set a new range for this tower
     * @param range int
     * 
    */
    public void setRange(int range) {
        this.range = range;
    }
    
    /**set a new reloadTime for this tower
     * @param reloadTime int
     */
    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }
    /**set a new position to this tower
     * warning : can be placed outside the game and on the path...
     * @param x the x position
     * @param y the y position
     */
    public void setCell(int x,int y){
        this.cell = new Cell(x,y);

    }
    /**buy an upgrade according to the upgrade in parameter
     * if this tower don't own this upgrade then it will do nothing
     * @param up Upgrade 
    */
    public void buyUp(Upgrade up){
        this.upgradeTree.buy(up);
    }


    /**sell an upgrade according to the upgrade in parameter
     * if this tower don't own this upgrade then it will do nothing
     * @param up Upgrade 
    */
    public void sellUp(Upgrade up){
        this.upgradeTree.sell(up);
    }
    

    /**get the Upgrade Tree of this tower
    * @return UpgradeTree
    */
    public UpgradeTree getUpgradeTree(){
        return this.upgradeTree;
    }

    /**return the distance of a balloon and a cell pathpath
     *distance : sqrt((LastCellPathX-BalloonX)²+(LastCellPathY-BalloonY²). 
     * @param b1 a balloon
     * @param lastCellPath the last cell of the current path.
     * @return double
     */
    protected double distance(Balloon b1,CellPath lastCellPath){
        double b1ToLastCellX = lastCellPath.getCell().getX()-b1.getCell().getCell().getX();
        double b1ToLastCellY = lastCellPath.getCell().getY()-b1.getCell().getCell().getY();
        return Math.sqrt(((b1ToLastCellX*b1ToLastCellX)+(b1ToLastCellY*b1ToLastCellY)));
    }
    /**check if a balloon is in range, if it is then it will 
     * return the balloon that is the closest to end the path,
     * if it is not in range, then it will return null
     * @param balloons a set of balloons that are currently in the game
     * @return balloon targeted
     */
    protected Balloon isInRange(Set<Balloon> balloons){
        Balloon balloonTargeted = null;
        double distanceMin = Double.MAX_VALUE;
        List<Cell> cellInRange= this.getCellInRange();
        for(Balloon b : balloons){
            Cell bCell = b.getCell().getCell();
            if(cellInRange.contains(bCell)){

                for(Path p : this.paths){
                    CellPath lasCellPath = p.getLastCell();
                    double currentDistance = this.distance(b, lasCellPath);
                    if (currentDistance<distanceMin){
                        distanceMin = currentDistance;
                        balloonTargeted = b;
                    }
                }
            }
        }
        return balloonTargeted;
    }
    /**check if two tower has the sameCell
     * @param object an object
     * @return true if they have the same cell, else false
     */
    public boolean SameTowerCell(Object object){
        if (!(object instanceof Tower)){
            return false;
        }
        Tower o = (Tower) object;
        return this.cell.equals(o.getCell());
    }
    /**attemps to fire a projectile at a target
     * if a target is found, it creates a new bullet with the properties of 
     * the current tower's bullet
     * @param balloons a set of balloons that are currently in the game
     * @return a new List of Bullet if a target is found,
     * else null.
     * 
     * 
     */
    public Bullet shoot(Set<Balloon>balloons){
        Balloon target= this.isInRange(balloons);
        Bullet projectile = null;
        if(target!=null){
            List<Effect> newEffects = new LinkedList<>();

            for (Effect effect : this.bullet.getEffects()){
                Effect modifiedEffect =  effect.copy();
                if (modifiedEffect instanceof Damage){
                    Damage damage = (Damage) modifiedEffect;
                    int modifiedDamage = (int) Math.ceil(damage.getDamage()*this.upgradeTree.getDamageMultiplier());
                    damage.setDamage(modifiedDamage);
                }
                
                newEffects.add(modifiedEffect);
            }
            int modifiedArea = (int) Math.ceil(this.bullet.getArea()*this.upgradeTree.getAreaMultiplier());
            projectile = new Bullet(modifiedArea,
            this.bullet.getTimeLeft(),newEffects);
            projectile.addTarget(target);
            projectile.setBalloons(balloons);
        }
        return projectile;
    }


    /**updates the tower's state every game tick
     * it is checking if the tower is ready to shoot or not
     * @param clock the current clock of the game
     * @return true if this tower is ready else false
    */
    @Override
    public boolean onClock(int clock) {
        int time = (int) Math.ceil((this.reloadTime*this.upgradeTree.getCooldownMultiplier()));
        return clock%(time)==0;
    }
    
    @Override
    public String toString(){
        return "Tower: " +this.getName()+ ", range : " + this.getRange() + ", reload time :"  + this.getReloadTime() + ", cost : " + this.cost + ", id : " + this.getId();
    }




    /**get the name of this tower
     * @return the name of this tower
     */
    public String getName(){
        return this.name;
    }
    /**copy this tower and returns it
     * @return a new tower with the same attributes as this tower
     */
    public Tower copy(){
        return new Tower(this.range, this.cost, this.bullet, this.reloadTime, this.cell, this.paths, this.upgradeTree.copy(), this.name);
    }
    

}
