package game;

/**A gameObject in the game */
public abstract class GameObject {
    /**gameObject counter between al gameobjects */
    private static int gameObjectCounter;
    /**gameObject's id*/
    private int id;
    /**gameObject's is destroyed ?*/
    private boolean destroyed;
    
    /**
     * Return this gameobjects's id
     * @return this gameobjects's id
    */
    public int getId(){
        return this.id;
    }
    
    /**
     * destroy this gameObject
    */
    public void destroy(){
        this.destroyed = true;
    }

    /**
     * Return true if this gameobject is destroyed
     * @return true if this gameobject is destroyed
    */
    public boolean toDestroy(){
        return this.destroyed;
    }

    /**
     * Return this gameobject's hashcode
     * @return tthis gameobject's hashcode
    */
    public int hashCode() {
        return this.id;
    }

    /**
     * Constructor for GameObject
    */
    public GameObject(){
        this.id = gameObjectCounter;
        gameObjectCounter++;
    }

    /**
     * get called at every clock of the game
     * @param clock the current clock of the game
     * @return true if the gameobject is ready, else false
    */
    public abstract boolean onClock(int clock);
}