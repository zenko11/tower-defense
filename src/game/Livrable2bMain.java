package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import java.util.*;
public class Livrable2bMain {

    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        /*if (args.length>=4){
            try {
                int height = Integer.parseInt(args[0]);
                int width = Integer.parseInt(args[1]);
                int nbOfPaths = Integer.parseInt(args[2]);
                int nbofBalloons = Integer.parseInt(args[3]);
                MultiplePathBoard board = new MultiplePathBoard(height,width,nbOfPaths);
                List<Path> p = board.getPaths();
                System.out.println(p);
                board.show();
                System.out.println(board);
                Game game = new Game(board,nbofBalloons);
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
            System.out.println("4 args needed, width,height,nbOfPaths and nbofBalloons");
        }*/
    }

}
