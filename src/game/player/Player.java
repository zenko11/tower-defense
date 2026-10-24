package game.player;
import game.tower.*;
import game.tower.upgrade.*;
import game.listchooser.util.*;

/**A class for a bloonstd game player */
public class Player{
    /**player lives, game ends if lives hits 0 */
    protected int lives;
    /**player money used to buy and upgrade towers */
    protected int money;
    /**A shop where the player can buy towers and its upgrades */
    protected Shop shop;

    /**
     * Build a player with the game shop
     * @param shop the shop where the player can buy
     */
    public Player(Shop shop){
        this.shop = shop;
        this.money = 2500;
        this.lives = 20;
    }

    /**
     * Get the player money
     * @return the player money
     */
    public int getMoney(){
        return this.money;
    }
    
    /**
     * Get the player lives
     * @return the player lives
     */
    public int getLives(){
        return this.lives;
    }
    /**
     * Get the player's shop
     * @return the player's shop
     */
    public Shop getShop(){
        return this.shop;
    }

    /**
     * give money to this player
     * @param value the value of value to give
     */
    public void addMoney(int value){
        this.money += value;
    }


    /**
     * Buy a tower to a (x,y) position.
     * This method does not set the tower.
     * If the player does not have enough money to buy the tower, this method returns null.
     * @param tower The tower to buy.
     * @param x x coordinate
     * @param y y coordinate
     * @return the tower or null if the player does not have enough money.
     */
    public Tower buyTower(Tower tower,int x,int y){
        int cost = tower.getCost();
        if(this.canBuyTower(tower)){
            this.money = this.money - cost;
            Tower tower2 = tower.copy();
            tower2.setCell(x,y);
            System.out.println("Tower "+ tower.getName()+ " was purchased for "+ cost + " gold. The player has now "+ this.money + " gold.");
            return tower2;
        }
        else{
            System.out.println("You don't have enough money");

            Input input = new Input();
            String chosenAction = input.readString();
            return null;
        }
    }

    /**
     * sell and destroy a tower
     * @param tower the tower to sell
     * @return true
     */
    public boolean sellTower(Tower tower){
        int cost = tower.getCost();
        this.money = this.money + (int)(Math.floor(0.8*cost));
        System.out.println("You sold "+ tower.getName()+" for "+0.8*cost);
        tower.destroy();
        return true;
    }

    /**
     * True if the player can afford the tower
     * @param tower the tower to buy
     * @return true if player money greater or equal to the tower cost
     */
    public boolean canBuyTower(Tower tower){
        int cost = tower.getCost();
        return this.money >= cost;
    }

    /**
     * True if the tower have this upgrade and the player can afford it.
     * @param tower the tower to be upgraded
     * @param upgrade the upgrade to be bought
     * @return true if the upgrade can be applied
     */
    public boolean canBuyUpgrade(Tower tower, Upgrade upgrade){
        if(tower.getUpgradeTree().getInactiveUpgrades().contains(upgrade)){
            int cost = upgrade.getCost();
            return this.money > cost;
        }
        return false;
    }

    /**
     * Buy the upgrade if it can be bought
     * @param tower the tower to upgrade
     * @param upgrade the upgrade to buy
     * @return true if the upgrade is successful
     */
    public boolean buyUpgrade(Tower tower, Upgrade upgrade){
        int cost = upgrade.getCost();
        if(this.canBuyUpgrade(tower,upgrade)){
            this.money = this.money - cost;
            tower.buyUp(upgrade);
            System.out.println("Upgrade was purchased for "+ cost + " gold. The player has now "+ this.money + " gold.");
            return true;
        }
        else{
            System.out.println("You don't have enough money");
            return false;
        }
    }

    /**
     * Sell a tower upgrade
     * @param tower the tower to downgrade
     * @param upgrade the upgrade to sell
     * @return true if you can sell the upgrade, else false
     */
    public boolean sellUpgrade(Tower tower,Upgrade upgrade){
        int cost = upgrade.getCost();
        this.money = this.money + (int)(Math.floor(0.8*cost));
        if(tower.getUpgradeTree().sell(upgrade)){
            System.out.println("You sold "+ tower.getName()+" for "+0.8*cost);
            return true;
        }
        return false;
    }

    /**
     * decrease the player life by 1.
     */
    public void decreaseLife(){
        this.lives = this.lives - 1;
    }
    
}

