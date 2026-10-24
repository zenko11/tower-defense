package game.board;

import game.exception.*;
import game.path.*;
import java.util.Random ;

/**
 * A class representing a classical board with a single path starting from left.
 * This path can go up,down,right but not left until end column is reached.
 */
public class ClassicalBoard extends Board {

    /** create a classcial board with the length
     * @param width width of this board
     * @param height height of this board
     *@throws TooMuchPathsException when paths are exceeding the limit (here it's 1)
     * @throws NoNextCellException when trying to get a null cell
    */
    public ClassicalBoard(int width,int height) throws TooMuchPathsException,NoNextCellException{
        super(width,height);
        this.initPath();
    }

    /** create a random path in the board
     * @throws TooMuchPathsException when paths are exceeding the limit (here it's 1)
     * @throws NoNextCellException when trying to get a null cell
	 */
    protected void initPath() throws TooMuchPathsException,NoNextCellException{

        fillPath();

        Random rng = new Random();
        int y = rng.nextInt(this.height);
        String lastDirection = "";

        int x = 0;
        Cell firstCell = this.tab[x][y];
        firstCell.setEmpty(false);


        CellPath cellPath = new CellPath(firstCell);
        CellPath current = cellPath;

        while(x < this.width - 1){
            int randomNum = rng.nextInt(2);
            if(randomNum == 1){
                if(!lastDirection.equals("Down") && y < this.height-1){
                    y++;
                    lastDirection = "Up";
                } else {
                    x++;
                    lastDirection = "Right";
                }
            } else {
                if(!lastDirection.equals("Up") && y > 0){
                    y--;
                    lastDirection = "Down";
                } else {
                    x++;
                    lastDirection = "Right";
                }
            }
            Cell newCell = this.tab[x][y];
            newCell.setEmpty(false);

            CellPath next = new CellPath(newCell);
            current.setNext(next);
            current = next;
            
        }
        this.addPath(new Path(cellPath));
    }

    /** add the path to the list of paths
	 * @param path the path to add 
     * @throws TooMuchPathsException if there is already a path
	 */
    public void addPath(Path path) throws TooMuchPathsException {
        if(this.pathList.size() < 1){
            super.addPath(path);
        } else {
            throw new TooMuchPathsException();
        }
    }

    /**
     * fill the board with empty cells
     */
    protected void fillPath(){
        for (int x = 0; x<this.width; x++){
            for (int y = 0; y < this.height; y++){
                this.tab[x][y] = new Cell(x,y);
            }
        }

    }
}