package game.board;

import game.exception.*;
import game.path.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**A bloon td game board with multiple straight paths */
public class MultiplePathBoard extends Board {
    /**The number of path on the board */
    protected int nbOfPaths ;
    
    /**
     * Build a multiple path board with a given width, height and number of paths
     * @param width the width of the board
     * @param height the height of the board
     * @param nbOfPaths the number of path
     * @throws TooMuchPathsException when paths are exceeding the limit (greater than height/2 or width/2)
     * @throws NoNextCellException when trying to get a null cell
     */
    public MultiplePathBoard(int width, int height, int nbOfPaths) throws TooMuchPathsException,NoNextCellException{
        super(width,height);
        this.nbOfPaths = nbOfPaths;
        if (nbOfPaths > height/2 || nbOfPaths > width/2){
            throw new TooMuchPathsException("too much path, you need to have less than height/2 and width/2");
        }

        this.initPath();
    }



    /**
     * Init the board with empty cells and create the paths*
     * @throws TooMuchPathsException when paths are exceeding the limit from the constructor
     * @throws NoNextCellException when trying to get a null cell
     */
    protected void initPath() throws NoNextCellException,TooMuchPathsException{
        fillPath();

        List<Integer> occupiedColumns = new ArrayList<>();
        List<Integer> occupiedRows = new ArrayList<>();


        for (int n = 0; n<this.nbOfPaths; n++){
            int startX = 0 ;
            int startY = 0 ;
            int dirX = 0 ;
            int dirY = 0;



            /*4 differents path types, we get one type of each before getting another for the same type */
            /*left to right */
            if (n%4 == 0){
                startX = 0;
                startY = startingIndex(this.height, occupiedRows);
                dirX = 1;
                dirY = 0;
            }
            /**top to bottom */
            if (n%4 == 1){
                startX = startingIndex(this.width, occupiedColumns);
                startY = 0;
                dirX = 0;
                dirY = 1;
            }
            /**right to left */
            if (n%4 == 2){
                startX = this.width-1;
                startY = startingIndex(this.height, occupiedRows);
                dirX = -1;
                dirY = 0;
            }
            /**bottom to top */
            if (n%4 == 3){
                startX = startingIndex(this.width, occupiedColumns);
                startY = this.height-1;
                dirX = 0;
                dirY = -1;
            }

            Path path = createPath(startX, startY, dirX, dirY);
            this.addPath(path);
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

    /**
     * Give a starting index for a path
     * @param max maximum index for the path to be in the grid
     * @param occupied list of index where a path already starts
     * @return the index where the path will start
     */
    protected int startingIndex(int max, List<Integer> occupied){
        Random rand = new Random();
        int res = rand.nextInt(max);

        while(occupied.contains(res)){
            res = rand.nextInt(max);
        }

        occupied.add(res);
        return res;
    }

    /**
     * true if this coordinate is in the board
     * @param x x coordinate
     * @param y y coordinate
     * @return true if in, false if not
     */
    protected boolean isIn(int x, int y){
        return x>=0 && x < this.width && y >=0 && y< this.height;
    }

    /**
     * Create a straight path from coordinate (x,y) and direction 
     * either dirX = 1 meaning moving from left to right, dirX = -1 meaning right to left, dirY = 1 top to bottom and dirY = -1 bottom to top
     * if dirX is not 0 then dirY is 0 and if dirY is not 0 then dirX is 0
     * @param x x coordinate of the starting cell
     * @param y y coordinate of the starting cell
     * @param dirX give the direction to follow
     * @param dirY give the direction to follow
     * @return the path created
     * @throws TooMuchPathsException when paths are exceeding the limit from the constructor
     * @throws NoNextCellException when trying to get a null cell
     */
    protected Path createPath(int x, int y, int dirX, int dirY) throws NoNextCellException,TooMuchPathsException{
        Cell firstCell = this.tab[x][y];
        CellPath firstCellPath = new CellPath(firstCell);

        int currentX = x;
        int currentY = y;

        currentX += dirX;
        currentY += dirY;

        Cell cell = this.tab[currentX][currentY];
        CellPath currentCellPath = new CellPath(cell);
        firstCellPath.setNext(currentCellPath);

        currentX += dirX;
        currentY += dirY;

        while (isIn(currentX,currentY)){
            Cell nextCell = this.tab[currentX][currentY];
            CellPath nextCellPath = new CellPath(nextCell);

            currentCellPath.setNext(nextCellPath);

            currentCellPath = nextCellPath;

            currentX += dirX;
            currentY += dirY;
        }
        return new Path(firstCellPath);
    }




}

