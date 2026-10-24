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


public class Towerdefense_a_random {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        if (args.length>=2){
            try {
                

                // Board and path
                int height = Integer.parseInt(args[1]);
                int width = Integer.parseInt(args[0]);
                int nbOfBallons = 15;
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

                

            
                
                //The waves
                int wave = 1;
                while(!game.gameLost()){
                    playWaveWithAction(wave, game, player, board);
                    wave++;
                }
                

                
            }
            catch ( Exception e) {
                    System.out.println("args need to be int type");
                    System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("2 args needed");
        }
    }

    private static Cell towerCell(Board board, int width, int height){
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

    /**
     * Randomly determines an actionto perform during the player's turn (choose to buy a tower, sell a tower, buy or sell an upgrade, or do nothing, 
     * 
     * @param game   The current game
     * @param player The player
     * @param shop   The shop containing available towers to buy.
     * @param board  The game board.
     * @return A ManageAction  or null.
     */
    private static ManageAction whichAction(Game game, Player player, Shop shop, Board board) {
    
    
        List<ActionType> actions = Arrays.asList(
            ActionType.BUYTOWER, 
         //   ActionType.SELLTOWER, 
         //   ActionType.BUYUPGRADE, 
        //    ActionType.SELLUPGRADE, 
            ActionType.DO_NOTHING
        );

        
        RandomListChooser<ActionType> randomAction = new RandomListChooser<>();
        ActionType chosenAction = randomAction.choose("which action do you want to do ?\n\t - 1 Buy a tower \n\t - 2 Sell a tower\n\t - 3 Buy an upgrade\n\t - 4 Sell an upgrade\n", actions);

    
        switch (chosenAction) {
            case BUYTOWER:
                return generateBuyTowerAction(game, player, shop, board);
            case SELLTOWER:
                return generateSellTowerAction(game, player);
            case BUYUPGRADE:
                return generateBuyUpgradeAction(game, player);
            case SELLUPGRADE:
                return generateSellUpgradeAction(game, player);
            case DO_NOTHING:
            default:
                System.out.println("You chose to do nothing.");
                return null; 
        }
    }

    /**
     * Generates a random action to buy an existing tower from the shop
     * randomly selects a tower from the shop, buy it and place it.
     *
     * @param game   the current game
     * @param player the player
     * @return a BuyTowerAction
     */
    private static ManageAction generateBuyTowerAction(Game game, Player player, Shop shop, Board board) {
        Cell c = towerCell(board, board.getWidth(), board.getHeight());
        RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
        Tower randomModelTower = randomTowerChooser.choose("Which tower do you want to choose :\n"+getListName(shop.getTowers()), shop.getTowers());
        return new BuyTowerAction(player, randomModelTower, c.getX(), c.getY(), game);
    }

    /***get the name listing of a Collection
     * @param list a collection
     * @return a String 
     */
    private static String getListName(Collection<?> list){
        String res = "";
        int i = 1;
        for(Object elt: list ){
            res+="- "+i+" "+elt.toString()+"\n";
            i++;
        }
        return res;
    }

    /**
     * Generates a random action to sell an existing tower
     * randomly selects a tower from the game and then sells it.
     *
     * @param game   the current game
     * @param player the player
     * @return a SellTowerAction, or null if there is no tower.
     */
    private static ManageAction generateSellTowerAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null; 

        RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
        List<Tower> availableTowers = new ArrayList<>(game.getTowers());
        Tower randomTower = randomTowerChooser.choose("Which tower do you want to sell :\n"+getListName(availableTowers),availableTowers);

        return new SellTowerAction(player, randomTower, game); 
    }

    /**
     * Generates a random action to buy an inactive upgrade from an existing tower
     * randomly selects a tower from the game that have at least one inactive upgrade
     * and then randomly selects which of its upgrades to buy.
     *
     * @param game   the current game
     * @param player the player
     * @return a BuyTowerUpgradeAction, or null if no towers can be upgraded.
     */
    private static ManageAction generateBuyUpgradeAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null;

        List<Tower> eligibleTowers = new ArrayList<>();
        for (Tower t : game.getTowers()) {
            if (!t.getUpgradeTree().getInactiveUpgrades().isEmpty()) {
                eligibleTowers.add(t);
            }
        }
        if (eligibleTowers.isEmpty()) return null;

        RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
        Tower towerToUpgrade = randomTowerChooser.choose("Which tower do you want to upgrade:\n"+getListName(eligibleTowers), eligibleTowers);

        RandomListChooser<Upgrade> randomUpgradeChooser = new RandomListChooser<>();
        Upgrade upgradeToBuy = randomUpgradeChooser.choose("Which upgrade do you want to choose:\n"+getListName(towerToUpgrade.getUpgradeTree().getInactiveUpgrades()), towerToUpgrade.getUpgradeTree().getInactiveUpgrades());

        return new UpgradeTowerAction(player, towerToUpgrade, upgradeToBuy);
    }
    /**
     * Generates a random action to sell an active upgrade from an existing tower
     * randomly selects a tower from the game that have at least one active upgrade
     * and then randomly selects which of its upgrades to sell.
     *
     * @param game   the current game
     * @param player the player
     * @return a SellTowerUpgradeAction, or null if no towers can be downgraded.
     */
    private static ManageAction generateSellUpgradeAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null;

    
        List<Tower> eligibleTowers = new ArrayList<>();
        for (Tower t : game.getTowers()) {
            if (!t.getUpgradeTree().getInactiveUpgrades().isEmpty()) {
                eligibleTowers.add(t);
            }
        }
        if (eligibleTowers.isEmpty()) return null;

        RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
        Tower towerToDowngrade = randomTowerChooser.choose("Which tower do you want to downgrade:\n"+getListName(eligibleTowers), eligibleTowers);

    
        RandomListChooser<Upgrade> randomUpgradeChooser = new RandomListChooser<>();
        Upgrade upgradeToSell = randomUpgradeChooser.choose("Listing of active upgrades :\n"+getListName(towerToDowngrade.getUpgradeTree().getActiveUpgrades()), towerToDowngrade.getUpgradeTree().getActiveUpgrades());

        return new SellTowerUpgradeAction(player, towerToDowngrade, upgradeToSell);
    }
    /**
     * Executes a single wave of the game.
     * This method initializes the balloons for the wave
     * to determine and perform a single action (buying a tower,selling a tower, buying an upgrade or selling an upgrade), 
     * and play the wave
     *
     * @param wave   the current wave number being played.
     * @param game   the main game
     * @param player the player that performes the action.
     * @param board  the game board
     */
    private static void playWaveWithAction(int wave, Game game, Player player,Board board) {
        try {
            System.out.println("\n--- WAVE " + wave + " ---");
            
            game.init_balloons();
            
            ManageAction action = whichAction(game, player, player.getShop(), board);
            if (action != null) 
                action.perform();
            
            game.playWave();
            System.out.println("End of wave : " + wave + ".\n");

        } catch (NoNextCellException ex) {
            System.out.println("Error  : " + ex.getMessage());
        }
    }
}

