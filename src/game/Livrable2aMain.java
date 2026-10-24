package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import java.util.*;

public class Livrable2aMain {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        /*if (args.length>=2){
            try {
                int height = Integer.parseInt(args[0]);
                int width = Integer.parseInt(args[1]);
                int nbOfBallons = Integer.parseInt(args[2]);
                ClassicalBoard board = new ClassicalBoard(height,width);
                List<Path> p = board.getPaths();
                System.out.println(p);
                board.show();
                System.out.println(board);
                Game game = new Game(board,nbOfBallons);
                while (!game.isFinished()){
                    game.loop();
                }

            }
            catch ( Exception e) {
                    System.out.println("args need to be int type");
                    System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("2 args needed");
        }*/
    }
}
