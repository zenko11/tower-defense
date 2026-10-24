package game.path;
import org.junit.Test;

import game.exception.*;
import game.board.*;
import game.path.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;
public class PathTest {

@Test
public void getPathEmptyTest()throws NoNextCellException{
    List<CellPath> emptyPath = new ArrayList<>();
    Path path = new Path(null);
    assertEquals(emptyPath, path.getPath());

}
@Test
public void getPathNotEmptyTest() throws NoNextCellException{
    List<CellPath> pathList = new ArrayList<>();
    CellPath cp = new CellPath(null,null);
    pathList.add(cp);
    Path path = new Path(cp);
    assertEquals(pathList, path.getPath());
}

@Test
public void EqualsTest() throws NoNextCellException{
    CellPath cp = new CellPath(null,null);
    Cell cell = new Cell(0, 0);
    CellPath cp2 = new CellPath(cell,cp);
    Path path = new Path(cp);
    Path otherPath = new Path(cp);
    Path otherWrongPath = new Path(cp2);
    assertTrue(path.equals(otherPath));
    assertFalse(path.equals(otherWrongPath));


}
}