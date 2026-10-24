package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import java.util.*;
import game.balloon.*;
import game.tower.*;
import game.player.*;

/**A game of a bloons td game */
public class Game {
    /**Game's Board */
    protected Board board;
    /**Game's clock */
    protected int clock;
    /**Game's gameObjects */
    protected Set<GameObject> gameObjects;
    /**Game's balloons */
    protected Set<Balloon> balloons;
    /**Game's towers */
    protected Set<Tower> towers;
    /**number of balloons */
    protected int nbBalloons;
    /**Game's player */
    protected Player player;

    /**
     * Builds a Game with a given board and balloons number
     * @param board Game's board
     * @param NbBalloons Game's balloons number
     * @param player game's player
     */
    public Game(Board board,int NbBalloons,Player player){
        this.clock = 0;
        this.gameObjects = new HashSet<GameObject>();
        this.balloons = new HashSet<Balloon>();
        this.towers = new HashSet<>();
        this.nbBalloons =NbBalloons;
        this.board = board;
        this.player = player;
    }
    /**add a fixed number of balloons according to the game
     * with a random speed and resistance
     *
    */
    public void init_balloons(){
        List<Path> paths = board.getPaths();
        int pathIndex = 0;
        int pathsNumber = paths.size();

        Random rng = new Random();
        for(int i = 0 ; i < this.nbBalloons ; i++){
            
            CellPath cellStart = paths.get(pathIndex).getFirstCell();
            int clockSpeed = rng.nextInt(40) + 10; // 10 ~ 50
            int resistance = rng.nextInt(4) + 1; // 1 ~ 4
            Balloon balloon = new Balloon(clockSpeed,resistance,cellStart);
            this.gameObjects.add(balloon);
            this.balloons.add(balloon);
            if(pathIndex + 1 >= pathsNumber){
                pathIndex = 0;
            } else {
                pathIndex++;
            }
        }
    }
    /**
     * Return true if the game is finished
     * @return true if the game is finished
    */
    public boolean isFinished(){
        return (player.getLives() <= 0)||(balloons.isEmpty());
    }
    /**tells us if the game lost 
     * @return true if the player's lives are lesser or equal than 0
     */
    public boolean gameLost(){
         return player.getLives() <= 0;
    }

    /**
     * Do a turn of the game
     * @throws NoNextCellException .
    */
    public void loop() throws NoNextCellException{
        List<GameObject> gameObjectToDestroy = new LinkedList<GameObject>();
        List<GameObject> gameObjectsToAdd = new LinkedList<>();

        // Clock des gameobjects
        for (GameObject gameObject : this.gameObjects) {
            boolean eventTrigerred = gameObject.onClock(this.clock);

            if (gameObject instanceof Bullet && eventTrigerred){
                Bullet b = (Bullet) gameObject;
                for (Effect e : b.getEffects()){
                    gameObjectsToAdd.add(e);
                }
            }

            if(gameObject.toDestroy()){
                gameObjectToDestroy.add(gameObject);
            }
        }

        // Clock des ballons
       for (Balloon balloon : this.balloons){
            
            if(balloon.toDestroy()){
                if (balloon.getResistance() > 0) {
                    player.decreaseLife();
                    System.out.println("Your lives have decreased, you have now : " + player.getLives());
                } else {
                    player.addMoney(10);
                }
                gameObjectToDestroy.add(balloon);
            }
        }
       for(Tower t: this.towers){
            if(t.onClock(this.clock)){
                Bullet bullet = t.shoot(this.balloons);
                if (bullet != null){
                    gameObjectsToAdd.add(bullet);
                }
            }
       }

       for (GameObject newObj : gameObjectsToAdd){
            this.addGameObject(newObj);
       }


        // Destruction des objets à détruire
        for(GameObject gameObject : gameObjectToDestroy){
            this.DeleteGameObject(gameObject);
        }

        this.clock++;
    }

    public void UpdateDestroyedTowers(){
        List<GameObject> gameObjectToDestroy = new LinkedList<GameObject>();

        for(GameObject tower : this.towers){
            if(tower.toDestroy()){
                gameObjectToDestroy.add(tower);
            }
        }

        for(GameObject gameObject : gameObjectToDestroy){
            this.DeleteGameObject(gameObject);
        }
    }

    /**
     * Do a wave of the game
     * @throws NoNextCellException .
    */
    public void playWave() throws NoNextCellException{
        while (!this.isFinished()){
            this.loop();
        }
        this.nbBalloons += 5;
    }

    /**
     * Return a set of the balloons in this game
     * @return a set of the balloons in this game
    */
    public Set<Balloon> getBalloons(){
        return this.balloons;
    }

    /**
     * Add a tower in the game
     * @param t the tower to add
    */
    public void setTower(Tower t){
        this.towers.add(t);
    }

    /**
     * get the set of towers in the game
     * @return the towers that are in the game
     * 
    */
    public Set<Tower> getTowers(){
        return this.towers;
    }

    /**
     * Add a balloon in the game
     * @param o the balloon to add
    */
    public void addBalloons(Balloon o){
        this.balloons.add(o);
    }

    /**
     * Add a GameObject in the game
     * @param o the GameObject to add
    */
    public void addGameObject(GameObject o){
            this.gameObjects.add(o);
    }

    /**
     * Delete a gameobject in the game
     * @param gameObject the GameObject to delete
    */
    public void DeleteGameObject(GameObject gameObject){
        if(gameObject instanceof Balloon){
            this.balloons.remove((Balloon) gameObject);

        } else if(gameObject instanceof Tower){
            this.towers.remove((Tower) gameObject);
        }
        if(this.gameObjects.contains(gameObject)){
            this.gameObjects.remove(gameObject);
        }
    }

    /**
     * Apply an effect to al balloon on the cells
     * @param cells the cells targeted
     * @param effect the effect to apply
    */
    public void applyEffectToBalloonOnCells(List<Cell> cells,Effect effect){
        for(Balloon balloon : this.balloons){
                CellPath cellPath = balloon.getCell();
                Cell cellBalloon = cellPath.getCell();
                for(Cell cellToApply : cells){
                    if(cellBalloon.equals(cellToApply)){
                        effect.addTarget(balloon);
                        effect.onClock(this.clock);
                    }
                }
            }
        this.addGameObject(effect);
    }
}

