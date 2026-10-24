package game.board;

import game.exception.*;
import game.path.*;
import java.util.ArrayList;
import java.util.List;


/**An abstract board representing a bloons td game board */
public abstract class Board {
    /**Board width */
    protected int width ;
    /**Board height */
    protected int height ;
    /**Represents the cells of the board */
    protected Cell[][] tab ;
    /**The board paths list */
    protected List<Path> pathList ;

    /**Builds a Board with a given width and height
     * @param width board width
     * @param height board height
     */
    public Board(int width, int height){
        this.width = width ;
        this.height =  height ;
        this.tab = new Cell[width][height];
        this.pathList = new ArrayList<>();
    }

    /**Get a specific cell from a x and a y value 
     * @param x x value
     * @param y y value
     * @return the cell to this coordinates
    */
    public Cell getCell(int x, int y){
        return this.tab[x][y] ;
    }
    
    /**Get the board width 
     * @return the width
    */
    public int getWidth(){
        return this.width;
    }

    /**Get the board height 
     * @return the width
    */
    public int getHeight(){
        return this.height ;
    }

    /**Get the list of the paths 
     * @return the list
    */
    public List<Path> getPaths(){
        return this.pathList ;
    }

    /**Add a path to the paths list
     * @param path the path to add
     * @throws TooMuchPathsException when paths are exceeding the limit
     */
    public void addPath(Path path) throws TooMuchPathsException{
        this.pathList.add(path);
    } 

    /**
     * Initialize the paths
     * @throws NoNextCellException when trying to reach a null cell
     * @throws TooMuchPathsException when paths exceed the limits determined by the constructor
     */
    protected abstract void initPath() throws NoNextCellException,TooMuchPathsException;

    /**
     * Show the board in the console
     * @throws NoNextCellException when trying to reach a null cell
     */
    public void show() throws NoNextCellException{
        boolean[][] estChemin = new boolean[this.width][this.height];
        for(Path path : this.pathList){
            CellPath cellPath = path.getFirstCell();
            while (!cellPath.isFinished()){
                Cell cell = cellPath.getCell();
                int x = cell.getX();
                int y = cell.getY();
                estChemin[x][y] = true;
                cellPath = cellPath.getNextCell();
            }

            Cell lastCell = cellPath.getCell();
            estChemin[lastCell.getX()][lastCell.getY()] = true ;
        }
        for(int i = 0 ; i < this.height ; i++){
            String ligne = "";
            for (int j = 0 ; j < this.width ; j++){
                if(estChemin[j][i]){
                    ligne = ligne + " #";
                } else {
                    ligne = ligne + " .";
                }
            }
            System.out.println(ligne + "\n");
        }
    }
}
