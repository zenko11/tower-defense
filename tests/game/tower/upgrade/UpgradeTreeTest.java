package game.tower.java;
import java.util.*;
import game.tower.upgrade.DamageUpgrade;
import game.tower.upgrade.RangeUpgrade;
import game.tower.upgrade.Upgrade;
import game.tower.upgrade.UpgradeTree;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpgradeTreeTest {
    private List<Upgrade> upgrades ;
    private Upgrade range1 ;
    private Upgrade range2 ;
    private Upgrade damage1 ;
    private UpgradeTree tree;


    @BeforeEach
    public void before(){
        this.upgrades = new ArrayList<>();
        this.range1 = new RangeUpgrade(0, 0.20);
        this.range2 = new RangeUpgrade(0, 0.50);
        this.damage1 = new DamageUpgrade(0, 0.30);
        this.upgrades.add(range1);
        this.upgrades.add(range2);
        this.upgrades.add(damage1);
        this.tree = new UpgradeTree(upgrades);
    }

    @Test
    public void buyTest(){
        tree.buy(range1);
        assertEquals(range1,this.tree.getActiveUpgrades().get(0));
    }

    @Test
    public void sellTest(){
        tree.buy(range1);
        tree.sell(range1);
        assertTrue(tree.getActiveUpgrades().isEmpty());
    }

    @Test
    public void getMultiplierTest(){
        tree.buy(range1);
        tree.buy(range2);
        tree.buy(damage1);

        assertTrue(tree.getRangeMultiplier() == 1.70);
    }
}
