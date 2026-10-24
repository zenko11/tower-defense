package game;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import game.path.*;
import game.exception.*;
import game.board.ClassicalBoard;
import java.util.*;


public class ClassicalBoardTest{

    @Test
    public void testInitPath() throws TooMuchPathsException,NoNextCellException {
        ClassicalBoard board = new ClassicalBoard(5,5);
        List<Path> paths = board.getPaths();
        Path path = paths.get(0);
        assertEquals(path.getFirstCell().getCell().getX(),0);
        assertEquals(path.getLastCell().getCell().getX(),4);
    }

    @Test
    public void testAddPathThrowError() throws TooMuchPathsException,NoNextCellException{
        ClassicalBoard board = new ClassicalBoard(5,5);
        CellPath cellpath1 = new CellPath(new Cell(0,0));
        CellPath cellpath2 = new CellPath(new Cell(1,1));
        Path newPath = new Path(cellpath1);
        Path newPath2 = new Path(cellpath2);
        assertThrows(TooMuchPathsException.class, ()->board.addPath(newPath2));
    }
}