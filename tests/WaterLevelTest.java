import junit.framework.TestCase;
import models.Plateau;
import models.Player;
import models.WaterLevel;

public class WaterLevelTest extends TestCase {
    public void test() throws Exception {
        WaterLevel lvl = new WaterLevel(1);
        assertEquals(lvl.getCurrentLvl(),1);
        assertEquals(lvl.getMaxLvl(),10);
        lvl.augmenteLvl();
        assertEquals(lvl.getCurrentLvl(),2);
        assertEquals(lvl.nbCaseInonde(),2);
        lvl.augmenteLvl();
        assertEquals(lvl.nbCaseInonde(),3);
    }
}
