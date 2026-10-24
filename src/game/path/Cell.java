package game.path;

/**A cell of a bloons td game */
public class Cell {
    /**x coordinate of the cell in the board */
    private int x;
    /**y coordinate of the cell in the board */
    private int y;
    /**true if this cell is empty (does not contain any path) */
    private boolean isEmpty ;

    /**
     * Builds an empty cell of a bloon td game board with a x and y coordinate.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.isEmpty = true;
    }

    /**
     * Get the x coordinate of this cell
     * @return the x coordinate
     */
    public int getX(){
        return this.x;
    }

    /**
     * Get the y coordinate of this cell
     * @return the y coordinate
     */
    public int getY(){
        return this.y;
    }

    /**
     * true if this cell is empty
     * @return boolean according to the cell emptiness
     */
    public boolean isEmpty(){
        return this.isEmpty ;
    }

    /**
     * Set the isEmpty value
     * @param value true to turn the cell empty (isEmpty value to true), false to set this value to false (isEmpty value to false)
     */
    public void setEmpty(boolean value){
        this.isEmpty = value;
    }

    public boolean equals(Object o){
        if(!(o instanceof Cell)){
            return false;
        }
        Cell other = (Cell)o;
        return other.x == this.x && other.y == this.y;
    }
}
