package game;

import org.junit.*;
import org.junit.Assert.*;
import game.balloon.*;
import game.board.*;
import game.player.*;
import game.exception.NoNextCellException;
import game.exception.TooMuchPathsException;
import game.path.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

public class gameTest {



@Test
public void getBalloonsClassicTest() throws TooMuchPathsException,NoNextCellException{
    Board classicalBoard = new ClassicalBoard(10, 10);
    Shop shop = new Shop(new ArrayList<>());
    Player player = new Player(shop);
    Game classicalGame = new Game(classicalBoard,10,player);
    classicalGame.init_balloons();
    Set<Balloon> classicalBalloons =classicalGame.getBalloons();
    
    assertEquals(classicalBalloons.size(),10);
    
    
    Game classicalGame2 = new Game(classicalBoard,50,player);
    classicalGame2.init_balloons();
    Set<Balloon> classicalBalloons2 =classicalGame2.getBalloons();
    assertEquals(classicalBalloons2.size(),50);
    
    
    
}

@Test

public void getBalloonsMultipleBoardTest() throws TooMuchPathsException,NoNextCellException{
    Board multiplePathBoard = new MultiplePathBoard(10, 10, 3);
    Shop shop = new Shop(new ArrayList<>());
    Player player = new Player(shop);
    Game multipleGame = new Game(multiplePathBoard, 10,player);
    multipleGame.init_balloons();
    Set<Balloon> multiBalloons =multipleGame.getBalloons();
    
    assertEquals(multiBalloons.size(),10);
    
    Game multipleGame2 = new Game(multiplePathBoard,50,player);
    multipleGame2.init_balloons();
    Set<Balloon> multipleBalloons2 =multipleGame2.getBalloons();
    assertEquals(multipleBalloons2.size(),50);

}

@Test 
public void isFinishedClassicTest()throws TooMuchPathsException,NoNextCellException{
    Board classicalBoard = new ClassicalBoard(10, 10);
    Shop shop = new Shop(new ArrayList<>());
    Player player = new Player(shop);
    Game game = new Game(classicalBoard,15,player);
    game.init_balloons();
    assertFalse(game.isFinished());
    player.decreaseLife();
    assertFalse(game.isFinished());
    for(int i = 0 ; i < 19 ; i ++){
        player.decreaseLife();
    }
    assertTrue(game.isFinished());

}
@Test 
public void isFinishedMultipleBoardTest()throws TooMuchPathsException,NoNextCellException{
    Board multipleBoard = new MultiplePathBoard(10, 10,3);
    Shop shop = new Shop(new ArrayList<>());
    Player player = new Player(shop);
    Game game = new Game(multipleBoard,15,player);
    game.init_balloons();
    assertFalse(game.isFinished());
    player.decreaseLife();
    assertFalse(game.isFinished());
    for(int i = 0 ; i < 19 ; i ++){
        player.decreaseLife();
    }
    assertTrue(game.isFinished());

}


}
