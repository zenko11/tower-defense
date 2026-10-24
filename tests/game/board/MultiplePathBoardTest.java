package game.board;

import game.path.*;
import game.exception.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class MultiplePathBoardTest {

    @Test
    public void ConstructorThrowsExceptionWhenTooMuchPath(){
        assertThrows(TooMuchPathsException.class, () -> new MultiplePathBoard(10,10,10));
    }

    @Test
    public void nbOfPathsRequired() throws Exception{
        int requiredNbOfPaths = 3;
        MultiplePathBoard board = new MultiplePathBoard(10,10,requiredNbOfPaths);

        assertEquals(requiredNbOfPaths,board.getPaths().size());
    }

    @Test
    public void BoardLimits()throws Exception{
        int width = 10;
        int height = 10;
        boolean end = false;

        MultiplePathBoard board = new MultiplePathBoard(width,height,3);

        for (Path path : board.getPaths()){
            end = true;
            CellPath cellPath = path.getFirstCell();
            while(!end){
                if (cellPath.isFinished()){
                    end = true;
                }
                int x = cellPath.getCell().getX();
                int y = cellPath.getCell().getY();

                assertTrue(x >= 0 && x< width);
                assertTrue(y >= 0 && y < height);

                cellPath = cellPath.getNextCell();
            }
        }
    }

}
