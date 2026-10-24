package game;
import game.board.*;
import game.exception.*;
import game.path.*;
import game.tower.*;
import game.tower.upgrade.*;
import java.util.*;

public class Livrable4bMain {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        /*if (args.length>=3){
            try {
                // Board and path
                int width = Integer.parseInt(args[0]);
                int height = Integer.parseInt(args[1]);
                int nbOfPaths = Integer.parseInt(args[2]);
                int nbOfBallons = 1;
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
                List<Upgrade> upgradesClassic = new ArrayList<Upgrade>();
                List<Upgrade> upgradesFreeze = new ArrayList<Upgrade>();
                List<Upgrade> slowingTower = new ArrayList<Upgrade>();

                Upgrade cooldownUpgrade = new CooldownUpgrade(10,0.2);
                Upgrade areaUpgrade = new AreaUpgrade(10,1);
                Upgrade damageUpgrade = new DamageUpgrade(10,3);
                Upgrade rangeUpgrade = new RangeUpgrade(10,0.2);

                upgradesClassic.add(cooldownUpgrade);
                upgradesClassic.add(areaUpgrade);
                upgradesClassic.add(damageUpgrade);
                upgradesClassic.add(damageUpgrade);
                upgradesClassic.add(rangeUpgrade);

                upgradesFreeze.add(rangeUpgrade);
                upgradesFreeze.add(rangeUpgrade);
                upgradesFreeze.add(rangeUpgrade);
                upgradesFreeze.add(cooldownUpgrade);
                upgradesFreeze.add(cooldownUpgrade);

                slowingTower.add(rangeUpgrade);
                slowingTower.add(rangeUpgrade);
                slowingTower.add(rangeUpgrade);
                slowingTower.add(cooldownUpgrade);
                slowingTower.add(cooldownUpgrade);

                UpgradeTree upgradeTreeClassic = new UpgradeTree(upgradesClassic);
                UpgradeTree upgradeTreeClassic2 = new UpgradeTree(upgradesClassic);
                UpgradeTree upgradeTreeFreeze = new UpgradeTree(upgradesFreeze);
                UpgradeTree upgradeTreeFreeze2 = new UpgradeTree(upgradesFreeze);
                UpgradeTree upgradeTreeSlowing = new UpgradeTree(slowingTower);
                UpgradeTree upgradeTreeSlowing2 = new UpgradeTree(slowingTower);

                // Towers
                Cell cell1 = towerCell(board,width,height);
                Tower classicTower1 = new Tower(6, 500, classicBullet, 20, cell1, p,upgradeTreeClassic,"classic");
                Cell cell2 = towerCell(board,width,height);
                Tower classicTower2 = new Tower(6, 500, classicBullet, 20, cell2, p,upgradeTreeClassic2,"classic");
                Cell cell3 = towerCell(board,width,height);
                Tower slowingTower1 = new Tower(6, 500, slowingBullet, 20, cell3, p,upgradeTreeSlowing,"slow");
                Cell cell4 = towerCell(board,width,height);
                Tower slowingTower2 = new Tower(6, 500, slowingBullet, 20, cell4, p,upgradeTreeSlowing2,"slow");
                Cell cell5 = towerCell(board,width,height);
                Tower freezingTower1 = new Tower(6, 500, freezingBullet, 20, cell5, p,upgradeTreeFreeze,"freeze");
                Cell cell6 = towerCell(board,width,height);
                Tower freezingTower2 = new Tower(6, 500, freezingBullet, 20, cell6, p,upgradeTreeFreeze2,"freeze");

                game.setTower(classicTower1);
                game.setTower(classicTower2);
                game.setTower(slowingTower1);
                game.setTower(slowingTower2);
                game.setTower(freezingTower1);
                game.setTower(freezingTower2);



                for(int i = 0;i<5;i++){
                    game.playWave();
                    for(Tower tower : game.getTowers()){
                        UpgradeTree upgradeTree = tower.getUpgradeTree();
                        List<Upgrade> upgradesAvailabe = upgradeTree.getInactiveUpgrades();

                        Upgrade upgrade = upgradesAvailabe.get(0);
                        tower.buyUp(upgrade);
                        System.out.println("Bought :" + upgrade.toString() + " for tower : " + tower.toString());
                    }
                    System.out.println("\nWave " + (i+1) + " finished\n");
                }
                for(int i = 0;i<5;i++){
                    game.playWave();
                    for(Tower tower : game.getTowers()){
                        UpgradeTree upgradeTree = tower.getUpgradeTree();
                        List<Upgrade> upgrades = upgradeTree.getActiveUpgrades();
                        Upgrade upgrade = upgrades.get(0);
                        tower.sellUp(upgrade);
                        System.out.println("Sold :" + upgrade.toString() + "for tower :" + tower.toString());
                    }
                    System.out.println("\nWave " + (i+6) + " finished\n");
                }

            }
            catch ( Exception e) {
                    System.out.println("args need to be int type");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
            }
        }
        else {
            System.out.println("3 args needed");
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
        return board.getCell(x, y);*/
    }
}
