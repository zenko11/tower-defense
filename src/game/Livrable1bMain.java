package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import java.util.*;
public class Livrable1bMain {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        if (args.length>=3){
            try {
                int height = Integer.parseInt(args[0]);
                int width = Integer.parseInt(args[1]);
                int nbOfPaths = Integer.parseInt(args[2]);
                MultiplePathBoard board = new MultiplePathBoard(height,width,nbOfPaths);
                List<Path> paths = board.getPaths();
                System.out.println(paths);
                board.show();
                System.out.println(board);

            }
            catch ( Exception e) {
                    System.out.println("args need to be int type");
                    System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("3 args needed");
        }
    }
}
