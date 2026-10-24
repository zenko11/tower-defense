package game.tower;
import java.util.*;
import game.balloon.*;
import game.path.*;
import game.tower.upgrade.Upgrade;
import game.tower.upgrade.UpgradeTree;
import game.exception.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private List<Effect> effects;
    private Bullet bullet;
    private Set<Balloon> balloons;
    private List<Path> paths;
    private Tower tower;
    private UpgradeTree upgrade;
    
    @BeforeEach
    public void before() throws TooMuchPathsException,NoNextCellException{
        this.effects = new ArrayList<>();
        this.effects.add(new Damage("damage",0, false, 1));
        this.bullet = new Bullet(1, 0, this.effects);

        this.balloons = new HashSet<>();
        CellPath cp =new CellPath(new Cell(10, 0));
        for(int i=10;i>=0;i--){
            cp =new CellPath(new Cell(i,0),cp);
        }
        Path p = new Path(cp);
        this.paths =new ArrayList<>();
        this.paths.add(p);
        List<Upgrade> upgradesClassic = new ArrayList<Upgrade>();
        this.upgrade = new UpgradeTree(upgradesClassic);
        this.tower = new Tower(2, 80, this.bullet, 5,new Cell(3,1), this.paths,this.upgrade,"towerName");
        
    }

    @Test
    public void getRangeTest(){
        assertEquals(this.tower.getRange(),2);
        
    }
    @Test
    public void getCostTest(){
        assertEquals(this.tower.getCost(),80);
    }
    @Test
    public void getBulletTest(){
        assertEquals(this.tower.getBullet(),this.bullet);
    }
    @Test
    public void getReloadTimeTest(){
        assertEquals(this.tower.getReloadTime(),5);
    }
    @Test
    public void getCellTest(){
        assertEquals(this.tower.getCell(),new Cell(3,1));
    }
    @Test
    public void SameTowerCellTest(){
        assertTrue(this.tower.SameTowerCell(this.tower));
        assertFalse(this.tower.SameTowerCell(new Tower(0, 0, this.bullet, 0,new Cell(0,0), this.paths,this.upgrade,"towerName")));
    }
    @Test
    public void shootNotInRangeTest(){
        Balloon balloonTarget = new Balloon(10,2,this.paths.get(0).getFirstCell());
        this.balloons.add(balloonTarget);
        Bullet b = this.tower.shoot(this.balloons);
        assertEquals(null,b);
    }
    @Test
    public void shootInRangeTest()throws NoNextCellException{
        Balloon balloonTarget = new Balloon(10,2,this.paths.get(0).getFirstCell());
        this.balloons.add(balloonTarget);
        balloonTarget.moveForward();
        balloonTarget.moveForward();
        Bullet b = this.tower.shoot(this.balloons);
        assertEquals(b.getTarget(),balloonTarget);
    }
    
    @Test
    public void onClockTest(){
        for(int i = 0;i<=10;i++){
            if(i ==0 || i==5 || i==10){
                assertTrue(this.tower.onClock(i));
            }
            else{
                assertFalse(this.tower.onClock(i));
            }
        }
    }


}
