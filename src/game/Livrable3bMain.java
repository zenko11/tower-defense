package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import game.tower.*;
import game.tower.upgrade.*;
import java.util.*;

public class Livrable3bMain {
    /*
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        if (args.length>=4){
            try {
                // Board and path
                int width = Integer.parseInt(args[0]);
                int height = Integer.parseInt(args[1]);
                int nbOfPaths = Integer.parseInt(args[2]);
                int nbOfBallons = Integer.parseInt(args[3]);
                MultiplePathBoard board = new MultiplePathBoard(width,height,nbOfPaths);
                List<Path> p = board.getPaths();
                System.out.println(p);
                board.show();
                System.out.println(board);

                // Game
                Game game = new Game(board,nbOfBallons);

                // Effects
                List<Effect> classicTowerEffects = new LinkedList<>();
                List<Effect> slowingTowerEffects = new LinkedList<>();
                List<Effect> freezingTowerEffects = new LinkedList<>();

                classicTowerEffects.add(new Damage("Dégât", 1, false, 2 ));
                slowingTowerEffects.add(new Damage("Dégât", 1, false, 1 ));
                slowingTowerEffects.add(new Slow("Slow", 3, false));
                freezingTowerEffects.add(new Damage("Dégât", 1, false, 1 ));
                freezingTowerEffects.add(new Freeze("Freeze", 3, false));

                // Bullets
                Bullet classicBullet = new Bullet(0, 1, classicTowerEffects);
                Bullet slowingBullet = new Bullet(0, 1, slowingTowerEffects);
                Bullet freezingBullet = new Bullet(0, 1, freezingTowerEffects);

                // Empty Upgrade
                List<Upgrade> listUp = new ArrayList<>();
                // Towers
                Cell cell1 = towerCell(board,width,height);
                Tower classicTower1 = new Tower(6, 500, classicBullet, 20, cell1, p,listUp);
                Cell cell2 = towerCell(board,width,height);
                Tower classicTower2 = new Tower(6, 500, classicBullet, 20, cell2, p,listUp);
                Cell cell3 = towerCell(board,width,height);
                Tower slowingTower1 = new Tower(6, 500, slowingBullet, 20, cell3, p,listUp);
                Cell cell4 = towerCell(board,width,height);
                Tower slowingTower2 = new Tower(6, 500, slowingBullet, 20, cell4, p,listUp);
                Cell cell5 = towerCell(board,width,height);
                Tower freezingTower1 = new Tower(6, 500, freezingBullet, 20, cell5, p,listUp);
                Cell cell6 = towerCell(board,width,height);
                Tower freezingTower2 = new Tower(6, 500, freezingBullet, 20, cell6, p,listUp);

                game.setTower(classicTower1);
                game.setTower(classicTower2);
                game.setTower(slowingTower1);
                game.setTower(slowingTower2);
                game.setTower(freezingTower1);
                game.setTower(freezingTower2);



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
            System.out.println("4 args needed");
        }
    }

    private static Cell towerCell(MultiplePathBoard board, int width, int height){
        Random rng = new Random();
        int x = rng.nextInt(width);
        int y = rng.nextInt(height);
        while (!(board.getCell(x, y).isEmpty())){
            x = rng.nextInt(width);
            y = rng.nextInt(height);
        }
        board.getCell(x, y).setEmpty(false);
        return board.getCell(x, y);
    }
    */
}
