package models;
import junit.framework.TestCase;

public class ObjetTest extends TestCase {
    public void test() throws Exception {
        Plateau plateau = new Plateau(8);
        Player player1 = new Player(plateau,0,"player1",3,3);
        assertEquals(player1.getCase().getX(),3);
        assertNotSame(player1.getCase().getX(),2);
    }
}