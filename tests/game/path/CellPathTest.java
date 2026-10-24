package game.path;
import game.exception.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CellPathTest{
    private Cell c1;
    private Cell c2;
    private Cell c3;
    private CellPath cp1;
    private CellPath cp2;
    private CellPath cp3;

    @BeforeEach
    public void before() {
        this.c1 = new Cell(0,0);
        this.c2 = new Cell(0,1);
        this.c3 = new Cell(0,2);


        this.cp1 = new CellPath(this.c1);
        this.cp2 = new CellPath(this.c2);
        this.cp1.setNext(this.cp2);
        this.cp2.setNext(this.cp3);
        this.cp3 = new CellPath(this.c3);
    }

    @Test
    public void getNextCellOk()throws NoNextCellException{
        assertEquals(c2,this.cp1.getNextCell().getCell());
    }

    @Test
    public void getNextCellException()throws NoNextCellException{
        assertThrows(NoNextCellException.class, ()->{
            this.cp3.getNextCell();
        });
    }

    @Test 
    public void isFinishedTrue(){
        assertTrue(this.cp3.isFinished());
    }

    @Test
    public void isFinishedFalse(){
        assertFalse(this.cp1.isFinished());
    }

    @Test
    public void setNextCellTest ()throws NoNextCellException{
        this.cp3.setNext(this.cp2);
        assertEquals(this.cp2,this.cp3.getNextCell());
    }
}