package game;
import game.board.*;
import game.exception.*;
import game.listchooser.*;
import game.manageAction.*;
import game.path.*;
import game.player.*;
import game.tower.*;
import game.tower.upgrade.*;
import java.util.*;


public class Livrable5 {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        if (args.length>=3){
            try {
                

                // Board and path
                int height = Integer.parseInt(args[0]);
                int width = Integer.parseInt(args[1]);
                int nbOfBallons = Integer.parseInt(args[2]);
                ClassicalBoard board = new ClassicalBoard(width,height);
                List<Path> p = board.getPaths();
                System.out.println(p);
                board.show();
                System.out.println(board);

                

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

                
                

                // Upgrades
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
                UpgradeTree upgradeTreeFreeze = new UpgradeTree(upgradesFreeze);
                UpgradeTree upgradeTreeSlowing = new UpgradeTree(slowingTower);
                UpgradeTree upgradeTreeClassic1 = new UpgradeTree(upgradesClassic);
                UpgradeTree upgradeTreeFreeze1 = new UpgradeTree(upgradesFreeze);
                UpgradeTree upgradeTreeSlowing1 = new UpgradeTree(slowingTower);

                // Towers
                Cell cell1 = towerCell(board,width,height);
                Tower classicTower1 = new Tower(6, 200, classicBullet, 100, cell1, p,upgradeTreeClassic,"classic");
                Cell cell2 = towerCell(board,width,height);
                Tower classicTower2 = new Tower(6, 200, classicBullet, 120, cell2, p,upgradeTreeClassic1,"classic");
                Cell cell3 = towerCell(board,width,height);
                Tower slowingTower1 = new Tower(6, 100, slowingBullet,150 , cell3, p,upgradeTreeSlowing,"slow");
                Cell cell4 = towerCell(board,width,height);
                Tower slowingTower2 = new Tower(6, 100, slowingBullet, 150, cell4, p,upgradeTreeSlowing1,"slow");
                Cell cell5 = towerCell(board,width,height);
                Tower freezingTower1 = new Tower(6, 50, freezingBullet, 150, cell5, p,upgradeTreeFreeze,"freeze");
                Cell cell6 = towerCell(board,width,height);
                Tower freezingTower2 = new Tower(6, 100, freezingBullet, 150, cell6, p,upgradeTreeFreeze1,"freeze");
                
                

                //Shop

                List<Tower> ltowers = new ArrayList<>();
                ltowers.add(classicTower1);
                ltowers.add(classicTower2);
                ltowers.add(slowingTower1);
                ltowers.add(slowingTower2);
                ltowers.add(freezingTower1);
                ltowers.add(freezingTower2);
                Shop shop = new Shop(ltowers);

                //Player
                Player player = new Player(shop);

                //Game
                Game game = new Game(board, nbOfBallons, player);

                //purchase towers;
                ManageAction buy1 = new BuyTowerAction(player, classicTower1, cell1.getX(), cell1.getY(), game);
                buy1.perform();
                ManageAction buy2 = new BuyTowerAction(player, classicTower2, cell2.getX(), cell2.getY(), game);
                buy2.perform();
                ManageAction buy3 = new BuyTowerAction(player, slowingTower1, cell3.getX(), cell3.getY(), game);
                buy3.perform();
                ManageAction buy4 = new BuyTowerAction(player, slowingTower2, cell4.getX(), cell4.getY(), game);
                buy4.perform();
                ManageAction buy5 = new BuyTowerAction(player, freezingTower1, cell5.getX(), cell5.getY(), game);
                buy5.perform();
                ManageAction buy6 = new BuyTowerAction(player, freezingTower2, cell6.getX(), cell6.getY(), game);
                buy6.perform();

            
                

                for (int wave = 1; wave <= 10; wave++) {
                    boolean isUpgrading = (wave <= 5);
                    playWaveWithAction(wave, isUpgrading, game, player);
                }

                
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

    private static Cell towerCell(ClassicalBoard board, int width, int height){
        Random rng = new Random();
        int x = rng.nextInt(width);
        int y = rng.nextInt(height);
        /*while (!(board.getCell(x, y).isEmpty())){
            x = rng.nextInt(width);
            y = rng.nextInt(height);
        }*/
        board.getCell(x, y).setEmpty(false);
        return board.getCell(x, y);
    }

    private static void playWaveWithAction(int wave, boolean isUpgrading, Game game, Player player) {
        try {
            System.out.println("\n--- MANCHE " + wave + " ---");
            
            game.init_balloons();
            

            RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
            RandomListChooser<Upgrade> randomUpgradeChooser = new RandomListChooser<>();
            
            List<Tower> eligibleTowers = new ArrayList<>();
            for (Tower t : game.getTowers()) {
                if (isUpgrading && !t.getUpgradeTree().getInactiveUpgrades().isEmpty()) {
                    eligibleTowers.add(t);
                } else if (!isUpgrading && !t.getUpgradeTree().getActiveUpgrades().isEmpty()) {
                    eligibleTowers.add(t);
                }
            }
            
            Tower selectedTower = randomTowerChooser.choose("Sélection de la tour :", eligibleTowers);

            if (selectedTower != null) {
               
                List<Upgrade> availableUpgrades;
                if(isUpgrading){
                    availableUpgrades=selectedTower.getUpgradeTree().getInactiveUpgrades();
                }
                else{
                    availableUpgrades=selectedTower.getUpgradeTree().getActiveUpgrades();
                }   
                    
                    
                Upgrade selectedUpgrade = randomUpgradeChooser.choose("Sélection de l'amélioration :", availableUpgrades);

                
                ManageAction action;
                if (isUpgrading) {
                    action = new UpgradeTowerAction(player, selectedTower, selectedUpgrade);
                   
                } else {
                    action = new SellTowerUpgradeAction(player, selectedTower, selectedUpgrade);
                    
                }
                
                action.perform();
            }

            
            game.playWave();
            System.out.println("Fin de la manche " + wave + ".\n");

        } catch (NoNextCellException ex) {
            System.out.println("Erreur de chemin : " + ex.getMessage());
        }
    }
}

