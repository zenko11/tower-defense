package game.tower;
import java.util.*;
import game.balloon.*;
import game.path.*;
import game.tower.upgrade.Upgrade;
import game.tower.upgrade.UpgradeTree;
import game.exception.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class BulletTest {
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
        this.bullet = new Bullet(-1, 10, this.effects);

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
        this.tower = new Tower(2, 80, this.bullet, 5,new Cell(3,1), this.paths,this.upgrade,"tower");

        for(int i = 0;i<5;i++){
            this.balloons.add(new Balloon(10, 2, cp));
        }
    }
    @Test
    public void getAreaTest(){
        assertEquals(this.bullet.getArea(),-1);
    }
    @Test
    public void getTimeLeftTest(){
        assertEquals(10,this.bullet.getTimeLeft());
    }

    @Test
    public void setBalloonsBeforeSetTest(){
        assertEquals(bullet.getBalloons(),null);
    }
    @Test
    public void setBalloonsAfterSetTest(){
        bullet.setBalloons(this.balloons);
        assertEquals(5,this.bullet.getBalloons().size());
        assertTrue(this.balloons.equals(this.bullet.getBalloons()));
    }
    @Test
    public void addTargetBeforeTest(){
        assertEquals(null, this.bullet.getTarget());
    }

    @Test
    public void addTargetAfterTest(){
        Balloon b = new Balloon(0, 0, null);
        this.bullet.addTarget(b);
        assertEquals(b, this.bullet.getTarget());
    }
    @Test 
    public void onClockOneBallonWithOutAreaTest()throws NoNextCellException{
        Balloon balloon = new Balloon(10, 2, this.paths.get(0).getFirstCell());
        this.balloons.add(balloon);
        this.bullet.setBalloons(this.balloons);
        balloon.moveForward();
        this.bullet.addTarget(balloon);
        for (int i = 0; i < 10; i++) { 
            if(this.bullet.timeLeft>1){
                assertFalse(this.bullet.onClock(i));
            }
            else{
                assertTrue(this.bullet.onClock(i));
            }
            
        }
        assertEquals(2, balloon.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());


    }
     @Test 
    public void onClockTwoBalloonWithOutAreaTest()throws NoNextCellException{
        Balloon balloon = new Balloon(10, 2, this.paths.get(0).getFirstCell());
        Balloon balloon2 = new Balloon(10, 2, this.paths.get(0).getFirstCell());
        this.balloons.add(balloon);
        this.balloons.add(balloon2);
        this.bullet.setBalloons(this.balloons);
        balloon2.moveForward();
        balloon2.moveForward();
        balloon.moveForward();
        balloon.moveForward();
        this.bullet.addTarget(balloon);
        for (int i = 0; i < 10; i++) { 
            if(this.bullet.getTimeLeft()>1){
                assertFalse(this.bullet.onClock(i));
            }
            else{
                assertTrue(this.bullet.onClock(i));
            }
            
        }
        assertEquals(2, balloon.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());
        assertEquals(2,balloon2.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());


    }
    @Test
    public void onClockTwoBalloonWithAreaTest()throws NoNextCellException{
        Balloon balloon = new Balloon(10, 2, this.paths.get(0).getFirstCell());
        Balloon balloon2 = new Balloon(10, 2, this.paths.get(0).getFirstCell());
        this.balloons.add(balloon);
        this.balloons.add(balloon2);
        Bullet bul = new Bullet(1, 10, this.effects);
        bul.setBalloons(this.balloons);
        bul.addTarget(balloon);
        balloon2.moveForward();
        balloon2.moveForward();
        balloon.moveForward();
        balloon.moveForward();
        this.bullet.addTarget(balloon);
        for (int i = 0; i < 10; i++) { 
            if(bul.getTimeLeft()>1){
                assertFalse(bul.onClock(i));
            }
            else{
                assertTrue(bul.onClock(i));
            }
            
        }
        assertEquals(2, balloon.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());
        assertEquals(1,balloon2.getResistance());
        this.effects.get(0).onClock(1);
        assertEquals(1, balloon.getResistance());
        assertEquals(1,balloon2.getResistance());
    }


}
