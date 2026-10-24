package game;
import game.board.*;
import game.exception.*;
import game.listchooser.util.*;
import game.listchooser.*;
import game.manageAction.*;
import game.path.*;
import game.player.*;
import game.tower.*;
import game.tower.upgrade.*;
import java.util.*;


public class Towerdefense_b_interactive {
    public static void main(String[] args) throws TooMuchPathsException,NoNextCellException{
        if (args.length>=4){
            try {
                

                // Board and path
                int height = Integer.parseInt(args[1]);
                int width = Integer.parseInt(args[0]);
                int nbOfPaths = Integer.parseInt(args[2]);
                int nbOfBallons = Integer.parseInt(args[3]);
                MultiplePathBoard board = new MultiplePathBoard(width,height,nbOfPaths);
                List<Path> p = board.getPaths();
                //System.out.println(p);
                board.show();
               // System.out.println(board);

                

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
        board.getCell(x, y).setEmpty(false);
        return board.getCell(x, y);
    }

    /**
     * Randomly determines an action to perform during the player's turn (choose to buy a tower, sell a tower, buy or sell an upgrade, or do nothing, 
     * 
     * @param game   The current game
     * @param player The player
     * @param shop   The shop containing available towers to buy.
     * @param board  The game board.
     * @return A ManageAction  or null.
     */
    private static ManageAction whichAction(Game game, Player player, Shop shop, Board board) {
        System.out.println("which action do you want to do ?\n\t - 1 Buy a tower \n\t - 2 Sell a tower\n\t - 3 Buy an upgrade\n\t - 4 Sell an upgrade\n\t - 5 Show the board\n\t - 6 Continue");
        Input input = new Input();
        String chosenAction = input.readString();
        ManageAction res = null;

        switch (chosenAction) {
            case "1":
                res = DoBuyTowerAction(game, player, shop, board);break;
            case "2":
                res =  DoSellTowerAction(game, player);break;
            case "3":
                res = DoBuyUpgradeAction(game, player);break;
            case "4":
                res = DoSellUpgradeAction(game, player);break;
            case "5":
                res = new ShowBoardAction(board);break;
            case "6":
                System.out.println("You chose to do continue.");
                return null; 
            default:
                return null;
        }
        if(res == null){
            return whichAction(game,player,shop,board);
        } else {
            return res;
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
    private static ManageAction DoBuyTowerAction(Game game, Player player, Shop shop, Board board) {
        List<Tower> towers = shop.getTowers();
        Input input = new Input();
        Tower tower = null;
        int x = -1;
        int y = -1;
        while(tower == null){ // tant que la tour n'est pas correct
            try {
                System.out.println("You have " + player.getMoney());
                System.out.println("Which tower do you want to choose :\n"+getListName(towers)+"- "+(towers.size()+1)+" none");
                String chosenTower = input.readString();

                int towerIndex = Integer.parseInt(chosenTower)-1;

                if(towerIndex == towers.size()){
                    return null;
                }

                tower = towers.get(towerIndex).copy();
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }
        int[] coordinates = getXandY(board);
        x = coordinates[0];
        y = coordinates[1];
        return new BuyTowerAction(player, tower, x, y, game);
    }

    static int[] getXandY(Board board){
        Input input = new Input();
        int x = -1;
        int y = -1;
        while(x == -1){ // tant que x n'est pas correct
            try {
                System.out.println("Enter the x position of the tower");
                String xChosen = input.readString();
                x = Integer.parseInt(xChosen);
                if(x < 0 || x >= board.getWidth()){
                    x = -1;
                }
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }
        while(y == -1){ // tant que y n'est pas correct
            try {
                System.out.println("Enter the y position of the tower");
                String yChosen = input.readString();
                y = Integer.parseInt(yChosen);
                if(y < 0 || y >= board.getHeight()){
                    y = -1;
                }
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }
        int[] res = {x,y}; 
        return res;
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
     * let the player select a tower from the game and then sells it.
     *
     * @param game   the current game
     * @param player the player
     * @return a SellTowerAction, or null if there is no tower.
     */
    private static ManageAction DoSellTowerAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null; 

        RandomListChooser<Tower> randomTowerChooser = new RandomListChooser<>();
        List<Tower> availableTowers = new ArrayList<>(game.getTowers());
        Input input = new Input();
        Tower tower = null;
        while(tower == null){ // tant que la tour n'est pas correct
            try {
                System.out.println("Which tower do you want to sell :\n"+getListName(availableTowers)+"- "+(availableTowers.size()+1)+" none");
                String chosenTower = input.readString();

                int towerIndex = Integer.parseInt(chosenTower)-1;

                if(towerIndex == availableTowers.size()){
                    return null;
                }

                tower = availableTowers.get(towerIndex);
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }
        return new SellTowerAction(player, tower, game); 
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
    private static ManageAction DoBuyUpgradeAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null;
        Tower tower = null;
        Upgrade upgrade = null;
        Input input = new Input();

        List<Tower> eligibleTowers = new ArrayList<>();
        for (Tower t : game.getTowers()) {
            if (!t.getUpgradeTree().getInactiveUpgrades().isEmpty()) {
                eligibleTowers.add(t);
            }
        }
        if (eligibleTowers.isEmpty()) return null;

        while(tower == null){ // tant que la tour n'est pas correct
            try {
                System.out.println("You have " + player.getMoney());
                System.out.println("Which tower do you want to upgrade:\n"+getListName(eligibleTowers)+"- "+(eligibleTowers.size()+1)+" none");
                String chosenTower = input.readString();

                int towerIndex = Integer.parseInt(chosenTower)-1;

                if(towerIndex == eligibleTowers.size()){
                    return null;
                }

                tower = eligibleTowers.get(towerIndex);
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(upgrade == null){ // tant que la tour n'est pas correct
            try {
                List<Upgrade> upgrades = tower.getUpgradeTree().getInactiveUpgrades();
                System.out.println("Which upgrade do you want to choose:\n"+getListName(upgrades)+"- "+(upgrades.size()+1)+" none");
                String chosenUpgrade = input.readString();

                int upgradeIndex = Integer.parseInt(chosenUpgrade)-1;

                if(upgradeIndex == upgrades.size()){
                    return null;
                }

                upgrade = upgrades.get(upgradeIndex);
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        return new UpgradeTowerAction(player, tower, upgrade);
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
    private static ManageAction DoSellUpgradeAction(Game game, Player player) {
        if (game.getTowers().isEmpty()) return null;

        List<Tower> eligibleTowers = new ArrayList<>();
        for (Tower t : game.getTowers()) {
            if (!t.getUpgradeTree().getInactiveUpgrades().isEmpty()) {
                eligibleTowers.add(t);
            }
        }
        if (eligibleTowers.isEmpty()) return null;

        Tower tower = null;
        Upgrade upgrade = null;
        Input input = new Input();

        while(tower == null){ // tant que la tour n'est pas correct
            try {
                System.out.println("Which tower do you want to sell the upgrade:\n"+getListName(eligibleTowers)+"- "+(eligibleTowers.size()+1)+" none");
                String chosenTower = input.readString();

                int towerIndex = Integer.parseInt(chosenTower)-1;

                if(towerIndex == eligibleTowers.size()){
                    return null;
                }

                tower = eligibleTowers.get(towerIndex);
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        while(upgrade == null){ // tant que la tour n'est pas correct
            try {
                List<Upgrade> upgrades = tower.getUpgradeTree().getActiveUpgrades();
                System.out.println("Which upgrade do you want to choose:\n"+getListName(upgrades)+"- "+(upgrades.size()+1)+" none");
                String chosenUpgrade = input.readString();

                int upgradeIndex = Integer.parseInt(chosenUpgrade)-1;

                if(upgradeIndex == upgrades.size()){
                    return null;
                }

                upgrade = upgrades.get(upgradeIndex);
            } catch (Exception e){
                System.out.println("Enter a valid number");
            }
        }

        return new SellTowerUpgradeAction(player, tower, upgrade);
    }
    /**
     * Executes a single wave of the game.
     * This method initializes the balloons for the wave
     * perform multiple action (buying a tower,selling a tower, buying an upgrade or selling an upgrade), 
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
            while (action != null) {
                action.perform();
                game.UpdateDestroyedTowers();
                action = whichAction(game, player, player.getShop(), board);
            }
            game.playWave();
            System.out.println("End of wave : " + wave + ".\n");

        } catch (NoNextCellException ex) {
            System.out.println("Error  : " + ex.getMessage());
        }
    }
}

