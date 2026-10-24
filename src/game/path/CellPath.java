package game.path;
import game.exception.*;
/**
 * class to connect each cell to his next cell
 */
public class CellPath{
    /**the current cell */
    protected Cell cell;
    /**the next cell linked to the current cell */
    protected CellPath nextCell;

    

    /** constructor for a cellPath
     * @param cell the actual cell
     * nextCell is null by default, 
     */
    public CellPath(Cell cell){
        this.cell = cell;
        this.nextCell = null;
    }
    /** constructor for a cellPath 
     * @param cell the actual cell
     * @param nextCell the next cellPath that contains the next cell
     */
    public CellPath(Cell cell,CellPath nextCell){
        this.cell = cell;
        this.nextCell = nextCell;
    }

    /** return the actual cell
     * @return the cell
     */
    public Cell getCell(){
        return this.cell;
    }
    /** set a new CellPath in nextCell
     * @param cell the next cell to set
    */
    public void setNext(CellPath cell){
        this.nextCell = cell;
    }



    /**return the next cellPath if it exist
     * @throws NoNextCellException if next cell is null
     * @return the next cell
     */
    public CellPath getNextCell()throws NoNextCellException{
        if(this.nextCell == null){
            throw new NoNextCellException();
        }
        return this.nextCell;
    }

    /**return if the actual cell is the last cell of the path
     * @return boolean that say if the cell is the last one of the path
     */
    public boolean isFinished(){
        return this.nextCell == null;
    }

    /**return if the actual cell is equals to another cell gived in parameter
     * @param o the other cell to compare
     * @return boolean that say if the cells are equals
     */
    public boolean equals(Object o){
        if (!(o instanceof CellPath)){
            return false;
        }
        CellPath other = (CellPath) o;
        return (this.cell == other.cell && this.nextCell == other.nextCell);
    }
}
